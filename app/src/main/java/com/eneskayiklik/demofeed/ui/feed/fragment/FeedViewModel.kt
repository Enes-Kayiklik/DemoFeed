package com.eneskayiklik.demofeed.ui.feed.fragment

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eneskayiklik.demofeed.data.model.feature.Feature
import com.eneskayiklik.demofeed.data.model.timeline.Timeline
import com.eneskayiklik.demofeed.data.network.RetrofitApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FeedViewModel @ViewModelInject constructor(
    private var retrofit: RetrofitApi
) : ViewModel() {
    private var _feature = MutableLiveData<Feature>()
    val feature: LiveData<Feature>
        get() = _feature

    private var _timeline = MutableLiveData<Timeline>()
    val timeline: LiveData<Timeline>
        get() = _timeline

    init {
        getFeatureData()
        getTimelineData()
    }

    private fun getFeatureData() {
        viewModelScope.launch(Dispatchers.IO) {
            _feature.postValue(retrofit.getFeature())
        }
    }

    private fun getTimelineData() {
        viewModelScope.launch(Dispatchers.IO) {
            _timeline.postValue(retrofit.getTimeline())
        }
    }
}