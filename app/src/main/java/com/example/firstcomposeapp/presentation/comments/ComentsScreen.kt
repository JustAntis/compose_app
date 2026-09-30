package com.example.firstcomposeapp.presentation.comments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.firstcomposeapp.R
import com.example.firstcomposeapp.domain.entities.PostComment


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentsScreen(
    postId: Int,
    viewModel: CommentsViewModel = hiltViewModel(
        creationCallback = {
            factory: CommentsViewModel.Factory -> factory.create(postId = postId)
        }
    ),
    onBackPressed: () -> Unit
){
    val state = viewModel.screenState.collectAsState()
    val currentState = state.value
    if (currentState is CommentsScreenState.Comments) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Comments for feedPost id:${currentState.postId}", fontSize = 24.sp)
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            onBackPressed()
                        }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = null
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->
            LazyColumn(
                contentPadding = paddingValues
            ) {
                items(currentState.comments, key = { it.id }) { comments ->
                    CommentItem(comments)
                }
            }
        }
    }
}

@Composable
private fun CommentItem(postComment: PostComment){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        AsyncImage(
            model = postComment.authorAvatarId,
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(32.dp),
            fallback = painterResource(R.drawable.person_circle_fill_svgrepo_com),
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = postComment.authorName,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = postComment.commentText,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = postComment.publicationDate,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}