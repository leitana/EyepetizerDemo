package com.lx.eye_home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.lx.common.mvvm.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @title：HomeViewModel
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2022/12/12
 */
@HiltViewModel
class HomeViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle): BaseViewModel() {
    private val HOME_PAGE_INDEX = "home_page_index"
    private val mLiveData = MutableLiveData<Int>()

    fun getSelect(): LiveData<Int>{
        if (mLiveData.value == null) {
            val index = savedStateHandle.get<Int>(HOME_PAGE_INDEX) ?: 0
            mLiveData.postValue(index)
        }
        return mLiveData
    }

    fun saveSelect(selectIndex: Int){
        savedStateHandle[HOME_PAGE_INDEX] = selectIndex
    }
}