package com.quehacerenzaragoza.soydezaragoza.domain.usecase

import com.quehacerenzaragoza.soydezaragoza.data.model.category.Category
import com.quehacerenzaragoza.soydezaragoza.data.model.comment.Comment
import com.quehacerenzaragoza.soydezaragoza.data.model.post.Post
import com.quehacerenzaragoza.soydezaragoza.data.remote.NetworkResult
import com.quehacerenzaragoza.soydezaragoza.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

/**
 * An interactor class that executes the implementation of [NewsViewModel]
 */

class NewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun getPostsByCategories(categoryId: Int): Flow<NetworkResult<List<Post>>> {
        return newsRepository.getPostsByCategories(categoryId)
    }
    suspend fun getCategories(): Flow<NetworkResult<List<Category>>> {
        return newsRepository.getCategories()
    }
    suspend fun getTrendingPosts(): Flow<NetworkResult<List<Post>>> {
        return newsRepository.getTrendingPosts()
    }
    suspend fun getPostById(postId: Int): Flow<NetworkResult<Post>> {
        return newsRepository.getPostById(postId)
    }
    suspend fun getPostComments(postId: Int): Flow<NetworkResult<List<Comment>>> {
        return newsRepository.getPostComments(postId)
    }
}