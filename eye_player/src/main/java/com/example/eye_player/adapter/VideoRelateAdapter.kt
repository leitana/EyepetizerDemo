package com.example.eye_player.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseMultiItemAdapter
import com.lx.common.model.Item
import com.lx.eye_player.databinding.PlayerItemRelateTitleBinding
import com.lx.eye_player.databinding.PlayerItemRelateVideoBinding

/**
 * @title：VideoRelateAdapter
 * @projectName EyepetizerDemo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2023/02/21
 */
class VideoRelateAdapter: BaseMultiItemAdapter<Item>() {
    companion object{
        const val TYPE_TITLE = 0
        const val TYPE_VIDEO = 1
    }

    class TitleItem(val viewBinding: PlayerItemRelateTitleBinding): RecyclerView.ViewHolder(viewBinding.root)

    class VideoItem(val viewBinding: PlayerItemRelateVideoBinding): RecyclerView.ViewHolder(viewBinding.root)

    init {
        addItemType(TYPE_TITLE, object : OnMultiItemAdapterListener<Item, TitleItem>{
            //创建 viewholder
            override fun onCreate(context: Context, parent: ViewGroup, viewType: Int): TitleItem {
                val viewBinding = PlayerItemRelateTitleBinding.inflate(LayoutInflater.from(context), parent, false)
                return TitleItem(viewBinding)
            }

            override fun onBind(holder: TitleItem, position: Int, item: Item?) {
                //绑定item 数据
                holder.viewBinding.model = item
            }
        }).addItemType(TYPE_VIDEO, object : OnMultiItemAdapterListener<Item, VideoItem>{
            //创建 viewholder
            override fun onCreate(context: Context, parent: ViewGroup, viewType: Int): VideoItem {
                val viewBinding = PlayerItemRelateVideoBinding.inflate(LayoutInflater.from(context), parent, false)
                return VideoItem(viewBinding)
            }

            override fun onBind(holder: VideoItem, position: Int, item: Item?) {
                //绑定item 数据
                holder.viewBinding.model = item?.data
            }

        }).onItemViewType { position, list->
            if (list[position].itemType == TYPE_TITLE) {
                TYPE_TITLE
            } else {
                TYPE_VIDEO
            }
        }
    }
}