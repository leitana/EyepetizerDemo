package com.lx.common.net

import android.util.Log
import com.lx.common.BuildConfig
import com.lx.common.Constants
import com.lx.common.di.EyeQualifier
import com.lx.common.di.WanQualifier
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 * @title：NetworkModule
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2022/12/20
 */

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class WanOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class EyeOkHttpClient

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor{
        return HttpLoggingInterceptor().also {
            if (BuildConfig.DEBUG) {
                it.level = HttpLoggingInterceptor.Level.BODY
            } else {
                it.level = HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @WanOkHttpClient
    @Singleton
    @Provides
    fun provideOkHttpClientWan(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient{
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS).also {
                if (BuildConfig.DEBUG){
                    it.addInterceptor(httpLoggingInterceptor)
                }
            }
            .build()
    }

    @EyeOkHttpClient
    @Singleton
    @Provides
    fun provideOkHttpClientEye(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient{
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS).also {
                if (BuildConfig.DEBUG){
                    it.addInterceptor(httpLoggingInterceptor)
                }
            }
            .build()
    }

    @Singleton
    @Provides
    fun provideWanRetrofit(@WanOkHttpClient okHttpClient: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.WAN_URL)
            .client(okHttpClient)
            .build()
    }


    @Singleton
    @Provides
    fun provideEyeRetrofit(@EyeOkHttpClient okHttpClient: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.EYE_URL)
            .client(okHttpClient)
            .build()
    }
}