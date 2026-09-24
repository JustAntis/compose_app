package com.example.firstcomposeapp.domain

import android.os.Build
import android.os.Parcelable
import androidx.annotation.RequiresApi
import androidx.navigation.NavType
import androidx.savedstate.SavedState
import com.example.firstcomposeapp.R
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class FeedPost(
    val id: Int = 0,
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
): Parcelable{
    companion object{
        val NavigationType: NavType<FeedPost> = object : NavType<FeedPost>(false) {
            override fun put(
                bundle: SavedState,
                key: String,
                value: FeedPost
            ) {
                bundle.putParcelable(key, value)
            }

            @RequiresApi(Build.VERSION_CODES.TIRAMISU)
            override fun get(
                bundle: SavedState,
                key: String
            ): FeedPost? {
                return bundle.getParcelable(key, FeedPost::class.java)
            }

            override fun parseValue(value: String): FeedPost {
                return Gson().fromJson(value, FeedPost::class.java)
            }
        }
    }
}
