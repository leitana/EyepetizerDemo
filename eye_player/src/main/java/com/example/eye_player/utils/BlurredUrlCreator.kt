package com.example.eye_player.utils

import com.blankj.utilcode.util.ScreenUtils
import com.blankj.utilcode.util.SizeUtils.dp2px


object BlurredUrlCreator {

    fun buildBlurredUrl(blurred: String): String {
        val width: Int = ScreenUtils.getScreenWidth()
        val height: Int = ScreenUtils.getScreenHeight() - dp2px(250f)
        return "${blurred}/thumbnail/${height}x${width}"
    }
}