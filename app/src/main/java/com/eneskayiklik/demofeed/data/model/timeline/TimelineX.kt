package com.eneskayiklik.demofeed.data.model.timeline

data class TimelineX(
    val countryCount: Int,
    val date: String,
    val id: String,
    val imageUrl: String,
    val mentions: ArrayList<Mention>,
    val title: String
)