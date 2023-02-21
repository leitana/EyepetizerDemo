package com.example.eye_player.di

import com.example.eye_player.api.VideoPlayerApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * @title：VideoPlayerServiceModule
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2023/02/21
 */
@Module
@InstallIn(SingletonComponent::class)
object VideoPlayerServiceModule {

    @Singleton
    @Provides
    fun provideVideoPlayerService(retrofit: Retrofit): VideoPlayerApi{
        return retrofit.create(VideoPlayerApi::class.java)
    }
}