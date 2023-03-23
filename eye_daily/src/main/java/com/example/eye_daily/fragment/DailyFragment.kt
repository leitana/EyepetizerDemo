package com.example.eye_daily.fragment

import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.eye_daily.adapter.DailyAdapter
import com.example.eye_daily.viewmodel.DailyViewModel
import com.lx.common.ext.dataStore
import com.lx.common.mvvm.fragment.BaseVMFragment
import com.lx.common.router.RouterPath
import com.lx.common.ui.adapter.FooterAdapter
import com.lx.common.utils.DataStoreUtils
import com.lx.eye_daily.R
import com.lx.eye_daily.databinding.DailyFragmentListBinding
import com.lx.lib_base.ext.toastError
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * @title：DailyFragment
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2023/02/14
 */
@AndroidEntryPoint
@Route(path = RouterPath.Daily.PATH_DAILY_FRAGMENT)
class DailyFragment: BaseVMFragment<DailyViewModel, DailyFragmentListBinding>() {

    private val mAdapter: DailyAdapter by lazy { DailyAdapter(mActivity, this) }

    override val getLayoutRes: Int
        get() = R.layout.daily_fragment_list

    override fun initView() {
        binding.run {
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = mAdapter.withLoadStateFooter(FooterAdapter(mAdapter::retry))
            refreshLayout.setOnRefreshListener {
                mViewModel.initDailyUrlManage()
                mAdapter.refresh()
            }
        }
        addLoadStateListener()
    }

    override fun lazyLoadData() {
        lifecycleScope.launch {
            mViewModel.getDailyList().collect { pagingData ->
                mAdapter.submitData(pagingData)
            }
        }
        DataStoreUtils.putSyncData("flag", "1111")
    }

    private fun addLoadStateListener(){
        mAdapter.addLoadStateListener {
            when(it.refresh){
                is LoadState.NotLoading -> {
                    loadFinished()
                    if (it.source.append.endOfPaginationReached) {
                        binding.refreshLayout.setEnableLoadMore(true)
                        binding.refreshLayout.finishLoadMoreWithNoMoreData()
                    } else {
                        binding.refreshLayout.setEnableLoadMore(false)
                    }
                }
                is LoadState.Loading -> {
                    binding.refreshLayout.autoRefresh()
                }
                is LoadState.Error -> {
                    binding.refreshLayout.finishRefresh()
                    val state = it.refresh as LoadState.Error
                    toastError(state.error.message.toString())
                }
            }
        }
    }

    private fun loadFinished(){
        binding.refreshLayout.finishRefresh()
    }
}