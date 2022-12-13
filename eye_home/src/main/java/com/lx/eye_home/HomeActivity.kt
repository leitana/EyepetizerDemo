package com.lx.eye_home

import android.view.View
import com.gyf.immersionbar.ktx.immersionBar
import com.lx.common.mvvm.activity.BaseBindVMActivity
import com.lx.eye_home.databinding.HomeActivityBinding
import com.lx.lib_base.ext.immersionStatusBar
import dagger.hilt.android.AndroidEntryPoint

/**
 * @title：HomeActivity
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2022/12/12
 */
@AndroidEntryPoint
class HomeActivity: BaseBindVMActivity<HomeViewModel, HomeActivityBinding>(){
    override val getLayoutRes: Int
        get() = R.layout.home_activity

    override fun initView() {
        immersionBar {
            statusBarColor(R.color.home_white)
            navigationBarColor(R.color.home_white)
        }
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR//状态栏为白色 图标显示深色
        mBinding.mBottomNavigationView.itemIconTintList = null
    }

    override fun initData() {
    }

    override fun startObserve() {
    }
}