package com.lx.common.di

import com.lx.common.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

/**
 * @title：UrlModule
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2022/12/21
 */

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class WanQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class EyeQualifier

@InstallIn(SingletonComponent::class)
@Module
object UrlModule {

    @WanQualifier
    @Provides
    fun provideWanUrl(): String{
        return Constants.WAN_URL
    }

    @EyeQualifier
    @Provides
    fun provideEyeUrl(): String{
        return Constants.EYE_URL
    }
}