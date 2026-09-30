package com.example.firstcomposeapp.domain.repositories

import com.example.firstcomposeapp.domain.entities.FeedPost
import com.example.firstcomposeapp.domain.entities.PostComment
import kotlinx.coroutines.flow.StateFlow

interface LemmyRepository {
    fun getFeedPosts(): StateFlow<List<FeedPost>>
    suspend fun loadNextData()

    suspend fun getComments(postId: Int): List<PostComment>
}