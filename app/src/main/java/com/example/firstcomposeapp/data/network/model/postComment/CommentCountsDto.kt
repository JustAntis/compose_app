package com.example.firstcomposeapp.data.network.model.postComment

import com.google.gson.annotations.SerializedName

data class CommentCountsDto(
    val score: Int,
    @SerializedName("upvotes")
    val upVotes: Int,
    @SerializedName("downvotes")
    val downVotes: Int,
)