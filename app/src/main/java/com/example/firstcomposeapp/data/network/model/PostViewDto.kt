package com.example.firstcomposeapp.data.network.model

data class PostViewDto(
    val post: PostDto,
    val community: CommunityDto,
    val counts: CountsDto
)
