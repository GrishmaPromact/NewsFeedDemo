package com.example.feeddemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.feeddemo.R
import com.example.feeddemo.data.model.FeedModel
import com.example.feeddemo.databinding.ItemNewsFeedBinding

class NewsFeedAdapter(private val rowsList: ArrayList<FeedModel.Row>) :
    RecyclerView.Adapter<NewsFeedAdapter.NewsFeedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsFeedViewHolder {
        val binding = ItemNewsFeedBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsFeedViewHolder(binding)
    }

    override fun getItemCount() = rowsList.size

    override fun onBindViewHolder(holder: NewsFeedViewHolder, position: Int) {
        holder.bind(rowsList[position])
    }

    inner class NewsFeedViewHolder(val binding: ItemNewsFeedBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: FeedModel.Row) {

            binding.tvTitle.text = data.title
            binding.tvDesc.text = data.description

            Glide.with(binding.ivNewsFeed.context)
                .load(data.imageHref)
                .placeholder(R.drawable.user_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.ivNewsFeed)
        }
    }


    fun addData(list: List<FeedModel.Row>) {
        rowsList.addAll(list)
    }

    fun clearList() {
        rowsList.clear()
        notifyDataSetChanged()
    }
}