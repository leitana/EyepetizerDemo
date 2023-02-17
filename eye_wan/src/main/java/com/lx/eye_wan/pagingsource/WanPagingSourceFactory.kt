package com.lx.eye_wan.pagingsource

import com.lx.eye_wan.net.HomeApi
import javax.inject.Inject

// An instance of PagingSource was re-used when Pager expected to create a new
// instance. Ensure that the pagingSourceFactory
class WanPagingSourceFactory @Inject constructor(private val homeApi: HomeApi) {
    fun create(): WanPagingSource {
        return WanPagingSource(homeApi)
    }
}
