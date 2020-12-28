package com.eneskayiklik.demofeed.data.network

import com.eneskayiklik.demofeed.data.model.feature.Feature
import com.eneskayiklik.demofeed.data.model.timeline.Timeline
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {
    @GET("featured")
    suspend fun getFeature(): Feature

    @GET("timeline")
    suspend fun getTimeline(
        @Query("page") page: Int = 1
    ): Timeline
}