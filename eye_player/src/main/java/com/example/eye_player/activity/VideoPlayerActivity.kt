package com.example.eye_player.activity

import com.example.eye_player.viewmodel.VideoPlayerViewModel
import com.lx.common.mvvm.activity.BaseBindVMActivity
import com.lx.eye_player.R
import com.lx.eye_player.databinding.PlayerActivityVideoBinding

/**
 * @title：VideoPlayerActivity
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2023/02/20
 */
class VideoPlayerActivity: BaseBindVMActivity<VideoPlayerViewModel, PlayerActivityVideoBinding>() {
    override val getLayoutRes: Int
        get() = R.layout.player_activity_video

    override fun initView() {
    }

    override fun initData() {
    }

    override fun startObserve() {
    }
}