package com.example.firstcomposeapp.domain.usecases

import com.example.firstcomposeapp.domain.repositories.LemmyRepository
import javax.inject.Inject

class LoadFeedPostsUseCase @Inject constructor(
    private val repository: LemmyRepository
) {
    suspend operator fun invoke() = repository.loadNextData()
}