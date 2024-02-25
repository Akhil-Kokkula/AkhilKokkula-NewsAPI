package com.example.akhilkokkula_newsapi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.akhilkokkula_newsapi.databinding.FragmentArticleDetailBinding

private const val TAG = "ArticleDetailFragment"

class ArticleDetailFragment : Fragment() {
    private var _binding: FragmentArticleDetailBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            FragmentArticleDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = arguments?.getString("title")
        val source = arguments?.getString("source")
        val author = arguments?.getString("author")
        val publishDate = arguments?.getString("publishDate")
        val imageUrl = arguments?.getString("imageUrl")
        val description = arguments?.getString("description")
        val content = arguments?.getString("content")

        binding.newsTitle.text = title ?: ""
        binding.sourceInfo.text = source ?: ""
        binding.authorInfo.text = author ?: ""
        binding.publishDate.text = publishDate ?: ""
        imageUrl?.let {
            Glide.with(this)
                .load(it)
                .placeholder(R.drawable.temp_img)
                .into(binding.newsImage)
        }
        binding.descriptionText.text = description ?: ""
        binding.contentText.text = content ?: ""
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}