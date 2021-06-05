package com.amnah.ali.players.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amnah.ali.players.R
import com.amnah.ali.players.data.DataManager
import com.amnah.ali.players.data.Players
import com.amnah.ali.players.databinding.LayoutHeaderBinding
import com.amnah.ali.players.databinding.LayoutItemBinding
import com.amnah.ali.players.util.Constants
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MainAdapter(private val context: Context, private val list: List<Players>) :
    RecyclerView.Adapter<MainAdapter.BaseViewHolder<*>>() {
    //make base view holder
    abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView)
    inner class HeaderViewHolder(val binding: LayoutHeaderBinding) : BaseViewHolder<View>(binding.root)
    inner class ItemViewHolder(val binding: LayoutItemBinding) : BaseViewHolder<View>(binding.root)

    //to select the length of list
    override fun getItemCount() = list.size

    //to get item view type by using some condition
    override fun getItemViewType(position: Int) =
        if (position == 0) Constants.TYPE_HEADER else Constants.TYPE_ITEM

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            //to show horizontal recycler
            Constants.TYPE_HEADER -> {
                val view:LayoutHeaderBinding = LayoutHeaderBinding.inflate(LayoutInflater.from(context),parent,false)
                HeaderViewHolder(view)
            }
            //to show items of vertical recycler
            Constants.TYPE_ITEM -> {
                val view:LayoutItemBinding = LayoutItemBinding.inflate(LayoutInflater.from(context),parent,false)
                ItemViewHolder(view)
            }
            else -> {
                return super.createViewHolder(parent, viewType)
            }
        }

    }
    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        //get the values by position of it in a list
        val currentPost = list[position]
        when (holder) {
            is ItemViewHolder -> {
                holder.binding.apply {
                    Glide.with(context)
                        .load(currentPost.imgProfile).error(R.drawable.ic_error)
                        .apply(RequestOptions.fitCenterTransform())
                        .into(imageProfile)

                    name.text = currentPost.name
                    details.text = currentPost.details

                }
            }
            is HeaderViewHolder -> {
                val recentPurchaseAdapter = HeaderAdapter(context, DataManager.playerList)
                //initialization to horizontal recycler view
                val layout = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                holder.binding.rvHeader.apply {
                    layoutManager = layout
                    adapter = recentPurchaseAdapter
                }
            }
        }
    }
}