package com.example.eye_daily.di

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.eye_daily.constant.Constant
import javax.inject.Inject

/**
 * @title：DailyUrlManage
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2023/02/16
 */
class DailyUrlManage @Inject constructor() {
    private val _daily = MutableLiveData<String>(Constant.BANNER_URL)
    val dailyUrl: LiveData<String> = _daily

    fun setDailyUrl(url: String) {
        _daily.value = url
    }
}