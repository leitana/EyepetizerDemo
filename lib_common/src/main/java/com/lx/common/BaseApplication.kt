package com.lx.common

import android.app.Application
import android.content.Context
import kotlin.properties.Delegates

/**
 * @title：BaseApplication
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2022/12/13
 */
open class BaseApplication: Application() {
    companion object {
        var context: Context by Delegates.notNull()
            private set
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}