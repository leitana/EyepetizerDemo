package com.lx.eyepetizerdemo.buildsrc

/**
 * @title：Libs
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2022/11/29
 */
object Libs {
    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:${Versions.APPCOMPAT}"
        //sdk包下graphics.drawable下有一个VectorDrawable类，对于较高的版本不需要引入此库来支持基于XML矢量图形创建可绘制对象。
        const val vectordrawable = "androidx.vectordrawable:vectordrawable:1.1.0"
        //core包+ktx扩展函数
        const val coreKtx = "androidx.core:core-ktx:${Versions.CORE_KTX}"
        //activity+ktx扩展函数
        const val activityKtx = "androidx.activity:activity-ktx:1.2.3"
        //fragment+ktx扩展函数
        const val fragmentKtx = "androidx.fragment:fragment-ktx:1.3.4"
        const val espresso_core = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
        const val ext_junit = "androidx.test.ext:junit:${Versions.EXT_JUNIT}"
        const val junit = "junit:junit:${Versions.JUNIT}"
        const val annotation = "androidx.annotation:annotation:1.3.0"
        const val multidex = "androidx.multidex:multidex:2.0.1"
        const val startup = "androidx.startup:startup-runtime:1.1.1"

        private const val lifecycle_version = "2.5.1"
        const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"
        const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"

        const val constraintlayout = "androidx.constraintlayout:constraintlayout:2.1.4"

        private const val paging_version = "3.1.1"
        const val paging_runtime = "androidx.paging:paging-runtime:$paging_version"
        const val paging_test = "androidx.paging:paging-common:$paging_version"
    }

    object Hilt {
        private const val hilt_version = "2.44"
        const val hilt_android = "com.google.dagger:hilt-android:${hilt_version}"
        const val hilt_compiler = "com.google.dagger:hilt-android-compiler:${hilt_version}"
    }

    const val material = "com.google.android.material:material:${Versions.MATERIAL}"

    const val gson = "com.google.code.gson:gson:2.8.7"

    //okhttp
    private const val okhttp_version = "4.9.1"
    const val okhttp = "com.squareup.okhttp3:okhttp:$okhttp_version"
    const val urlconnection = "com.squareup.okhttp3:okhttp-urlconnection:$okhttp_version"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:$okhttp_version"
    //retrofit
    private const val retrofit_version = "2.9.0"
    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofit_version"
    //gson转换器
    const val convertGson = "com.squareup.retrofit2:converter-gson:$retrofit_version"
    const val converterMoshi = "com.squareup.retrofit2:converter-moshi:2.9.0"
    //scalars转换器
    const val convertScalars = "com.squareup.retrofit2:converter-scalars:2.9.0"
    const val adapterRxjava = "com.squareup.retrofit2:adapter-rxjava2:$retrofit_version"

    //常用工具
    const val utilcodex = "com.blankj:utilcodex:1.31.1"

    //图片加载框架
    private const val glide_version = "4.14.2"
    const val glide = "com.github.bumptech.glide:glide:$glide_version"
    const val glide_compiler = "com.github.bumptech.glide:compiler:$glide_version"

    //日志
    const val logger = "com.orhanobut:logger:2.2.0"

    //Arouter
    private const val arouter_version = "1.5.2"
    const val arouter = "com.alibaba:arouter-api:$arouter_version"
    const val arouter_compiler = "com.alibaba:arouter-compiler:$arouter_version"

    //协程
    private const val coroutines_version = "1.6.4"
    const val coroutines_core ="org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version"
    const val coroutines_android ="org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version"

    //toast
    const val toasty = "com.github.GrenderG:Toasty:1.5.2"

    private const val immersionbar_version = "3.2.2"
    const val immersionbar = "com.geyifeng.immersionbar:immersionbar:$immersionbar_version"
    const val immersionbar_ktx = "com.geyifeng.immersionbar:immersionbar-ktx:$immersionbar_version"
}