package com.example.newsapi

import com.example.newsapi.model.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines?apiKey=437e32de5a6d41bcaceb3361cab60946")
    fun getData(
       @Query ("country") countryCode: String,
    ): Call<News>

    @GET("top-headlines?apiKey=437e32de5a6d41bcaceb3361cab60946")
    fun getCategoryData(
        @Query ("country") countryCode: String,
        @Query ("category") category: String,
    ): Call<News>

}