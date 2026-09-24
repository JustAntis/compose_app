package com.example.firstcomposeapp.domain

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class StatisticItem(
    val type: StatisticType,
    val count: Int
): Parcelable

enum class StatisticType{
    VIEWS, COMMENTS, SHARES, LIKES
}
