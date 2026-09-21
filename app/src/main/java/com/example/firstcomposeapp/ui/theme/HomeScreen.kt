package com.example.firstcomposeapp.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.firstcomposeapp.NewsFeedViewModel
import com.example.firstcomposeapp.domain.FeedPost

@Composable
fun HomeScreen(
    viewModel: NewsFeedViewModel = hiltViewModel(),
    paddingValues: PaddingValues,
    onCommentsClickListener:(FeedPost) -> Unit
) {
    val screenState = viewModel.screenState.collectAsState()

    when(val currentState = screenState.value){
        is NewsFeedScreenState.Posts -> FeedPosts(
            viewModel = viewModel,
            paddingValues = paddingValues,
            posts = currentState.posts,
            onCommentsClickListener = onCommentsClickListener
        )
        NewsFeedScreenState.Initial -> { }
    }

}

@Composable
fun FeedPosts(
    viewModel: NewsFeedViewModel,
    paddingValues: PaddingValues,
    posts: List<FeedPost>,
    onCommentsClickListener:(FeedPost) -> Unit
){
    LazyColumn(contentPadding = paddingValues, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(posts, key = { it.hashCode() }) { feedPost ->
            val dismissState = rememberSwipeToDismissBoxState()
            SwipeToDismissBox(
                state = dismissState,
                backgroundContent = {
                    Box(
                        contentAlignment = Alignment.CenterEnd,
                        modifier = Modifier
                            .padding(16.dp)
                            .background(Color.Red.copy(alpha = 5f))
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
    }
}