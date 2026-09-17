package com.example.firstcomposeapp

import androidx.lifecycle.ViewModel
import com.example.firstcomposeapp.domain.FeedPost
import com.example.firstcomposeapp.domain.StatisticItem
import com.example.firstcomposeapp.domain.StatisticType
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {

    private val initialList = mutableListOf<FeedPost>().apply {
        repeat(100){
            add(FeedPost(publicationDate = it.toString()))
        }
    }
    private val _feedPost = MutableStateFlow<List<FeedPost>>(initialList)
    val feedPost = _feedPost.asStateFlow()

    fun onClickStatistic(item: StatisticItem, feedPost: FeedPost){
        when(item.type){
            StatisticType.VIEWS -> updateCount(item = item, feedPost = feedPost)
            StatisticType.COMMENTS -> { }
            StatisticType.SHARES -> { }
            StatisticType.LIKES -> { }
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
        val modifiedList = _feedPost.value.toMutableList()
        modifiedList.remove(feedPost)
        _feedPost.value = modifiedList
    }
    private fun updateCount(feedPost: FeedPost, item: StatisticItem){
        val modifiedList = _feedPost.value.toMutableList().apply {
            replaceAll {oldFeedPost ->
                if (oldFeedPost === feedPost){
                    oldFeedPost.copy(statistics = updateStats(feedPost = feedPost, item = item))
                }else{
                    oldFeedPost
                }
            }
        }
        _feedPost.value = modifiedList
    }
}