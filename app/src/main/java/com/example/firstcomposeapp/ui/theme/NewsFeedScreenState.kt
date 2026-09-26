package com.example.firstcomposeapp.ui.theme

import com.example.firstcomposeapp.domain.entities.FeedPost

sealed class NewsFeedScreenState {

    data class Posts(val posts: List<FeedPost>): NewsFeedScreenState()
    object Initial: NewsFeedScreenState()
}