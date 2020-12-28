package com.eneskayiklik.demofeed.ui.feed.fragment

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.eneskayiklik.demofeed.data.TimelinePagingSource
import com.eneskayiklik.demofeed.data.model.feature.Feature
import com.eneskayiklik.demofeed.data.network.RetrofitApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TimelineViewModel @ViewModelInject constructor(
    private var retrofit: RetrofitApi
) : ViewModel() {
    private var _feature = MutableLiveData<Feature>()
    val feature: LiveData<Feature>
        get() = _feature

    val timeline = getTimelineData().cachedIn(viewModelScope)

    init {
        getFeatureData()
    }

    private fun getFeatureData() {
        viewModelScope.launch(Dispatchers.IO) {
            _feature.postValue(retrofit.getFeature())
        }
    }

    private fun getTimelineData() =
        Pager(
            config = PagingConfig(pageSize = 4, maxSize = 100, enablePlaceholders = false),
            pagingSourceFactory = { TimelinePagingSource(retrofit) }
        ).liveData
}