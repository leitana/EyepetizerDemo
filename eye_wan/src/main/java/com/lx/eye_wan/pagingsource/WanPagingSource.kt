package com.lx.eye_wan.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lx.eye_wan.bean.HomeArticle
import com.lx.eye_wan.net.HomeApi
import com.lx.eye_wan.net.apiData
import javax.inject.Inject

class WanPagingSource @Inject constructor(private val homeApi: HomeApi): PagingSource<Int, HomeArticle.DatasBean>() {
    override fun getRefreshKey(state: PagingState<Int, HomeArticle.DatasBean>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HomeArticle.DatasBean> {
        return try {
            val page = params.key ?: 1
            val pageSize = params.loadSize
            val dataList = homeApi.getTopArticles()
            val repoItems = dataList.apiData()
            val prevKey = if (page > 1) page - 1 else null
            val nextKey = if (repoItems.isNotEmpty()) page + 1 else null
            LoadResult.Page(repoItems, prevKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}