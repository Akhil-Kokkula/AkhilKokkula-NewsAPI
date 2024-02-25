package com.example.akhilkokkula_newsapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.akhilkokkula_newsapi.databinding.ArticleItemBinding

private const val TAG = "NewsListAdapter"

class NewsHolder (
    private val binding: ArticleItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(article: Article) {
        binding.newsTitle.text = article.title
        binding.authorInfo.text = article.author
        binding.dateInfo.text = article.publishedAt
    }
}

class NewsListAdapter(
    private var newsList: List<Article>,
    private val clickListener: OnArticleClickListener
) : RecyclerView.Adapter<NewsHolder>() {

    interface OnArticleClickListener {
        fun onArticleClick(article: Article)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ArticleItemBinding.inflate(inflater, parent, false)
        return NewsHolder(binding)
    }

    override fun onBindViewHolder(newsHolder: NewsHolder, position: Int) {
        val article = newsList[position]
        newsHolder.bind(article)
        newsHolder.itemView.setOnClickListener {
            clickListener.onArticleClick(article)
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun updatingNewsList(updatedNewsList: List<Article>) {
        newsList = updatedNewsList
        notifyDataSetChanged()
    }
}

