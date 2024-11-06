package com.quehacerenzaragoza.soydezaragoza.data.remote

import com.quehacerenzaragoza.soydezaragoza.data.model.category.Categories
import com.quehacerenzaragoza.soydezaragoza.data.model.comments.Comments
import com.quehacerenzaragoza.soydezaragoza.data.model.post.Post

abstract class RemoteDataSource {

    abstract suspend fun getPostsByCategories(): NetworkResult<List<Post>>

    abstract suspend fun getCategories(): NetworkResult<List<Categories>>

    abstract suspend fun getPostById(postId: Int): NetworkResult<Post>

    abstract suspend fun getPostComments(postId: Int): NetworkResult<List<Comments>>
}