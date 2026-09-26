package com.example.firstcomposeapp.data.mappers

import com.example.firstcomposeapp.data.network.model.PostListResponse
import com.example.firstcomposeapp.data.network.model.PostViewDto
import com.example.firstcomposeapp.domain.entities.FeedPost
import com.example.firstcomposeapp.domain.entities.StatisticItem
import com.example.firstcomposeapp.domain.entities.StatisticType
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

class LemmyMapper @Inject constructor(){
    fun mapDtoToEntityModel(postViewDto: PostViewDto): FeedPost{
        return FeedPost(
            id = postViewDto.post.id,
            communityName = postViewDto.community.title,
            publicationDate = formatPublicationDate(postViewDto.post.published),
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

    private fun formatPublicationDate(published: String): String {
        return try {
            DATE_FORMATTER.format(Instant.parse(published))
        } catch (e: Exception) {
            published
        }
    }

    private companion object {
        val DATE_FORMATTER: DateTimeFormatter =
            DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm", Locale.getDefault())
                .withZone(ZoneId.systemDefault())
    }
}
