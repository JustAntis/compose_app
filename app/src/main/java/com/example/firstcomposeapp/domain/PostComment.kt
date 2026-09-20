package com.example.firstcomposeapp.domain

import com.example.firstcomposeapp.R

data class PostComment(
    val id: Int,
    val authorName: String = "Author",
    val authorAvatarId: Int = R.drawable.person_circle_fill_svgrepo_com,
    val commentText: String = "some text",
    val publicationDate: String = "18:32"
)
