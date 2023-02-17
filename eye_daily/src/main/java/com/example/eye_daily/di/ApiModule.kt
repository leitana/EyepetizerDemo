package com.example.eye_daily.di

import com.example.eye_daily.api.DailyApi
import com.lx.common.net.EyeUrlQualifier
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

/**
 * @title：ApiModule
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2023/02/14
 */
@InstallIn(SingletonComponent::class)
@Module
object ApiModule {

    @Singleton
    @Provides
    fun provideDailyApi(@EyeUrlQualifier retrofit: Retrofit): DailyApi{
        return retrofit.create(DailyApi::class.java)
    }
}