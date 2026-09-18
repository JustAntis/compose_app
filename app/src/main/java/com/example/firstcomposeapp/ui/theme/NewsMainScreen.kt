package com.example.firstcomposeapp.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.firstcomposeapp.navigation.AppNavGraph

@Composable
fun MainScreen() {
    val navHostController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navHostController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Profile,
                    NavigationItem.Favourite
                )
                items.forEach { navigationItem ->
                    NavigationBarItem(
                        selected = currentRoute == navigationItem.screen.route,
                        onClick = { navHostController.navigate(navigationItem.screen.route) },
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
            navHostController = navHostController,
            homeScreenContent = {
                HomeScreen(paddingValues = paddingValues)
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
private fun TextCounter(name: String){
    val count = remember { mutableIntStateOf(0) }

    Text(text = "Screen: $name, Count: ${count.intValue}", modifier = Modifier.clickable{ count.intValue++ }, color = Color.Black)
}