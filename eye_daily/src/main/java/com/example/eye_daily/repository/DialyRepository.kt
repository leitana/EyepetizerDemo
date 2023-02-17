package com.example.eye_daily.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.eye_daily.model.ProviderMultiModel
import com.example.eye_daily.pagingSource.BannerPagingSourceFactory
import com.example.eye_daily.pagingSource.DailyPagingSourceFactory
import com.lx.common.model.Item
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.concatWith
import kotlinx.coroutines.flow.conflate
import javax.inject.Inject

/**
 * @title：DialyRepository
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2023/02/14
 */
class DialyRepository @Inject constructor() {
    private val PAGE_SIZE = 20
    @Inject
    lateinit var bannerPagingSourceFactory: BannerPagingSourceFactory

    @Inject
    lateinit var dialyPagingSourceFactory: DailyPagingSourceFactory

    val bannerPagingData = Pager(
        config = PagingConfig(10),
        pagingSourceFactory = { bannerPagingSourceFactory.create() }).flow

    val dailyPagingData = Pager(
        config = PagingConfig(20),
        pagingSourceFactory = { dialyPagingSourceFactory.create() }).flow

    fun getDailyList(): Flow<PagingData<ProviderMultiModel>>{
        return Pager(config = PagingConfig(PAGE_SIZE),
            pagingSourceFactory = { dialyPagingSourceFactory.create() }).flow
    }
}