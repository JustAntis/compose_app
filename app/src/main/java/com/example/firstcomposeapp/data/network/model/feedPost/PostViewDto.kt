package com.example.firstcomposeapp.data.network.model.feedPost

data class PostViewDto(
    val post: PostDto,
    val community: CommunityDto,
    val counts: PostCountsDto
)
