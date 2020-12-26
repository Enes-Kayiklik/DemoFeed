package com.eneskayiklik.demofeed.ui.feed.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.eneskayiklik.demofeed.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedFragment : Fragment(R.layout.fragment_feed) {
    private val feedViewModel by viewModels<FeedViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        feedViewModel.feature.observe(this.viewLifecycleOwner, Observer { feature ->
            Log.e("Feed", "$feature")
        })

        feedViewModel.timeline.observe(this.viewLifecycleOwner, Observer { timeline ->
            Log.e("Timeline", "$timeline")
        })
    }
}