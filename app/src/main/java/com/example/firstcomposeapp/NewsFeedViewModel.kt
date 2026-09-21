package com.example.firstcomposeapp

import androidx.lifecycle.ViewModel
import com.example.firstcomposeapp.domain.FeedPost
import com.example.firstcomposeapp.domain.StatisticItem
import com.example.firstcomposeapp.domain.StatisticType
import com.example.firstcomposeapp.ui.theme.NewsFeedScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class NewsFeedViewModel @Inject constructor(): ViewModel() {
    private val initialList = mutableListOf<FeedPost>().apply {
        repeat(10){
            add(FeedPost(id = it, publicationDate = it.toString()))
        }
    }
    val initialState = NewsFeedScreenState.Posts(initialList)
    private val _screenState = MutableStateFlow<NewsFeedScreenState>(initialState)
    val screenState = _screenState.asStateFlow()


    fun onClickStatistic(item: StatisticItem, feedPost: FeedPost){
        when(item.type){
            StatisticType.VIEWS -> { }
            StatisticType.COMMENTS -> { }
            StatisticType.SHARES -> { }
            StatisticType.LIKES -> updateCount(item = item, feedPost = feedPost)
        }
    }

    private fun updateStats(feedPost: FeedPost, item: StatisticItem): List<StatisticItem>{
        val oldStat = feedPost.statistics
        val newStat = oldStat.toMutableList().apply {
            replaceAll{oldItem ->
                if (oldItem.type == item.type){
                    oldItem.copy(count = oldItem.count + 1)
                }else{
                    oldItem
                }
            }
        }
        return newStat
    }

    fun delete(feedPost: FeedPost){
        val currentState = screenState.value
        if (currentState !is NewsFeedScreenState.Posts) return
        val modifiedList = currentState.posts.toMutableList()
        modifiedList.remove(feedPost)
        _screenState.value = NewsFeedScreenState.Posts(modifiedList)
    }
    private fun updateCount(feedPost: FeedPost, item: StatisticItem){
        val currentState = screenState.value
        if (currentState !is NewsFeedScreenState.Posts) return
        val modifiedList = currentState.posts.toMutableList().apply {
            replaceAll {oldFeedPost ->
                if (oldFeedPost.id == feedPost.id){
                    oldFeedPost.copy(statistics = updateStats(feedPost = feedPost, item = item))
                }else{
                    oldFeedPost
                }
            }
        }
        _screenState.value = NewsFeedScreenState.Posts(modifiedList)
    }
}