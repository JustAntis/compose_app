package com.example.firstcomposeapp.data.network.model.postComment

import com.google.gson.annotations.SerializedName

data class AuthorDto(
    val id: Int,
    @SerializedName("display_name")
    val displayName: String?,
    val name: String,
    @SerializedName("avatar")
    val avatar: String?
)
