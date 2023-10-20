package com.example.lab_5r


import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsApiClient {
    private val baseUrl = "https://newsapi.org/v2/"
    private val apiKey = "1453fb5bac004551a6bf519431e813f4"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val apiService: NewsApiService by lazy {
        retrofit.create(NewsApiService::class.java)
    }

    fun getTopHeadlines(): Call<NewsResponse> {
        return apiService.getTopHeadlines("us", apiKey)
    }
}
