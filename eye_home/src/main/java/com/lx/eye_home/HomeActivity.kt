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
    private var mDailyFragment: Fragment? = null

    @Inject
    lateinit var homeApi: HomeApi

    override val getLayoutRes: Int
        get() = R.layout.home_activity

    override fun initView() {
        immersionStatusBar(false, android.R.color.white, true, 0.5f)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR//状态栏为白色 图标显示深色
//        mBinding.mBottomNavigationView.itemIconTintList = null
        initBottomNavigation()
    }

    override fun initData() {
//        lifecycleScope.launchWhenCreated {
//            val str = homeApi.getTopArticles().apiData().get(0).author
//            toastInfo(str.toString())
//        }
        mViewModel.getSelect().observe(this){ index ->
            switchFragment(index)
        }
    }

    override fun startObserve() {
    }

    private fun saveAndSwitch(index: Int){
        mViewModel.saveSelect(index)
        switchFragment(index)
    }

    private fun initBottomNavigation() {
        mBinding.mBottomNavigationView.itemIconTintList = null
        mBinding.mBottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item_wan -> saveAndSwitch(0)
                R.id.item_daily -> saveAndSwitch(1)
                R.id.item_hot -> saveAndSwitch(2)
                R.id.item_person -> saveAndSwitch(3)
            }
            true
        }
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
            1 -> mDailyFragment?.let {
                transaction.show(it)
            } ?: (ARouter.getInstance().build(RouterPath.Daily.PATH_DAILY_FRAGMENT)
                .navigation() as Fragment).let {
                    mDailyFragment = it
                transaction.add(R.id.mContentFL, it, RouterPath.Daily.PATH_DAILY_FRAGMENT)
            }
        }
        transaction.commitNowAllowingStateLoss()
    }

    private fun hideFragments(transaction: FragmentTransaction){
        mWanAndroidFragment?.let { transaction.hide(it) }
        mDailyFragment?.let { transaction.hide(it) }
    }
}