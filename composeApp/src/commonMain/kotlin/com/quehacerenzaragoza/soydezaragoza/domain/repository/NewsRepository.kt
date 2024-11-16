package com.quehacerenzaragoza.soydezaragoza.domain.repository

import com.quehacerenzaragoza.soydezaragoza.data.model.category.Categories
import com.quehacerenzaragoza.soydezaragoza.data.model.comments.Comments
import com.quehacerenzaragoza.soydezaragoza.data.model.post.Post
import com.quehacerenzaragoza.soydezaragoza.data.remote.NetworkResult
import kotlinx.coroutines.flow.Flow

/**
 * interface to make an interaction between [NewsRepositoryImpl] & [NewsUseCase]
 * */
interface NewsRepository {
    suspend fun getPostsByCategories(): Flow<NetworkResult<List<Post>>>
    suspend fun getCategories(): Flow<NetworkResult<List<Categories>>>
    suspend fun getTrendingPosts(): Flow<NetworkResult<List<Post>>>
    suspend fun getPostById(postId: Int): Flow<NetworkResult<Post>>
    suspend fun getPostComments(postId: Int): Flow<NetworkResult<List<Comments>>>
}