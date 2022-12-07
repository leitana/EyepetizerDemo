package com.lx.eyepetizerdemo

import androidx.lifecycle.lifecycleScope
import com.lx.common.mvvm.activity.BaseBindActivity
import com.lx.eyepetizerdemo.databinding.ActivitySplashBinding
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
        lifecycleScope.launchWhenCreated {
            delay(500)
            finish()
        }
    }
}