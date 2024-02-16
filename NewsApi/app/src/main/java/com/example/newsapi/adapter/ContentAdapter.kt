package com.example.newsapi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapi.databinding.ContentLayoutBinding
import com.example.newsapi.databinding.ItemLayoutBinding
import com.example.newsapi.model.Articles
import com.example.newsapi.model.News
import com.example.newsapi.model.Source
import com.example.newsapi.model.content

class ContentAdapter(var context: Context, var newsList : MutableList<content>):
    RecyclerView.Adapter<ContentAdapter.MyViewHolder>() {
    class MyViewHolder(var binding: ContentLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    var itemContentClickListener: ((position: Int, content:content) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var binding = ContentLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var news = newsList[position]
        holder.binding.title.text=news.category

        holder.binding.cardview.setOnClickListener {
            itemContentClickListener?.invoke(position,news)
        }
    }


}