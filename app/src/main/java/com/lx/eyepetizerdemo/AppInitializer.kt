package com.lx.eyepetizerdemo

import android.content.Context
import androidx.startup.Initializer

/**
 * @title：AppInitializer
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2022/11/29
 */
class AppInitializer: Initializer<Unit> {
    override fun create(context: Context) {
        TODO("Not yet implemented")
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}