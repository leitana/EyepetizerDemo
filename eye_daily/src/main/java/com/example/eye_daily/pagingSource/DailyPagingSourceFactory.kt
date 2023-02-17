package com.example.eye_daily.pagingSource

import com.example.eye_daily.api.DailyApi
import com.example.eye_daily.di.DailyUrlManage
import javax.inject.Inject

/**
 * @title：DailyPagingSourceFactory
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2023/02/16
 */
class DailyPagingSourceFactory @Inject constructor(
    val dailyApi: DailyApi,
    val dailyUrlManage: DailyUrlManage
) {
    fun create(): DailyPagingSource = DailyPagingSource(dailyApi, dailyUrlManage)
}