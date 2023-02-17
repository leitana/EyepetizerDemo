package com.example.eye_daily.api

import com.example.eye_daily.model.DailyModel
import com.lx.common.model.Issue
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * @title：DailyApi
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2023/02/14
 */
interface DailyApi {
    @GET()
    suspend fun getDailyBanner(@Url url: String?): DailyModel

    @GET
    suspend fun getDailyList(@Url url: String?): DailyModel

    @GET("v3/queries/hot")
    suspend fun getKeyWordList(): List<String>

    @GET("v1/search")
    suspend fun searchVideoList(@Query("query") keyword: String): Issue

    @GET
    suspend fun getMoreSearchVideoList(@Url url: String): Issue
}