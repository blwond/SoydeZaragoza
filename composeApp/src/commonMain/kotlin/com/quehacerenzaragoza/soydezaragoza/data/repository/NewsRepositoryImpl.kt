package com.quehacerenzaragoza.soydezaragoza.data.repository

import com.quehacerenzaragoza.soydezaragoza.data.model.category.Categories
import com.quehacerenzaragoza.soydezaragoza.data.model.comments.Comments
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

    override suspend fun getCategories(): Flow<NetworkResult<List<Categories>>> {
        return flow {
            emit(NetworkResult.Loading(true))
            val response = remoteDataSource.getCategories()
            emit(response)
        }
    }

    override suspend fun getTrendingPosts(): Flow<NetworkResult<List<Post>>> {
        return flow {
            emit(NetworkResult.Loading(true))
            val response = remoteDataSource.getTrendingPosts()
            emit(response)
        }
    }

    override suspend fun getPostById(postId: Int): Flow<NetworkResult<Post>> {
        return flow {
            emit(NetworkResult.Loading(true))
            val response = remoteDataSource.getPostById(postId)
            emit(response)
        }
    }

    override suspend fun getPostComments(postId: Int): Flow<NetworkResult<List<Comments>>> {
        return flow {
            emit(NetworkResult.Loading(true))
            val response = remoteDataSource.getPostComments(postId)
            emit(response)
        }
    }
}