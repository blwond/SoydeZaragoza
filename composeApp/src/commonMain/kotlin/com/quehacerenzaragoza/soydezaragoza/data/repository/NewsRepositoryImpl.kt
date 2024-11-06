package com.quehacerenzaragoza.soydezaragoza.data.repository

import com.quehacerenzaragoza.soydezaragoza.data.model.post.Post
import com.quehacerenzaragoza.soydezaragoza.data.remote.NetworkResult
import com.quehacerenzaragoza.soydezaragoza.data.remote.news.NewsRemoteDataSource
import com.quehacerenzaragoza.soydezaragoza.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Repository class for fetching News data from server
 * */
class NewsRepositoryImpl (private val remoteDataSource: NewsRemoteDataSource) : NewsRepository {
    override suspend fun getPostsByCategories(): Flow<NetworkResult<List<Post>>> {
        return flow {
            emit(NetworkResult.Loading(true))
            val response = remoteDataSource.getPostsByCategories()
            emit(response)
        }
    }
}