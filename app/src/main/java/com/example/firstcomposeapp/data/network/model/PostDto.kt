package com.example.firstcomposeapp.data.network.model

import com.google.gson.annotations.SerializedName

data class PostDto(
    val id: Int,
    val name: String,
    val body: String?,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String?,
    val published: String
)
