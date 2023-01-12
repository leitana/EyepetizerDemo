package com.lx.eye_wan

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.lx.common.mvvm.fragment.BaseVMFragment
import com.lx.common.router.RouterPath
import com.lx.common.ui.adapter.FooterAdapter
import com.lx.eye_wan.adapter.ArticleAdapter
import com.lx.eye_wan.databinding.WanActivityMainBinding
import com.lx.eye_wan.viewmodel.WanViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * @title：WanFragment
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2022/12/22
 */
@AndroidEntryPoint
@Route(path = RouterPath.Wan.PATH_WAN_FRAGMENT)
class WanFragment: BaseVMFragment<WanViewModel, WanActivityMainBinding>() {

    private val mAdapter : ArticleAdapter by lazy { ArticleAdapter() }

    override val getLayoutRes: Int
        get() = R.layout.wan_activity_main

    override fun initView() {
        binding.run {
            recyclerview.layoutManager = LinearLayoutManager(context)
//            recyclerview.adapter = mAdapter
            recyclerview.adapter = mAdapter.withLoadStateFooter(FooterAdapter(mAdapter::retry))
//            recyclerview.setHasFixedSize(true)
        }
    }

    override fun lazyLoadData() {
        lifecycleScope.launch {
            mViewModel.getArticleData().collect { pagingData ->
                mAdapter.submitData(pagingData)
            }
        }
    }
}