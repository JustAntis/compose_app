package com.example.firstcomposeapp.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.firstcomposeapp.domain.entities.FeedPost


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
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
                navArgument(Screen.KEY_FEED_POST){
                    type = FeedPost.NavigationType
                }
            )
        )
        { //comments/{feed_post_id}/{feed_post_text}
            val feedPost = it.arguments?.getParcelable(Screen.KEY_FEED_POST, FeedPost::class.java) ?: throw RuntimeException("Bad Parcelable Object")
            commentsScreenContent(feedPost)
        }
    }
}