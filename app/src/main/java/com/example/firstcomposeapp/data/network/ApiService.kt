package com.example.firstcomposeapp.data.network

import com.example.firstcomposeapp.data.network.model.PostListResponse
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

}