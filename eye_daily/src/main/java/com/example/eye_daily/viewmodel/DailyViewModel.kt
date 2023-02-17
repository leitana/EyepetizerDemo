package com.example.eye_daily.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.eye_daily.constant.Constant
import com.example.eye_daily.di.DailyUrlManage
import com.example.eye_daily.model.ProviderMultiModel
import com.example.eye_daily.repository.DialyRepository
import com.lx.common.model.Item
import com.lx.common.mvvm.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @title：DailyViewModel
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2023/02/14
 */
@HiltViewModel
class DailyViewModel @Inject constructor(private val repository: DialyRepository): BaseViewModel() {
    @Inject
    lateinit var dailyUrlManage: DailyUrlManage
    fun getDailyList(): Flow<PagingData<ProviderMultiModel>>{
        return repository.getDailyList().cachedIn(viewModelScope)
    }

    fun initDailyUrlManage() {
        dailyUrlManage.setDailyUrl(Constant.BANNER_URL)
    }
}