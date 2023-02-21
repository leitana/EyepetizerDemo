package com.example.eye_player.repository

import com.example.eye_player.adapter.VideoRelateAdapter
import com.example.eye_player.api.VideoPlayerApi
import com.lx.common.model.Item
import javax.inject.Inject

/**
 * @title：VideoPlayerRepository
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2023/02/21
 */
class VideoPlayerRepository @Inject constructor(private val mVideoPlayerApi: VideoPlayerApi) {

    private val VIDEO_SMALL_CARD_TYPE = "videoSmallCard"

    suspend fun getRelateVideoList(id: Int): List<Item>{
        val issue = mVideoPlayerApi.getRelateVideoList(id)
        issue.itemList.forEach {
            if (it.type == VIDEO_SMALL_CARD_TYPE) {
                it.itemType = VideoRelateAdapter.TYPE_VIDEO
            } else {
                it.itemType = VideoRelateAdapter.TYPE_TITLE
            }
        }
        return issue.itemList
    }
}