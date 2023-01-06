package com.lx.eye_home

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.launcher.ARouter
import com.gyf.immersionbar.ktx.immersionBar
import com.lx.common.di.EyeQualifier
import com.lx.common.di.WanQualifier
import com.lx.common.mvvm.activity.BaseBindVMActivity
import com.lx.common.net.WanOkHttpClient
import com.lx.common.net.WanUrlQualifier
import com.lx.common.router.RouterPath
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

    private var mWanAndroidFragment: Fragment? = null

    @Inject
    lateinit var homeApi: HomeApi

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
//        lifecycleScope.launchWhenCreated {
//            val str = homeApi.getTopArticles().apiData().get(0).author
//            toastInfo(str.toString())
//        }
        viewModel.getSelect().observe(this){ index ->
            switchFragment(index)
        }
    }

    override fun startObserve() {
    }

    private fun switchFragment(position: Int){
        val transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)
        when(position) {
            0 -> mWanAndroidFragment?.let {
                transaction.show(it)
            } ?: (ARouter.getInstance().build(RouterPath.Wan.PATH_WAN_FRAGMENT)
                .navigation() as Fragment).let {
                    mWanAndroidFragment = it
                transaction.add(R.id.mContentFL, it, RouterPath.Wan.PATH_WAN_FRAGMENT)
            }
        }
        transaction.commitNowAllowingStateLoss()
    }

    private fun hideFragments(transaction: FragmentTransaction){
        mWanAndroidFragment?.let { transaction.hide(it) }
    }
}