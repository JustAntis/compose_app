package com.example.firstcomposeapp.data.repository

import com.example.firstcomposeapp.data.mappers.FeedPostMapper
import com.example.firstcomposeapp.data.mappers.PostCommentMapper
import com.example.firstcomposeapp.data.network.ApiService
import com.example.firstcomposeapp.domain.entities.FeedPost
import com.example.firstcomposeapp.domain.entities.PostComment
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
    private val mapperFeedPostMapper: FeedPostMapper,
    private val mapperPostCommentMapper: PostCommentMapper
):  LemmyRepository {

    private var nextPage: String? = null
    private val _feedPosts = MutableStateFlow<List<FeedPost>>(emptyList())

    override fun getFeedPosts(): StateFlow<List<FeedPost>> {
        return _feedPosts.asStateFlow()
    }

    override suspend fun loadNextData() {
        val response = apiService.getPosts(pageCursor = nextPage)
        nextPage = response.nextPage
        val listOfFeedPosts = mapperFeedPostMapper.mapListResponseToListFeedPost(response)
        _feedPosts.update {
            it + listOfFeedPosts
        }
    }

    override suspend fun getComments(postId: Int): List<PostComment> {
        val response = apiService.getComments(postId = postId)
        return mapperPostCommentMapper.mapListDtoToEntityList(response)
    }

}