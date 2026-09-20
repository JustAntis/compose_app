package com.example.firstcomposeapp.ui.theme

import com.example.firstcomposeapp.domain.FeedPost
import com.example.firstcomposeapp.domain.PostComment

sealed class HomeScreenState {

    data class Posts(val posts: List<FeedPost>): HomeScreenState()
    data class Comments(val feedPost: FeedPost, val comments: List<PostComment>): HomeScreenState()
    object Initial: HomeScreenState()
}