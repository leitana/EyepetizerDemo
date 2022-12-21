package com.lx.eye_home.di

import com.lx.common.di.WanQualifier
import com.lx.common.net.WanOkHttpClient
import com.lx.eye_home.HomeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * @title：ApiMudule
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2022/12/21
 */
@InstallIn(SingletonComponent::class)
@Module
object ApiMudule {

    @Singleton
    @Provides
//    @WanOkHttpClient
//    @WanQualifier
    fun provideTopArticle(retrofit: Retrofit): HomeApi{
        return retrofit.create(HomeApi::class.java)
    }
}