package com.example.firstcomposeapp

import androidx.lifecycle.ViewModel
import com.example.firstcomposeapp.domain.FeedPost
import com.example.firstcomposeapp.domain.PostComment
import com.example.firstcomposeapp.domain.StatisticItem
import com.example.firstcomposeapp.domain.StatisticType
import com.example.firstcomposeapp.ui.theme.HomeScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {
    private val initialList = mutableListOf<FeedPost>().apply {
        repeat(10){
            add(FeedPost(id = it, publicationDate = it.toString()))
        }
    }
    private val comments = mutableListOf<PostComment>().apply {
        repeat(10){
            add(PostComment(id = it))
        }
    }


    val initialState = HomeScreenState.Posts(initialList)
    private val _screenState = MutableStateFlow<HomeScreenState>(initialState)
    val screenState = _screenState.asStateFlow()

    var savedState: HomeScreenState = initialState
    fun showComments(feedPost: FeedPost){
        savedState = _screenState.value
        _screenState.value = HomeScreenState.Comments(feedPost, comments)
    }

    fun closeComments(){
        _screenState.value = savedState
    }

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
        if (currentState !is HomeScreenState.Posts) return
        val modifiedList = currentState.posts.toMutableList()
        modifiedList.remove(feedPost)
        _screenState.value = HomeScreenState.Posts(modifiedList)
    }
    private fun updateCount(feedPost: FeedPost, item: StatisticItem){
        val currentState = screenState.value
        if (currentState !is HomeScreenState.Posts) return
        val modifiedList = currentState.posts.toMutableList().apply {
            replaceAll {oldFeedPost ->
                if (oldFeedPost.id == feedPost.id){
                    oldFeedPost.copy(statistics = updateStats(feedPost = feedPost, item = item))
                }else{
                    oldFeedPost
                }
            }
        }
        _screenState.value = HomeScreenState.Posts(modifiedList)
    }
}