package com.lx.eye_wan.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lx.common.mvvm.viewmodel.BaseViewModel
import com.lx.eye_wan.bean.HomeArticle
import com.lx.eye_wan.repository.Repository
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @title：WanViewModel
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2023/01/05
 */
@ActivityRetainedScoped
class WanViewModel @Inject constructor(val repository: Repository): BaseViewModel() {
    fun getArticleData(): Flow<PagingData<HomeArticle.DatasBean>> {
        // cachedIn() 函数，
        // 这是用于将服务器返回的数据在 viewModelScope 这个作用域内进行缓存，
        // 假如手机横竖屏发生了旋转导致 Activity 重新创建，
        // Paging 3 就可以直接读取缓存中的数据，而不用重新发起网络请求了。
        return repository.getArticles().cachedIn(viewModelScope)
    }
}