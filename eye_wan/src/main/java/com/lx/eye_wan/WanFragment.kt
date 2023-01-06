package com.lx.eye_wan

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.lx.common.mvvm.fragment.BaseVMFragment
import com.lx.common.router.RouterPath
import com.lx.eye_wan.adapter.ArticleAdapter
import com.lx.eye_wan.databinding.WanActivityMainBinding
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
class WanFragment: BaseVMFragment<WanActivityMainBinding, WanViewModel>() {

    private val mAdapter : ArticleAdapter by lazy { ArticleAdapter() }

    override val getLayoutRes: Int
        get() = R.layout.wan_activity_main

    override fun initView() {
        binding.run {
            recyclerview.layoutManager = LinearLayoutManager(context)
            recyclerview.adapter = mAdapter
        }
    }

    override fun lazyLoadData() {
        lifecycleScope.launch {
            viewModel.getArticalData().collect { pagingData ->
                mAdapter.submitData(pagingData)
            }
        }
    }
}