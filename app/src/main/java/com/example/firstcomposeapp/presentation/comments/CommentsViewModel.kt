package com.example.firstcomposeapp.presentation.comments

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstcomposeapp.domain.usecases.GetCommentsUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel(assistedFactory = CommentsViewModel.Factory::class)
class CommentsViewModel @AssistedInject constructor(
    @Assisted val postId: Int,
    private val getCommentsUseCase: GetCommentsUseCase
): ViewModel() {

    private val _stateScreen = MutableStateFlow<CommentsScreenState>(CommentsScreenState.Initial)
    val screenState: StateFlow<CommentsScreenState> = _stateScreen.asStateFlow()

    init {
        loadComments(postId = postId)
    }
    fun loadComments(postId: Int){
        viewModelScope.launch {
            runCatching {
                getCommentsUseCase.invoke(postId)
            }.onSuccess {
                _stateScreen.value = CommentsScreenState.Comments(postId, it)
            }.onFailure {
                if (it is CancellationException) throw it
                Log.d("VIEW_MODEL_COMMENTS", "Load failed: ${it.message}", it)
            }
        }
    }
    @AssistedFactory
    interface Factory {
        fun create(postId: Int): CommentsViewModel
    }
}