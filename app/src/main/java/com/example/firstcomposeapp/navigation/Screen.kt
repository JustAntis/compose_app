package com.example.firstcomposeapp.navigation

import android.net.Uri
import com.example.firstcomposeapp.domain.entities.FeedPost
import com.google.gson.Gson

sealed class Screen (
    val route: String
){
    object NewsFeed: Screen(ROUTE_NEWS_FEED)
    object Favourite: Screen(ROUTE_FAVOURITE)
    object Profile: Screen(ROUTE_PROFILE)
    object Home: Screen(ROUTE_HOME)
    object Comments: Screen(ROUTE_COMMENTS){
        private const val ROUTE_FOR_ARGS = "comments"
        fun getRouteWithArgs(feedPost: FeedPost): String{
            val feedPostToJson = Gson().toJson(feedPost)
            return "$ROUTE_FOR_ARGS/${feedPostToJson.encode()}"
        }
    }

    companion object{
        const val KEY_FEED_POST = "feed_post"
        const val ROUTE_HOME = "home"
        const val ROUTE_COMMENTS = "comments/{$KEY_FEED_POST}"
        const val ROUTE_NEWS_FEED = "News Feed"
        const val ROUTE_FAVOURITE = "Favourite"
        const val ROUTE_PROFILE = "Profile"
    }
}
fun String.encode(): String{
    return Uri.encode(this)
}