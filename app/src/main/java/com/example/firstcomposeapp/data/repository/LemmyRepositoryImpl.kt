package com.example.firstcomposeapp.data.repository

import com.example.firstcomposeapp.data.mappers.LemmyMapper
import com.example.firstcomposeapp.data.network.ApiService
import com.example.firstcomposeapp.domain.entities.FeedPost
import com.example.firstcomposeapp.domain.repositories.LemmyRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@Singleton
class LemmyRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: LemmyMapper
):  LemmyRepository {

    private var nextPage: String? = null
    private val _feedPosts = MutableStateFlow<List<FeedPost>>(emptyList())

    override fun getFeedPosts(): StateFlow<List<FeedPost>> {
        return _feedPosts.asStateFlow()
    }

    override suspend fun loadNextData() {
        val response = apiService.getPosts(pageCursor = nextPage)
        nextPage = response.nextPage
        val listOfFeedPosts = mapper.mapListResponseToListFeedPost(response)
        _feedPosts.update {
            it + listOfFeedPosts
        }
    }
}