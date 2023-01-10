package com.lx.eye_wan.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.lx.eye_wan.bean.HomeArticle
import com.lx.eye_wan.net.HomeApi
import com.lx.eye_wan.pagingsource.WanPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @title：Repository
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2023/01/05
 */

class Repository @Inject constructor() {
    private val PAGE_SIZE = 50
    @Inject
    lateinit var homeApi: HomeApi

    @Inject
    lateinit var wanPagingSource: WanPagingSource

    fun getArticles(): Flow<PagingData<HomeArticle.DatasBean>> {
        return Pager(
            config = PagingConfig(PAGE_SIZE),
            pagingSourceFactory = { wanPagingSource }
        ).flow
    }
}