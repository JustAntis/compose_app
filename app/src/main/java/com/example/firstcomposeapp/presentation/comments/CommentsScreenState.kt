package com.example.firstcomposeapp.presentation.comments

import com.example.firstcomposeapp.domain.entities.PostComment

sealed class CommentsScreenState {

    data class Comments(val postId: Int, val comments: List<PostComment>): CommentsScreenState()
    object Initial: CommentsScreenState()
}