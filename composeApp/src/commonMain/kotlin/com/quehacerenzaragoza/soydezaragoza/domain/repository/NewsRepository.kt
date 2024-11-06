package com.quehacerenzaragoza.soydezaragoza.domain.repository

import com.quehacerenzaragoza.soydezaragoza.data.model.post.Post
import com.quehacerenzaragoza.soydezaragoza.data.remote.NetworkResult
import kotlinx.coroutines.flow.Flow

/**
 * interface to make an interaction between [NewsRepositoryImpl] & [NewsUseCase]
 * */
interface NewsRepository {
    suspend fun getPostsByCategories(): Flow<NetworkResult<List<Post>>>
}