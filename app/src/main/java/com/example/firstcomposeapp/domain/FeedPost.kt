package com.example.firstcomposeapp.domain

import com.example.firstcomposeapp.R

data class FeedPost(
    val communityName: String = "/developers",
    val publicationDate: String = "9:43",
    val avatarResId: Int = R.drawable.outline_alt_route_24,
    val contentText: String = "Favourite",
    val contentImageResId: Int = R.drawable.io_mem,
    val statistics: List<StatisticItem> = listOf(
        StatisticItem(StatisticType.VIEWS, 966),
        StatisticItem(StatisticType.COMMENTS, 43),
        StatisticItem(StatisticType.SHARES, 345),
        StatisticItem(StatisticType.LIKES, 541),
    )
)
