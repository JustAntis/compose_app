package com.example.firstcomposeapp.presentation.news

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.firstcomposeapp.domain.entities.FeedPost

@Composable
fun HomeScreen(
    viewModel: NewsFeedViewModel = hiltViewModel(),
    paddingValues: PaddingValues,
    onCommentsClickListener: (FeedPost) -> Unit,
    snackBarHostState: SnackbarHostState
) {
    val screenState = viewModel.screenState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.eventErrorChannel.collect {
            val result = snackBarHostState.showSnackbar(
                message = it,
                actionLabel = "Retry",
                duration = SnackbarDuration.Long
            )
            if (result == SnackbarResult.ActionPerformed) {
                viewModel.updateList()
            }
        }
    }
    when (val currentState = screenState.value) {
        is NewsFeedScreenState.Posts -> FeedPosts(
            viewModel = viewModel,
            paddingValues = paddingValues,
            posts = currentState.posts,
            onCommentsClickListener = onCommentsClickListener,
            currentState.nextDataIsLoading
        )

        NewsFeedScreenState.Initial -> {}
        NewsFeedScreenState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color.Black)
            }
        }

        is NewsFeedScreenState.Error -> {
            LaunchedEffect(currentState) {
                val result = snackBarHostState.showSnackbar(
                    message = currentState.message,
                    actionLabel = "Retry",
                    duration = SnackbarDuration.Indefinite
                )
                if (result == SnackbarResult.ActionPerformed) {
                    viewModel.updateList()
                }
            }
        }
    }
}

@Composable
fun FeedPosts(
    viewModel: NewsFeedViewModel,
    paddingValues: PaddingValues,
    posts: List<FeedPost>,
    onCommentsClickListener: (FeedPost) -> Unit,
    nextDataIsLoading: Boolean
) {
    val listState = rememberLazyListState()
    val shouldLoadMore by remember {
        derivedStateOf {
            val lastVisible = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            val total = listState.layoutInfo.totalItemsCount
            total > 0 && lastVisible >= total - 3
        }
    }

    LaunchedEffect(shouldLoadMore) {
        if (shouldLoadMore) viewModel.updateList()
    }
    LazyColumn(
        contentPadding = paddingValues,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        state = listState
    ) {
        items(posts, key = { it.id }) { feedPost ->
            val dismissState = rememberSwipeToDismissBoxState()
            SwipeToDismissBox(
                state = dismissState,
                backgroundContent = {
                    Box(
                        contentAlignment = Alignment.CenterEnd,
                        modifier = Modifier
                            .padding(16.dp)
                            .background(Color.Red.copy(alpha = 0.5f))
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
                        onCommentsClickListener(feedPost)
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
        if (nextDataIsLoading) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}