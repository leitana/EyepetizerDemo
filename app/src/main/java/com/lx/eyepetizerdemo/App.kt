package com.lx.eyepetizerdemo

import com.lx.common.BaseApplication
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import dagger.hilt.android.HiltAndroidApp

/**
 * @title：App
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2022/11/29
 */
@HiltAndroidApp
class App: BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        SmartRefreshLayout.setDefaultRefreshInitializer{ context, layout ->
            layout.setEnableLoadMore(true)
            layout.setEnableLoadMoreWhenContentNotFull(true)
        }
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            layout.setEnableHeaderTranslationContent(true)
            MaterialHeader(context).setColorSchemeResources(com.lx.lib_base.R.color.blue,
                com.lx.lib_base.R.color.blue, com.lx.lib_base.R.color.blue)
        }

//        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
//            layout.setEnableFooterFollowWhenNoMoreData(true)
//            layout.setEnableFooterTranslationContent(true)
//            layout.setFooterHeight(153f)
//            layout.setFooterTriggerRate(0.6f)
//            NoStatusFooter.REFRESH_FOOTER_NOTHING = GlobalUtil.getString(R.string.footer_not_more)
//            NoStatusFooter(context).apply {
//                setAccentColorId(R.color.colorTextPrimary)
//                setTextTitleSize(16f)
//            }
//        }
    }
}