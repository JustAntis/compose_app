package com.example.firstcomposeapp

import androidx.lifecycle.ViewModel
import com.example.firstcomposeapp.domain.FeedPost
import com.example.firstcomposeapp.domain.PostComment
import com.example.firstcomposeapp.ui.theme.CommentsScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class CommentsViewModel @Inject constructor(): ViewModel() {

    private val _stateScreen = MutableStateFlow<CommentsScreenState>(CommentsScreenState.Initial)
    val screenState: StateFlow<CommentsScreenState> = _stateScreen.asStateFlow()

    init {
        loadComments(FeedPost())
    }

    fun loadComments(feedPost: FeedPost){
        val comments = mutableListOf<PostComment>().apply {
            repeat(10){
                add(PostComment(id = it))
            }
        }
        _stateScreen.value = CommentsScreenState.Comments(feedPost, comments)
    }
}