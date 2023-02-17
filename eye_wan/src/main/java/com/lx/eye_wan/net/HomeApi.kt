package com.lx.eye_wan.net

import com.lx.eye_wan.bean.HomeArticle
import retrofit2.http.GET
import retrofit2.http.Path

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

    /**
     * 获取文章列表
     * http://www.wanandroid.com/article/list/0/json
     * [pageNum]
     */
    @GET("article/list/{pageNum}/json")
    suspend fun getArticles(@Path("pageNum") pageNum: Int): ApiResponse<HomeArticle>
}