package com.quehacerenzaragoza.soydezaragoza.data.remote.news

import com.quehacerenzaragoza.soydezaragoza.data.model.category.Category
import com.quehacerenzaragoza.soydezaragoza.data.model.comment.Comment
import com.quehacerenzaragoza.soydezaragoza.data.model.post.Post
import com.quehacerenzaragoza.soydezaragoza.data.remote.NetworkResult

abstract class NewsRemoteDataSource {

    abstract suspend fun getPostsByCategories(): NetworkResult<List<Post>>

    abstract suspend fun getCategories(): NetworkResult<List<Category>>

    abstract suspend fun getTrendingPosts(): NetworkResult<List<Post>>

    abstract suspend fun getPostById(postId: Int): NetworkResult<Post>

    abstract suspend fun getPostComments(postId: Int): NetworkResult<List<Comment>>
}