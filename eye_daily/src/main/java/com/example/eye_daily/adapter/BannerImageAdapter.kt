package com.example.eye_daily.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lx.common.model.Item
import com.lx.eye_daily.databinding.DailyItemBannerImageBinding
import com.youth.banner.adapter.BannerAdapter

class BannerImageAdapter(context: Context, datas: List<Item>) :
    BannerAdapter<Item, BannerImageAdapter.BannerViewHolder>(datas) {

    private val mInflater = LayoutInflater.from(context)

    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): BannerViewHolder {
        val binding =
            DailyItemBannerImageBinding.inflate(mInflater, parent, false)
        return BannerViewHolder(binding)
    }

    override fun onBindView(holder: BannerViewHolder, data: Item, position: Int, size: Int) {
        holder.binding.model = data
    }

    class BannerViewHolder(val binding: DailyItemBannerImageBinding) :
        RecyclerView.ViewHolder(binding.root)
}