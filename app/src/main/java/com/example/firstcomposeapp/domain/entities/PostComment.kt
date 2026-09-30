package com.example.firstcomposeapp.domain.entities

data class PostComment(
    val id: Int,
    val authorName: String,
    val authorAvatarId: String?,
    val commentText: String,
    val publicationDate: String
)
