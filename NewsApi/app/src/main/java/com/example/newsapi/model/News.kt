package com.example.newsapi.model

data class News(
    var status: String,
    var totalResults: Int,
    var articles: MutableList<Articles>
)
