package com.example.eye_daily.pagingSource

import com.example.eye_daily.api.DailyApi
import javax.inject.Inject

/**
 * @title：BannerPagingSpurceFactory
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2023/02/16
 */
class BannerPagingSourceFactory @Inject constructor(private val dailyApi: DailyApi) {
    fun create():  BannerPagingSource{
        return BannerPagingSource(dailyApi)
    }
}