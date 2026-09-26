package com.example.firstcomposeapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.firstcomposeapp.domain.entities.FeedPost

class NavigationState(
    val navHostController: NavHostController
) {
    fun navigateTo(route: String){
        navHostController.navigate(route) {
            popUpTo(navHostController.graph.findStartDestination().id){
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    fun navigateToComments(feedPost: FeedPost){//comments/feedPost/
        navHostController.navigate(Screen.Comments.getRouteWithArgs(feedPost))
    }
}

@Composable
fun rememberNavigationState(
    navHostController: NavHostController = rememberNavController()
): NavigationState{
    return remember { NavigationState(navHostController) }
}