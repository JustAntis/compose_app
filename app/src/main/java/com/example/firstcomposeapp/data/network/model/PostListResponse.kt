package com.example.firstcomposeapp.data.network.model

import com.google.gson.annotations.SerializedName

data class PostListResponse(
    @SerializedName("next_page")
    val nextPage: String?,
    val posts: List<PostViewDto>
)
