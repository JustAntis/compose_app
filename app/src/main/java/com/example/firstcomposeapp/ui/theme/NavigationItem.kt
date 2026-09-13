package com.example.firstcomposeapp.ui.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.firstcomposeapp.R

sealed class NavigationItem(
    val titleResId:Int,
    val icon: ImageVector
) {
    object Home: NavigationItem(
        titleResId = R.string.home_nav,
        icon = Icons.Outlined.Home
    )
    object Favourite: NavigationItem(
        titleResId = R.string.favourite_nav,
        icon = Icons.Outlined.FavoriteBorder
    )
    object Profile: NavigationItem(
        titleResId = R.string.profile_nav,
        icon = Icons.Outlined.Person
    )
}