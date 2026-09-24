package com.example.firstcomposeapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.firstcomposeapp.domain.FeedPost


fun NavGraphBuilder.homeScreenNavGraph(
    newsFeedScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable (FeedPost) -> Unit,

    ) {
    navigation(
        startDestination = Screen.NewsFeed.route,
        route = Screen.Home.route
    ) {
        composable(Screen.NewsFeed.route) {
            newsFeedScreenContent()
        }
        composable(
            route = Screen.Comments.route,
            arguments = listOf(
                navArgument(Screen.KEY_FEED_POST_ID){
                    type = NavType.IntType
                },
                navArgument(Screen.KEY_FEED_POST_TEXT){
                    type = NavType.StringType
                }
            )
        )
        { //comments/{feed_post_id}/{feed_post_text}
            val feedPostId = it.arguments?.getInt(Screen.KEY_FEED_POST_ID) ?: 0
            val feedPostText = it.arguments?.getString(Screen.KEY_FEED_POST_TEXT) ?: ""
            commentsScreenContent(FeedPost(id = feedPostId, contentText = feedPostText))
        }
    }
}