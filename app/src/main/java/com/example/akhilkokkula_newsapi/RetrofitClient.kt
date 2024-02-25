package com.example.akhilkokkula_newsapi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: NewsApiInterface by lazy {
        retrofit.create(NewsApiInterface::class.java)
    }
}
