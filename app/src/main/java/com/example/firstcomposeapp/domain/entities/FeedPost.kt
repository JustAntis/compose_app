package com.example.firstcomposeapp.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FeedPost(
    val id: Int,
    val communityName: String?,
    val publicationDate: String?,
    val avatarUrl: String?,
    val contentText: String?,
    val contentImageUrl: String?,
    val statistics: List<StatisticItem> = listOf(
        StatisticItem(StatisticType.VIEWS, 966),
        StatisticItem(StatisticType.COMMENTS, 43),
        StatisticItem(StatisticType.SHARES, 345),
        StatisticItem(StatisticType.LIKES, 541),
    )
): Parcelable
