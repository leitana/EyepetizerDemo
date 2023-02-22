package com.lx.common.router

/**
 * @title：RouterPath
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2023/01/06
 */
object RouterPath {
     class Wan {
         companion object{
             const val PATH_WAN_FRAGMENT = "/wan/articalFragment"
         }
     }

    class Daily {
        companion object{
            const val PATH_DAILY_FRAGMENT = "/daily/dailyFragment"
        }
    }

    class Video {
        companion object{
            const val PATH_PLAYER_Activity = "/player/playerRelateActivity"
        }
    }
}