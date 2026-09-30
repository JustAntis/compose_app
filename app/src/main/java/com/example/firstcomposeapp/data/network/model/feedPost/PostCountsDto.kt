package com.example.firstcomposeapp.data.network.model.feedPost

data class PostCountsDto(
    val comments: Int,
    val upvotes: Int,
    val downvotes: Int,
    val score: Int
)
