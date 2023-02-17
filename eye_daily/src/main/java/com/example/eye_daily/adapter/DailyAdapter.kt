package com.example.eye_daily.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.eye_daily.model.DailyModel
import com.example.eye_daily.model.ProviderMultiModel
import com.lx.eye_daily.R
import com.lx.eye_daily.databinding.DailyItemBannerBinding
import com.lx.eye_daily.databinding.DailyItemHeadTextBinding
import com.lx.eye_daily.databinding.DailyItemImageTextBinding
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.indicator.CircleIndicator

/**
 * @title：DailyAdapter
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2023/02/14
 */
class DailyAdapter(val activity: Activity, val owner: LifecycleOwner): PagingDataAdapter<ProviderMultiModel, RecyclerView.ViewHolder>(COMPARATOR) {

    companion object{
        private val COMPARATOR = object : DiffUtil.ItemCallback<ProviderMultiModel>(){
            override fun areItemsTheSame(
                oldItem: ProviderMultiModel,
                newItem: ProviderMultiModel
            ): Boolean {
                return oldItem == newItem
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(
                oldItem: ProviderMultiModel,
                newItem: ProviderMultiModel
            ): Boolean {
                return oldItem == newItem
            }

        }
    }


    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return item?.type ?: -1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when(holder) {
            is BannerViewHolder -> {
                holder.binding.model = item
                holder.binding.owner = owner
                holder.binding.activity = activity

                holder.binding.banner.apply {
                    setAdapter(BannerImageAdapter(this.context, item?.items!!))
                    addBannerLifecycleObserver(owner)
                    indicator = CircleIndicator(this.context)
//                    setOnBannerListener { _, position ->
//                        go2VideoPlayerActivity(
//                            activity,
//                            null,
//                            item.items[position].data,
//                            true
//                        )
//                    }
                }
            }
            is HeaderTextViewHolder -> {
                holder.binding.model = item
            }

            is ImageTextViewHolder -> {
                holder.binding.model = item
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType){
            ProviderMultiModel.Type.TYPE_BANNER -> {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.daily_item_banner, parent, false)
                return BannerViewHolder(view)
            }
            ProviderMultiModel.Type.TYPE_TITLE -> {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.daily_item_head_text, parent, false)
                return HeaderTextViewHolder(view)
            }
            ProviderMultiModel.Type.TYPE_IMAGE -> {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.daily_item_image_text, parent, false)
                return ImageTextViewHolder(view)
            }
            else -> {
                return EmptyViewHolder(View(parent.context))
            }
        }
    }

    class EmptyViewHolder(view: View) : RecyclerView.ViewHolder(view)

    class BannerViewHolder(item: View): RecyclerView.ViewHolder(item){
         val binding: DailyItemBannerBinding = DataBindingUtil.bind(item)!!
    }

    class HeaderTextViewHolder(item: View): RecyclerView.ViewHolder(item){
        val binding: DailyItemHeadTextBinding = DataBindingUtil.bind(item)!!
    }

    class ImageTextViewHolder(item: View): RecyclerView.ViewHolder(item){
        val binding: DailyItemImageTextBinding = DataBindingUtil.bind(item)!!
    }

}