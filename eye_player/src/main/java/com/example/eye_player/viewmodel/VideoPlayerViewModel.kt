package com.example.eye_player.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.eye_player.repository.VideoPlayerRepository
import com.lx.common.model.Data
import com.lx.common.model.Item
import com.lx.common.mvvm.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @title：VideoPlayerViewModel
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2023/02/21
 */
@HiltViewModel
class VideoPlayerViewModel @Inject constructor(

    private val videoPlayerRepository: VideoPlayerRepository): BaseViewModel() {
    private val _relateVideoList = MutableLiveData<List<Item>>()
    val relateVideoList: LiveData<List<Item>> = _relateVideoList

    var videoModelList: MutableList<Data> = mutableListOf()

    fun getRelateVideoList(id: Int): LiveData<List<Item>> = flowEx {
        videoPlayerRepository.getRelateVideoList(id)
    }

    //移除最新的，并返回当前最前的数据
    fun removeVideoModel(): Data?{
        return if (videoModelList.isNotEmpty()) {
            videoModelList.removeAt(videoModelList.lastIndex)
            if (videoModelList.isNotEmpty()) {
                videoModelList[videoModelList.lastIndex]
            } else {
                null
            }
        } else {
            null
        }
    }
}