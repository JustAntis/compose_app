package com.example.firstcomposeapp.navigation

sealed class Screen (
    val route: String
){
    object NewsFeed: Screen(ROUTE_NEWS_FEED)
    object Favourite: Screen(ROUTE_FAVOURITE)
    object Profile: Screen(ROUTE_PROFILE)

    object Home: Screen(ROUTE_HOME)
    object Comments: Screen(ROUTE_COMMENTS)

    private companion object{
        const val ROUTE_HOME = "home"
        const val ROUTE_COMMENTS = "comments"
        const val ROUTE_NEWS_FEED = "News Feed"
        const val ROUTE_FAVOURITE = "Favourite"
        const val ROUTE_PROFILE = "Profile"
    }
}