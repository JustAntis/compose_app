package com.example.firstcomposeapp.presentation.news

import com.example.firstcomposeapp.domain.entities.FeedPost

sealed class NewsFeedScreenState {

    data class Posts(val posts: List<FeedPost>): NewsFeedScreenState()
    object Initial: NewsFeedScreenState()
}