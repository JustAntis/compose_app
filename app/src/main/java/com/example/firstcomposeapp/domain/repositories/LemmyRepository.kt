package com.example.firstcomposeapp.domain.repositories

import com.example.firstcomposeapp.domain.entities.FeedPost
import kotlinx.coroutines.flow.StateFlow

interface LemmyRepository {
    fun getFeedPosts(): StateFlow<List<FeedPost>>
    suspend fun loadNextData()
}