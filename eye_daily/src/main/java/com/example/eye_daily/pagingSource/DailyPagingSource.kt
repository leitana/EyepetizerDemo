package com.example.eye_daily.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.eye_daily.api.DailyApi
import com.example.eye_daily.constant.Constant
import com.example.eye_daily.di.DailyUrlManage
import com.example.eye_daily.model.ProviderMultiModel
import com.lx.common.model.Item

/**
 * @title：DailyPagingSource
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2023/02/15
 */
class DailyPagingSource(val dailyApi: DailyApi, val dailyUrlManager: DailyUrlManage): PagingSource<String, ProviderMultiModel>() {
    private val BANNER_TYPE = "banner2"
    private val TEXT_HEADER_TYPE = "textHeader"
    override fun getRefreshKey(state: PagingState<String, ProviderMultiModel>): String? = null

    override suspend fun load(params: LoadParams<String>): LoadResult<String, ProviderMultiModel> {
        return try {
            val url = dailyUrlManager.dailyUrl.value
            val dataList = dailyApi.getDailyList(url)
            val providerMultiModels = mutableListOf<ProviderMultiModel>()
            val repoItems = dataList.issueList[0].itemList
            repoItems.removeAll {
                it.type == BANNER_TYPE
            }
            if (url == Constant.BANNER_URL) {
                providerMultiModels.add(ProviderMultiModel(type = ProviderMultiModel.Type.TYPE_BANNER, items = repoItems))
            } else {
                repoItems.forEach {
                    if (it.type == TEXT_HEADER_TYPE) {
                        providerMultiModels.add(
                            ProviderMultiModel(
                                type = ProviderMultiModel.Type.TYPE_TITLE,
                                item = it
                            )
                        )
                    } else {
                        providerMultiModels.add(
                            ProviderMultiModel(
                                type = ProviderMultiModel.Type.TYPE_IMAGE,
                                item = it
                            )
                        )
                    }
                }
            }
//            val repoItems = dataList.issueList[0].itemList
            val prevKey = null
            val nextKey = if (repoItems.isNotEmpty() && dataList.nextPageUrl.isNotEmpty()) dataList.nextPageUrl else null
            LoadResult.Page(providerMultiModels, prevKey, nextKey)
        } catch(e: Exception) {
            LoadResult.Error(e)
        }
    }
}