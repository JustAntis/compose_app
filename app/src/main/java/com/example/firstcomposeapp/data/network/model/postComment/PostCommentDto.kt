package com.example.firstcomposeapp.data.network.model.postComment

data class PostCommentDto(
    val creator: AuthorDto,
    val comment: CommentDto,
    val counts: CommentCountsDto
)
