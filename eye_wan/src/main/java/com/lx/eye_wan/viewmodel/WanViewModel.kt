package com.lx.eye_wan.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lx.common.mvvm.viewmodel.BaseViewModel
import com.lx.eye_wan.bean.HomeArticle
import com.lx.eye_wan.repository.Repository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow

/**
 * @title：WanViewModel
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2023/01/05
 */
@ActivityRetainedScoped
class WanViewModel: BaseViewModel() {
    fun getArticleData(): Flow<PagingData<HomeArticle.DatasBean>> {
        return Repository.getArticles().cachedIn(viewModelScope)
    }
}