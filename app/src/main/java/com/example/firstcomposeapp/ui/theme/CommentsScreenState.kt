package com.example.firstcomposeapp.ui.theme

import com.example.firstcomposeapp.domain.FeedPost
import com.example.firstcomposeapp.domain.PostComment

sealed class CommentsScreenState {

    data class Comments(val feedPost: FeedPost, val comments: List<PostComment>): CommentsScreenState()
    object Initial: CommentsScreenState()
}