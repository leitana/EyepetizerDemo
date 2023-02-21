package com.example.eye_player.api

import com.lx.common.model.Issue
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @title：VideoPlayerApi
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2023/02/21
 */
interface VideoPlayerApi {
    @GET("v4/video/related")
    suspend fun getRelateVideoList(@Query("id") id: Int): Issue
}