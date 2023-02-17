package com.example.eye_daily.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.eye_daily.api.DailyApi
import com.example.eye_daily.di.DailyUrlManage
import com.lx.common.model.Item

/**
 * @title：BannerPagingSource
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2023/02/16
 */
class BannerPagingSource(val dailyApi: DailyApi): PagingSource<String, Item>() {
    override fun getRefreshKey(state: PagingState<String, Item>): String? = null

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Item> {
        return try {
            val dataList = dailyApi.getDailyBanner("")
            val repoItems = dataList.issueList[0].itemList
            val prevKey = null
            val nextKey = if (repoItems.isNotEmpty() && !dataList.nextPageUrl.isNullOrEmpty()) dataList.nextPageUrl else null
            LoadResult.Page(repoItems, prevKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}