package com.example.firstcomposeapp.ui.theme

import com.example.firstcomposeapp.domain.entities.FeedPost
import com.example.firstcomposeapp.domain.entities.PostComment

sealed class CommentsScreenState {

    data class Comments(val feedPost: FeedPost, val comments: List<PostComment>): CommentsScreenState()
    object Initial: CommentsScreenState()
}