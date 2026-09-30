package com.example.firstcomposeapp.data.mappers

import com.example.firstcomposeapp.data.mappers.DateFormatter.toFormattedDate
import com.example.firstcomposeapp.data.network.model.postComment.CommentListResponse
import com.example.firstcomposeapp.data.network.model.postComment.PostCommentDto
import com.example.firstcomposeapp.domain.entities.PostComment
import javax.inject.Inject

class PostCommentMapper @Inject constructor() {
    fun mapPostCommentDtoToPostCommentEntity(postCommentDto: PostCommentDto): PostComment{
        return PostComment(
            id = postCommentDto.comment.id,
            authorName = postCommentDto.creator.displayName ?: postCommentDto.creator.name,
            authorAvatarId = postCommentDto.creator.avatar,
            commentText = postCommentDto.comment.content,
            publicationDate = postCommentDto.comment.published.toFormattedDate()
        )
    }
    fun mapListDtoToEntityList(response: CommentListResponse): List<PostComment>{
        return response.comments.map {
            mapPostCommentDtoToPostCommentEntity(it)
        }
    }
}