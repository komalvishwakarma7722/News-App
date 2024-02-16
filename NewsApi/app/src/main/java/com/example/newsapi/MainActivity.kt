package com.example.newsapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapi.adapter.ArticlesAdapter
import com.example.newsapi.adapter.ContentAdapter
import com.example.newsapi.databinding.ActivityMainBinding
import com.example.newsapi.model.Articles
import com.example.newsapi.model.Country
import com.example.newsapi.model.News
import com.example.newsapi.model.content
import com.example.newsapi.network.ApiNews
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var contentList = mutableListOf<content>()
    private var newsList = mutableListOf<Articles>()
    private var countryList = mutableListOf<Country>()
    private lateinit var newsAdapter: ArticlesAdapter
    private lateinit var contentAdapter: ContentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        newsAdapter = ArticlesAdapter(this, newsList)
        binding.recycleview1.layoutManager = LinearLayoutManager(this)
        binding.recycleview1.adapter = newsAdapter


        contentAdapter = ContentAdapter(this, contentList)
        binding.recycle.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recycle.adapter = contentAdapter

        getNews()
        PreparData1()
    }

    private fun getNews() {
        countryData()

        var countryAdapter = ArrayAdapter(
            applicationContext,
            android.R.layout.simple_spinner_dropdown_item,
            countryList.map { it.countryname }
        )
        binding.autoCountry.setAdapter(countryAdapter)

        binding.autoCountry.onItemClickListener = object :
            AdapterView.OnItemClickListener {
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedCountryCode = countryList[position].countrycode

                loadcountry(selectedCountryCode)
            }

        }
    }

    private fun loadcountry(countryCode: String) {
        ApiNews.init().getData(countryCode).enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                if (response.isSuccessful) {
                    val newsCountry = response.body()?.articles
                    newsCountry?.let {
                        newsAdapter.articleList = it
                        newsAdapter.notifyDataSetChanged()
                        contentAdapter.itemContentClickListener={ position, content ->
                            getCategoryData(countryCode,content)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("TAG", "onFailure: ")
            }


        })
    }

    private fun getCategoryData(countryCode: String,category:content){
        ApiNews.init().getCategoryData(countryCode,category.category).enqueue(object :Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
                if (response.isSuccessful){
                    val newsCountry = response.body()?.articles
                    newsCountry?.let {
                        newsAdapter.articleList = it
                        newsAdapter.notifyDataSetChanged()
                    }
                }
            }
            override fun onFailure(call: Call<News>, t: Throwable) {
            }

        })
    }


    private fun PreparData1() {
        contentList.add(content(id = 1, category = "Bussiness"))
        contentList.add(content(id = 2, category = "Entertainment"))
        contentList.add(content(id = 3, category = "General"))
        contentList.add(content(id = 4, category = "Health"))
        contentList.add(content(id = 5, category = "Science"))
        contentList.add(content(id = 6, category = "Sport"))
        contentList.add(content(id = 7, category = "Technology"))
    }


    private fun countryData() {
        countryList.add(Country(1, countryname = "India", countrycode = "in"))
        countryList.add(Country(2, countryname = " Australia", countrycode = "Au"))
        countryList.add(Country(3, countryname = " China", countrycode = "Cn"))
        countryList.add(Country(4, countryname = "France", countrycode = "fr"))
        countryList.add(Country(5, countryname = "Hong Kong", countrycode = "hk"))
        countryList.add(Country(6, countryname = "Switzerland", countrycode = "ch"))
        countryList.add(Country(7, countryname = "Saudi Arabia", countrycode = "sa"))
        countryList.add(Country(8, countryname = " Philippines", countrycode = "ph"))
        countryList.add(Country(9, countryname = " Russia", countrycode = "ru"))
        countryList.add(Country(10, countryname = "Germany", countrycode = "de"))
    }
}


