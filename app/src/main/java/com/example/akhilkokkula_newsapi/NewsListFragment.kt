package com.example.akhilkokkula_newsapi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.akhilkokkula_newsapi.databinding.FragmentNewsListBinding

private const val TAG = "NewsListFragment"

class NewsListFragment : Fragment(), NewsListAdapter.OnArticleClickListener {

    private var _binding: FragmentNewsListBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val newsListViewModel: NewsListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsListBinding.inflate(inflater, container, false)

        binding.newsRecyclerView.layoutManager = LinearLayoutManager(context)

        val newsListAdapter = NewsListAdapter(emptyList(), this)
        binding.newsRecyclerView.adapter = newsListAdapter

        val categorySpinner = binding.categorySpinner
        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                println(adapterView?.getItemAtPosition(position).toString())
                val category = adapterView?.getItemAtPosition(position).toString()
                newsListViewModel.getArticlesByCategory(category)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        //updating news
        newsListViewModel.newsList.observe(viewLifecycleOwner) { newsList ->
            newsListAdapter.updatingNewsList(newsList)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onArticleClick(article: Article) {
        val bundle = Bundle().apply {
            putString("title", article.title)
            putString("source", article.source.name)
            putString("author", article.author)
            putString("publishDate", article.publishedAt)
            putString("imageUrl", article.urlToImage)
            putString("description", article.description)
            putString("content", article.content)
        }

        findNavController().navigate(
            R.id.action_newsListFragment_to_fragmentArticleDetail,
            bundle
        )
    }
}