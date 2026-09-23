package com.example.firstcomposeapp.ui.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.firstcomposeapp.R
import com.example.firstcomposeapp.navigation.Screen

sealed class NavigationItem(
    val screen: Screen,
    val titleResId:Int,
    val icon: ImageVector
) {
    object Home: NavigationItem(
        screen = Screen.Home,
        titleResId = R.string.home_nav,
        icon = Icons.Outlined.Home
    )
    object Favourite: NavigationItem(
        Screen.Favourite,
        titleResId = R.string.favourite_nav,
        icon = Icons.Outlined.FavoriteBorder
    )
    object Profile: NavigationItem(
        Screen.Profile,
        titleResId = R.string.profile_nav,
        icon = Icons.Outlined.Person
    )
}