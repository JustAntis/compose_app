package com.example.firstcomposeapp.ui.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.firstcomposeapp.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {

    Scaffold(
        bottomBar = {
            NavigationBar {
                val state = remember { mutableIntStateOf(0) }
                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Profile,
                    NavigationItem.Favourite
                )
                items.forEachIndexed { index, navigationItem ->
                    NavigationBarItem(
                        selected = state.intValue == index,
                        onClick = { state.intValue = index },
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
    ) { it ->
        val feedPost = viewModel.feedPost.collectAsState()
        NewsCard(
            modifier = Modifier.padding(
                top = 30.dp,
                start = 16.dp,
                end = 16.dp,
                bottom = 16.dp
            ),
            feedPost = feedPost.value,
            onCommentClickListener = {  },
            onLikeClickListener = {  },
            onShareClickListener = {  },
            onViewsClickListener = { newItem ->
                viewModel.onClickStatistic(newItem)
            }
        )
        it
    }
}