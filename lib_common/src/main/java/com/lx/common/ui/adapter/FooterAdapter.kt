package com.lx.common.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lx.common.R
import com.lx.common.databinding.CommonFooterItemBinding

/**
 * @title：FooterAdapter
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2023/01/12
 */
class FooterAdapter(val retry: () -> Unit): LoadStateAdapter<FooterAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val binding: CommonFooterItemBinding = DataBindingUtil.bind(itemView)!!
    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.binding.run {
            progressBar.isVisible = loadState is LoadState.Loading
            retryButton.isVisible = loadState is LoadState.Error
            retryButton.setOnClickListener {
                retry()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.common_footer_item, parent, false)
        return ViewHolder(view)
    }
}