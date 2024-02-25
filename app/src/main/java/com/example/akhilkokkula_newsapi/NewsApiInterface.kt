package com.example.akhilkokkula_newsapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiInterface{
    @GET("top-headlines?country=us&apiKey=538dffa48b8b45b09f72e60dcd75834b")
    fun getArticlesByCategory(
        @Query("category") category: String
    ): Call<NewsData>
}
