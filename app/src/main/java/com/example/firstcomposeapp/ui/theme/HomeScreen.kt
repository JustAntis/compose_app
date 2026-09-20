package com.example.firstcomposeapp.ui.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.firstcomposeapp.MainViewModel
import com.example.firstcomposeapp.domain.PostComment

@Composable
fun HomeScreen(viewModel: MainViewModel = hiltViewModel(), paddingValues: PaddingValues) {
    val feedPost = viewModel.feedPosts.collectAsState()
    if (feedPost.value.isNotEmpty()){
        val comments = mutableListOf<PostComment>().apply {
            repeat(20){
                add(PostComment(id = it))
            }
        }
        CommentsScreen(feedPost = feedPost.value[0], comments = comments)
    }

//    LazyColumn(contentPadding = paddingValues, verticalArrangement = Arrangement.spacedBy(8.dp)) {
//        items(feedPost.value, key = { it.hashCode() }) { feedPost ->
//            val dismissState = rememberSwipeToDismissBoxState()
//            SwipeToDismissBox(
//                state = dismissState,
//                backgroundContent = {
//                    Box(
//                        contentAlignment = Alignment.CenterEnd,
//                        modifier = Modifier
//                            .padding(16.dp)
//                            .background(Color.Red.copy(alpha = 5f))
//                            .fillMaxSize()
//                    ) {
//                        Text(
//                            text = "Delete",
//                            fontSize = 24.sp,
//                            color = Color.White,
//                            modifier = Modifier.padding(16.dp)
//                        )
//                    }
//                },
//                onDismiss = {
//                    viewModel.delete(feedPost)
//                },
//                enableDismissFromEndToStart = true,
//                enableDismissFromStartToEnd = false,
//                modifier = Modifier
//                    .animateItem()
//                    .fillMaxSize()
//            ) {
//                NewsCard(
//                    modifier = Modifier
//                        .fillMaxSize(),
//                    feedPost = feedPost,
//                    onCommentClickListener = {
//                        viewModel.onClickStatistic(feedPost = feedPost, item = it)
//                    },
//                    onLikeClickListener = {
//                        viewModel.onClickStatistic(feedPost = feedPost, item = it)
//                    },
//                    onShareClickListener = {
//                        viewModel.onClickStatistic(feedPost = feedPost, item = it)
//                    },
//                    onViewsClickListener = {
//                        viewModel.onClickStatistic(feedPost = feedPost, item = it)
//                    },
//                )
//            }
//        }
//    }
}