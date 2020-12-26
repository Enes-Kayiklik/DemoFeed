package com.eneskayiklik.demofeed.data.model.timeline

import com.google.gson.annotations.SerializedName

data class Mention(
    @SerializedName("fullname")
    val fullName: String,
    val id: String,
    val isFollowing: Boolean,
    val profileImage: String,
    val userName: String
)