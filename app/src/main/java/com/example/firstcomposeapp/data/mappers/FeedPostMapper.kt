package com.example.firstcomposeapp.data.mappers

import com.example.firstcomposeapp.data.mappers.DateFormatter.toFormattedDate
import com.example.firstcomposeapp.data.network.model.feedPost.PostListResponse
import com.example.firstcomposeapp.data.network.model.feedPost.PostViewDto
import com.example.firstcomposeapp.domain.entities.FeedPost
import com.example.firstcomposeapp.domain.entities.StatisticItem
import com.example.firstcomposeapp.domain.entities.StatisticType
import javax.inject.Inject

class FeedPostMapper @Inject constructor(){
    fun mapDtoToEntityModel(postViewDto: PostViewDto): FeedPost{
        return FeedPost(
            id = postViewDto.post.id,
            communityName = postViewDto.community.title,
            publicationDate = postViewDto.post.published.toFormattedDate(),
            avatarUrl = postViewDto.community.icon,
            contentText = if (postViewDto.post.body.isNullOrBlank())
                postViewDto.post.name
            else
                postViewDto.post.name
            ,
            contentImageUrl = postViewDto.post.thumbnailUrl,
            statistics = listOf(
                //Todo
                StatisticItem(StatisticType.VIEWS, 1),
                StatisticItem(StatisticType.COMMENTS, postViewDto.counts.comments),
                StatisticItem(StatisticType.SHARES, 1),
                StatisticItem(StatisticType.LIKES, postViewDto.counts.score),
            )
        )
    }
    fun mapListResponseToListFeedPost(postListResponse: PostListResponse): List<FeedPost>{
        return postListResponse.posts.map {
            mapDtoToEntityModel(it)
        }
    }

}
