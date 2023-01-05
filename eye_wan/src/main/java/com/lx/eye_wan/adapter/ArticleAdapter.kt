package com.lx.eye_wan.adapter

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.lx.eye_wan.bean.HomeArticle

/**
 * @title：ArticleAdapter
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2023/01/05
 */
class ArticleAdapter: PagingDataAdapter<HomeArticle.DatasBean, ArticleAdapter.ViewHolder>(COMPARATOR) {
    companion object{
        private val COMPARATOR = object : DiffUtil.ItemCallback<HomeArticle.DatasBean>(){
            override fun areItemsTheSame(
                oldItem: HomeArticle.DatasBean,
                newItem: HomeArticle.DatasBean
            ): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(
                oldItem: HomeArticle.DatasBean,
                newItem: HomeArticle.DatasBean
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }
}