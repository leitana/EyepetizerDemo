package com.lx.eyepetizerdemo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * @title：App
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2022/11/29
 */
@HiltAndroidApp
class App: Application() {
    override fun onCreate() {
        super.onCreate()

    }
}