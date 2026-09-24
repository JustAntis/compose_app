package com.example.firstcomposeapp.navigation

import com.example.firstcomposeapp.domain.FeedPost

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
            return "$ROUTE_FOR_ARGS/${feedPost.id}/${feedPost.contentText}"
        }
    }

    companion object{
        const val KEY_FEED_POST_ID = "feed_post_id"
        const val KEY_FEED_POST_TEXT = "feed_post_text"
        const val ROUTE_HOME = "home"
        const val ROUTE_COMMENTS = "comments/{$KEY_FEED_POST_ID}/{$KEY_FEED_POST_TEXT}"
        const val ROUTE_NEWS_FEED = "News Feed"
        const val ROUTE_FAVOURITE = "Favourite"
        const val ROUTE_PROFILE = "Profile"
    }
}