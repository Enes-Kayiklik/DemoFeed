package com.eneskayiklik.demofeed.ui.feed.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.eneskayiklik.demofeed.R
import com.eneskayiklik.demofeed.databinding.FragmentFeedBinding
import com.eneskayiklik.demofeed.ui.feed.adapter.FeaturedAdapter
import com.eneskayiklik.demofeed.ui.feed.adapter.FeedAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedFragment : Fragment(R.layout.fragment_feed) {
    private val feedViewModel by viewModels<FeedViewModel>()
    private var _binding: FragmentFeedBinding? = null
    private val binding: FragmentFeedBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFeedBinding.inflate(inflater, container, false)
        setupObserver()
        return binding.root
    }

    private fun setupObserver() {
        feedViewModel.feature.observe(this.viewLifecycleOwner, Observer { feature ->
            binding.recyclerViewFeatured.apply {
                layoutManager =
                    LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
                adapter = FeaturedAdapter(feature.featured)
            }
        })

        feedViewModel.timeline.observe(this.viewLifecycleOwner, Observer { timeline ->
            binding.recyclerViewFeed.apply {
                layoutManager =
                    LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
                adapter = FeedAdapter(timeline.timeline)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}