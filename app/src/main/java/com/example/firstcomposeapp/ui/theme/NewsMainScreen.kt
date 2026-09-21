package com.example.firstcomposeapp.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.firstcomposeapp.domain.FeedPost
import com.example.firstcomposeapp.navigation.AppNavGraph
import com.example.firstcomposeapp.navigation.rememberNavigationState

@Composable
fun MainScreen() {
    val navigationState = rememberNavigationState()

    val commentsToPosts: MutableState<FeedPost?> = remember { mutableStateOf(null) }
    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Profile,
                    NavigationItem.Favourite
                )
                items.forEach { navigationItem ->
                    NavigationBarItem(
                        selected = currentRoute == navigationItem.screen.route,
                        onClick = {
                            navigationState.navigateTo(navigationItem.screen.route)
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
        }
    ) { paddingValues ->
        AppNavGraph(
            navHostController = navigationState.navHostController,
            homeScreenContent = {
                if (commentsToPosts.value == null) {
                    HomeScreen(
                        paddingValues = paddingValues,
                        onCommentsClickListener = {
                            commentsToPosts.value = it
                        }
                    )
                } else {
                    CommentsScreen {
                        commentsToPosts.value = null
                    }
                }
            },
            profileScreenContent = {
                TextCounter("Profile")
            },
            favouriteScreenContent = {
                TextCounter("Favourite")
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