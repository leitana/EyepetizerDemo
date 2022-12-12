package com.lx.eye_home

import com.lx.common.mvvm.activity.BaseBindVMActivity
import com.lx.eye_home.databinding.HomeActivityBinding
import com.lx.lib_base.ext.immersionStatusBar

/**
 * @title：HomeActivity
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2022/12/12
 */
//@AndroidEntryPoint
class HomeActivity: BaseBindVMActivity<HomeViewModel, HomeActivityBinding>(){
    override val getLayoutRes: Int
        get() = R.layout.home_activity

    override fun initView() {
        immersionStatusBar(true, android.R.color.white, true, 0.2f)
    }

    override fun initData() {
    }

    override fun startObserve() {
    }
}