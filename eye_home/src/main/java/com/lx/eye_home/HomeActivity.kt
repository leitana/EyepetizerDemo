package com.lx.eye_home

import android.view.View
import androidx.lifecycle.lifecycleScope
import com.gyf.immersionbar.ktx.immersionBar
import com.lx.common.di.EyeQualifier
import com.lx.common.di.WanQualifier
import com.lx.common.mvvm.activity.BaseBindVMActivity
import com.lx.common.net.WanOkHttpClient
import com.lx.eye_home.databinding.HomeActivityBinding
import com.lx.lib_base.ext.immersionStatusBar
import com.lx.lib_base.ext.toastInfo
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * @title：HomeActivity
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2022/12/12
 */
@AndroidEntryPoint
class HomeActivity: BaseBindVMActivity<HomeViewModel, HomeActivityBinding>(){

//    @WanOkHttpClient
//    @WanQualifier
    @WanOkHttpClient
    @Inject
    lateinit var homeApi: HomeApi

//    @WanQualifier
//    @Inject
//    lateinit var retrofit: Retrofit

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
        lifecycleScope.launchWhenCreated {
            val str = homeApi.getTopArticles().apiData().get(0).publishTime
            toastInfo(str.toString())
        }
    }

    override fun startObserve() {
    }
}