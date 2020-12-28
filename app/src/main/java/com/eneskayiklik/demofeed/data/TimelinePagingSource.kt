package com.eneskayiklik.demofeed.data

import androidx.paging.PagingSource
import com.eneskayiklik.demofeed.data.model.timeline.TimelineX
import com.eneskayiklik.demofeed.data.network.RetrofitApi

private const val FIRST_PAGE = 1

class TimelinePagingSource(
    private val timelineApi: RetrofitApi
) : PagingSource<Int, TimelineX>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TimelineX> {
        val position = params.key ?: FIRST_PAGE
        val photos = timelineApi.getTimeline(position).timeline

        return try {
            LoadResult.Page(
                data = photos,
                prevKey = if (position == FIRST_PAGE) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}