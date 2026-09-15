package com.example.firstcomposeapp

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class MainViewModelInstagram @Inject constructor(): ViewModel() {
    private val _isFollowing = MutableStateFlow(false)
    val isFollowing = _isFollowing.asStateFlow()

    fun changeFollowingStatus(){
        _isFollowing.value = !isFollowing.value
    }
}