package com.example.firstcomposeapp.data.network

import com.example.firstcomposeapp.data.network.model.feedPost.PostListResponse
import com.example.firstcomposeapp.data.network.model.postComment.CommentListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("post/list")
    suspend fun getPosts(
        @Query("limit") limit: Int = 20,
        @Query("sort") sort: String = "Hot",
        @Query("type_") type: String = "All",
        @Query("page_cursor") pageCursor: String? = null
    ): PostListResponse

    @GET("comment/list")
    suspend fun getComments(
        @Query("post_id") postId: Int,
        @Query("sort") sort: String = "Hot",
        @Query("max_depth") maxDepth: Int = 1,
    ): CommentListResponse
}