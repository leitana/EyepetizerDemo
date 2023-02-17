package com.lx.eye_home

import com.lx.eye_home.model.HomeArticle
import retrofit2.http.GET

/**
 * @title：HomeApi
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2022/12/21
 */
interface HomeApi {
    /**
     * 获取首页置顶文章列表
     * http://www.wanandroid.com/article/top/json
     */
    @GET("article/top/json")
    suspend fun getTopArticles(): ApiResponse<MutableList<HomeArticle.DatasBean>>
}