package com.lx.eye_wan.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lx.eye_wan.R
import com.lx.eye_wan.bean.HomeArticle
import com.lx.eye_wan.databinding.WanItemMainListBinding

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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.wan_item_main_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = getItem(position)
        holder.binding.viewModel = article
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding: WanItemMainListBinding = DataBindingUtil.bind(itemView)!!
    }
}