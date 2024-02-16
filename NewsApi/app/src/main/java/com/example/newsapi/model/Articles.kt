package com.example.newsapi.model

import com.google.gson.annotations.SerializedName

data class Articles(
    var source: Source,
    var author:String,
    var title:String,
    var description:String,
    var url:String,
    @SerializedName("urlToImage")
    var image: String,
    var publishedAt:String
)
