package com.lx.eyepetizerdemo

import android.content.Intent
import androidx.lifecycle.lifecycleScope
import com.gyf.immersionbar.ktx.immersionBar
import com.lx.common.mvvm.activity.BaseBindActivity
import com.lx.eye_home.HomeActivity
import com.lx.eyepetizerdemo.databinding.ActivitySplashBinding
import com.lx.lib_base.ext.startActivity
import kotlinx.coroutines.delay

/**
 * @title：SplashAcitivty
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2022/12/07
 */
class SplashAcitivty: BaseBindActivity<ActivitySplashBinding>() {
    override val getLayoutRes: Int
        get() = R.layout.activity_splash

    override fun initData() {
        immersionBar {
            statusBarColor(R.color.white)
            navigationBarColor(R.color.white)
        }
        lifecycleScope.launchWhenCreated {
            delay(500)
            startActivity<HomeActivity>()
            finish()
        }
    }
}