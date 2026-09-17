package com.example.firstcomposeapp.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.firstcomposeapp.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    Scaffold(
        bottomBar = {
            BottomBar()
        }
    ) { paddingValues ->
        val feedPost = viewModel.feedPost.collectAsState()
        LazyColumn(contentPadding = paddingValues) {
            items(feedPost.value, key = { it.hashCode() }) { feedPost ->
                val dismissState = rememberSwipeToDismissBoxState()
                SwipeToDismissBox(
                    state = dismissState,
                    backgroundContent = {
                        Box(
                            contentAlignment = Alignment.CenterEnd,
                            modifier = Modifier
                                .padding(16.dp)
                                .background(Color.Red)
                                .fillMaxSize()
                        ) {
                            Text(
                                text = "Delete",
                                fontSize = 24.sp,
                                color = Color.White,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    },
                    onDismiss = {
                        viewModel.delete(feedPost)
                    },
                    enableDismissFromEndToStart = true,
                    enableDismissFromStartToEnd = false,
                    modifier = Modifier
                        .animateItem()
                        .fillMaxSize()
                ) {
                    NewsCard(
                        modifier = Modifier
                            .fillMaxSize(),
                        feedPost = feedPost,
                        onCommentClickListener = {
                            viewModel.onClickStatistic(feedPost = feedPost, item = it)
                        },
                        onLikeClickListener = {
                            viewModel.onClickStatistic(feedPost = feedPost, item = it)
                        },
                        onShareClickListener = {
                            viewModel.onClickStatistic(feedPost = feedPost, item = it)
                        },
                        onViewsClickListener = {
                            viewModel.onClickStatistic(feedPost = feedPost, item = it)
                        },
                    )
                }
            }
        }
    }
}

@Composable
fun BottomBar() {
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
}