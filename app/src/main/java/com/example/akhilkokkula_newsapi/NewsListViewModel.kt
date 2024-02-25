package com.example.akhilkokkula_newsapi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "NewsListViewModel"

class NewsListViewModel : ViewModel() {

    private val _newsList = MutableLiveData<List<Article>>()
    val newsList: LiveData<List<Article>> = _newsList

    init {
        viewModelScope.launch {
            val api = RetrofitClient.api
            getArticles(api, "Business")?.let { articles ->
                _newsList.value = articles
            }
        }
    }

    fun getArticlesByCategory(category: String) {
        viewModelScope.launch {
            val api = RetrofitClient.api
            getArticles(api, category)?.let { articles ->
                _newsList.value = articles
            }
        }
    }

    private suspend fun getArticles(api: NewsApiInterface, category: String = ""): List<Article>? {
        return withContext(Dispatchers.IO) {
            val res = api.getArticlesByCategory(category).execute()
            if (res.isSuccessful) {
                res.body()?.articles
            } else {
                null
            }
        }
    }
}

