package com.example.firstcomposeapp.presentation.news

import com.example.firstcomposeapp.domain.entities.FeedPost

sealed class NewsFeedScreenState {

    data class Posts(val posts: List<FeedPost>, val nextDataIsLoading: Boolean = false): NewsFeedScreenState()
    object Initial: NewsFeedScreenState()
    object Loading: NewsFeedScreenState()
    data class Error(val message: String): NewsFeedScreenState()
}