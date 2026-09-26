package com.example.firstcomposeapp

import androidx.lifecycle.ViewModel
import com.example.firstcomposeapp.domain.entities.FeedPost
import com.example.firstcomposeapp.domain.entities.PostComment
import com.example.firstcomposeapp.ui.theme.CommentsScreenState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel(assistedFactory = CommentsViewModel.Factory::class)
class CommentsViewModel @AssistedInject constructor(
    @Assisted  val feedPost: FeedPost
): ViewModel() {

    private val _stateScreen = MutableStateFlow<CommentsScreenState>(CommentsScreenState.Initial)
    val screenState: StateFlow<CommentsScreenState> = _stateScreen.asStateFlow()

    init {
        loadComments(feedPost = feedPost)
    }
    fun loadComments(feedPost: FeedPost){
        val comments = mutableListOf<PostComment>().apply {
            repeat(10){
                add(PostComment(id = it))
            }
        }
        _stateScreen.value = CommentsScreenState.Comments(feedPost, comments)
    }
    @AssistedFactory
    interface Factory {
        fun create(feedPost: FeedPost): CommentsViewModel
    }
}