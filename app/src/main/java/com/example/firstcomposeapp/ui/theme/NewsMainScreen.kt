package com.example.firstcomposeapp.ui.theme

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.firstcomposeapp.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    val selectedNavItem = viewModel.selectedNavItem.collectAsState()
    Scaffold(
        bottomBar = {
            NavigationBar {
                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Profile,
                    NavigationItem.Favourite
                )
                items.forEach { navigationItem ->
                    NavigationBarItem(
                        selected = selectedNavItem.value == navigationItem,
                        onClick = { viewModel.selectNavItem(navigationItem) },
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
        when(selectedNavItem.value){
            NavigationItem.Home -> {
                HomeScreen(paddingValues = paddingValues)
            }

            NavigationItem.Profile -> {
                Text(text = "Profile", color = Color.Black)
            }

            NavigationItem.Favourite -> {
                Text(text = "Favourite", color = Color.Black)
            }
        }
    }
}
