package com.example.newsapi.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapi.R
import com.example.newsapi.databinding.ItemLayoutBinding
import com.example.newsapi.model.Articles
import com.squareup.picasso.Picasso

class ArticlesAdapter(var context: Context, var articleList : MutableList<Articles>):
    RecyclerView.Adapter<ArticlesAdapter.MyViewHolder>() {
    class MyViewHolder(var binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var binding = ItemLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var article = articleList[position]
        Picasso.get().load(article.image).into(holder.binding.image)
        holder.binding.title.text = article.title
        holder.binding.desc.text = article.description
        holder.binding.url.text = article.url


        holder.binding.url.setOnClickListener {
            openUrlInBrowser(article.url)
        }

    }
    private fun openUrlInBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }
}