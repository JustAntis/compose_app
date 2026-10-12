package com.example.firstcomposeapp.presentation.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.firstcomposeapp.navigation.AppNavGraph
import com.example.firstcomposeapp.navigation.rememberNavigationState
import com.example.firstcomposeapp.presentation.comments.CommentsScreen
import com.example.firstcomposeapp.presentation.news.HomeScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun MainScreen() {
    val navigationState = rememberNavigationState()
    val snackBarHostState = remember { SnackbarHostState() }

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()

                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Profile,
                    NavigationItem.Favourite
                )
                items.forEach { navigationItem ->

                    val selected = navBackStackEntry?.destination?.hierarchy?.any {
                        it.route == navigationItem.screen.route
                    } ?: false

                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            if(!selected) {
                                navigationState.navigateTo(navigationItem.screen.route)
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = navigationItem.icon,
                                contentDescription = null
                            )
                        },
                        label = { Text(stringResource(id = navigationItem.titleResId)) }
                    )
                }
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }
    ) { paddingValues ->
        AppNavGraph(
            navHostController = navigationState.navHostController,
            newsFeedScreenContent = {
                HomeScreen(
                    paddingValues = paddingValues,
                    onCommentsClickListener = {
                        navigationState.navigateToComments(it)
                    },
                    snackBarHostState = snackBarHostState
                )
            },
            profileScreenContent = {
                TextCounter("Profile")
            },
            favouriteScreenContent = {
                TextCounter("Favourite")
            },
            commentsScreenContent = {feedPost ->
                CommentsScreen(
                    onBackPressed = {
                        navigationState.navHostController.popBackStack()
                    },
                    postId = feedPost.id
                )
            }
        )
    }
}

@Composable
private fun TextCounter(name: String) {
    val count = rememberSaveable { mutableIntStateOf(0) }

    Text(
        text = "Screen: $name, Count: ${count.intValue}",
        modifier = Modifier.clickable { count.intValue++ },
        color = Color.Black
    )
}