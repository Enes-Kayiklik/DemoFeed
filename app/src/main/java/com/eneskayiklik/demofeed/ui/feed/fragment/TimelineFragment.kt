package com.eneskayiklik.demofeed.ui.feed.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.eneskayiklik.demofeed.R
import com.eneskayiklik.demofeed.databinding.FragmentFeedBinding
import com.eneskayiklik.demofeed.databinding.MentionSheetBinding
import com.eneskayiklik.demofeed.ui.feed.adapter.FeaturedAdapter
import com.eneskayiklik.demofeed.ui.feed.adapter.MentionAdapter
import com.eneskayiklik.demofeed.ui.feed.adapter.TimelineAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TimelineFragment : Fragment(R.layout.fragment_feed) {
    private val feedViewModel by viewModels<TimelineViewModel>()
    private var _binding: FragmentFeedBinding? = null
    private val binding: FragmentFeedBinding
        get() = _binding!!
    private var mentionBinding: MentionSheetBinding? = null
    private val timelineAdapter = TimelineAdapter {
        mentionBinding?.let { bind ->
            bind.recyclerViewMention.adapter = MentionAdapter(it)
            bottomSheet.show()
        }
    }

    private lateinit var bottomSheet: BottomSheetDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFeedBinding.inflate(inflater, container, false)
        mentionBinding = MentionSheetBinding.inflate(inflater)
        setupTimelineRecyclerView()
        setupBottomSheet()
        setupObserver()
        return binding.root
    }

    private fun setupBottomSheet() {
        bottomSheet = BottomSheetDialog(this@TimelineFragment.requireContext())
        bottomSheet.setContentView(mentionBinding!!.root)
    }

    private fun setupTimelineRecyclerView() {
        binding.recyclerViewFeed.apply {
            adapter = timelineAdapter
        }
    }

    private fun setupObserver() {
        feedViewModel.feature.observe(this.viewLifecycleOwner, Observer { feature ->
            binding.recyclerViewFeatured.apply {
                adapter = FeaturedAdapter(feature.featured)
            }
        })

        feedViewModel.timeline.observe(this.viewLifecycleOwner, Observer {
            timelineAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        mentionBinding = null
    }
}