package com.amnah.ali.players.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amnah.ali.players.R
import com.amnah.ali.players.data.Players
import com.amnah.ali.players.databinding.LayoutHeaderItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class HeaderAdapter (private val context: Context, private val list: List<Players>) :
    RecyclerView.Adapter<HeaderAdapter.ViewHolder>() {
    class ViewHolder(val binding: LayoutHeaderItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: LayoutHeaderItemBinding =
            LayoutHeaderItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentPost = list[position]
        holder.binding.apply {
            Glide.with(context)
                .load(currentPost.imgProfile).error(R.drawable.ic_error)
                .apply(RequestOptions.circleCropTransform())
                .into(players)

        }
    }
    override fun getItemCount() = list.size

}