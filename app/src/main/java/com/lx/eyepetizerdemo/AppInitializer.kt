package com.lx.eyepetizerdemo

import android.app.Application
import android.content.Context
import androidx.startup.Initializer
import com.alibaba.android.arouter.launcher.ARouter
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger


/**
 * @title：AppInitializer
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2022/11/29
 */
class AppInitializer: Initializer<Unit> {
    override fun create(context: Context) {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(context.applicationContext as Application)
        Logger.addLogAdapter(AndroidLogAdapter())
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}