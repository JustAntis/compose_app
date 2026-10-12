package com.example.firstcomposeapp.presentation.news

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstcomposeapp.domain.entities.FeedPost
import com.example.firstcomposeapp.domain.entities.StatisticItem
import com.example.firstcomposeapp.domain.entities.StatisticType
import com.example.firstcomposeapp.domain.usecases.GetFeedPostsUseCase
import com.example.firstcomposeapp.domain.usecases.LoadFeedPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel
class NewsFeedViewModel @Inject constructor(
    private val getFeedPostsUseCase: GetFeedPostsUseCase,
    private val loadFeedPostsUseCase: LoadFeedPostsUseCase
) : ViewModel() {

    private val feedPosts = getFeedPostsUseCase.invoke()
    private val _screenState =
        MutableStateFlow<NewsFeedScreenState>(NewsFeedScreenState.Initial)
    val screenState = _screenState.asStateFlow()

    private val _eventErrorChannel = Channel<String>(Channel.BUFFERED)
    val eventErrorChannel = _eventErrorChannel.receiveAsFlow()

    private var isLoading = false

    init {
        viewModelScope.launch {
            feedPosts.collect {
                _screenState.value = NewsFeedScreenState.Posts(it)
                Log.d("VIEW_MODEL_TAG", "Size is:${it.size}")
            }
        }
        updateList()
    }
    fun updateList() {
        if (isLoading) return
        isLoading = true
        when (val state = _screenState.value) {
            is NewsFeedScreenState.Posts -> {
                if (state.posts.isEmpty()) {
                    _screenState.value = NewsFeedScreenState.Loading
                } else {
                    _screenState.value = state.copy(nextDataIsLoading = true)
                }
            }

            else -> {
                _screenState.value = NewsFeedScreenState.Loading
            }
        }
        viewModelScope.launch {
            runCatching {
                loadFeedPostsUseCase.invoke()
            }.onFailure {
                if (it is CancellationException) throw it
                val errorMessage = it.message ?: "Something Went Wrong"
                when (val state = _screenState.value) {
                    is NewsFeedScreenState.Posts -> {
                        if (state.posts.isNotEmpty()) {
                            _eventErrorChannel.send(errorMessage)
                        }
                    }

                    else -> {
                        _screenState.value = NewsFeedScreenState.Error(errorMessage)
                    }
                }
                Log.e("VIEW_MODEL_TAG", "Load failed ${it.message}", it)
                Log.e("VIEW_MODEL_TAG", "state: ${_screenState.value}")
            }
            val currentState = _screenState.value
            if (currentState is NewsFeedScreenState.Posts) {
                _screenState.value = currentState.copy(nextDataIsLoading = false)
            }
            isLoading = false
        }
    }

    fun onClickStatistic(item: StatisticItem, feedPost: FeedPost) {
        when (item.type) {
            StatisticType.VIEWS -> {}
            StatisticType.COMMENTS -> {}
            StatisticType.SHARES -> {}
            StatisticType.LIKES -> updateCount(item = item, feedPost = feedPost)
        }
    }

    private fun updateStats(feedPost: FeedPost, item: StatisticItem): List<StatisticItem> {
        val oldStat = feedPost.statistics
        val newStat = oldStat.toMutableList().apply {
            replaceAll { oldItem ->
                if (oldItem.type == item.type) {
                    oldItem.copy(count = oldItem.count + 1)
                } else {
                    oldItem
                }
            }
        }
        return newStat
    }

    fun delete(feedPost: FeedPost) {
        val currentState = screenState.value
        if (currentState !is NewsFeedScreenState.Posts) return
        val modifiedList = currentState.posts.toMutableList()
        modifiedList.remove(feedPost)
        _screenState.value = NewsFeedScreenState.Posts(modifiedList)
    }

    private fun updateCount(feedPost: FeedPost, item: StatisticItem) {
        val currentState = screenState.value
        if (currentState !is NewsFeedScreenState.Posts) return
        val modifiedList = currentState.posts.toMutableList().apply {
            replaceAll { oldFeedPost ->
                if (oldFeedPost.id == feedPost.id) {
                    oldFeedPost.copy(statistics = updateStats(feedPost = feedPost, item = item))
                } else {
                    oldFeedPost
                }
            }
        }
        _screenState.value = NewsFeedScreenState.Posts(modifiedList)
    }
}