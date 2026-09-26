package com.example.firstcomposeapp.domain.usecases

import com.example.firstcomposeapp.domain.repositories.LemmyRepository
import javax.inject.Inject

class GetFeedPostsUseCase @Inject constructor(
    private val repository: LemmyRepository
) {
    operator fun invoke() = repository.getFeedPosts()
}