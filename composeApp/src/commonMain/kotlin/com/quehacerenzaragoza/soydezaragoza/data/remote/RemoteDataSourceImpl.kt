package com.quehacerenzaragoza.soydezaragoza.data.remote

import com.quehacerenzaragoza.soydezaragoza.data.model.category.Categories
import com.quehacerenzaragoza.soydezaragoza.data.model.comments.Comments
import com.quehacerenzaragoza.soydezaragoza.data.model.post.Post
import com.quehacerenzaragoza.soydezaragoza.util.extensions.AUTH_KEY_SOYDEZARAGOZA
import com.quehacerenzaragoza.soydezaragoza.util.extensions.BASE_URL_API
import com.quehacerenzaragoza.soydezaragoza.util.extensions.CATEGORIES_END_POINT
import com.quehacerenzaragoza.soydezaragoza.util.extensions.COMMENTS_END_POINT
import com.quehacerenzaragoza.soydezaragoza.util.extensions.POSTS_END_POINT
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headers

class RemoteDataSourceImplementation(
    private val httpClient: HttpClient
) : RemoteDataSource() {

    override suspend fun getPostsByCategories(): NetworkResult<List<Post>> {
        return try {
            val response = httpClient.get("$BASE_URL_API$POSTS_END_POINT") {
                headers {
                    append(HttpHeaders.Authorization, "Basic $AUTH_KEY_SOYDEZARAGOZA")
                }
                parameter("categories", 1)
                parameter("page", 1)
                parameter("per_page", 10)
                parameter("_embed", true)
            }

            if (response.status == HttpStatusCode.OK) {
                val posts = response.body<List<Post>>()
                NetworkResult.Success(posts)
            } else {
                NetworkResult.Error(null, "Error fetching posts: ${response.status}")
            }
        } catch (e: Exception) {
            NetworkResult.Error(null, e.message ?: "Unknown error")
        }
    }

    override suspend fun getCategories(): NetworkResult<List<Categories>> {
        return try {
            val response = httpClient.get("$BASE_URL_API$CATEGORIES_END_POINT") {
                headers {
                    append(HttpHeaders.Authorization, "Basic $AUTH_KEY_SOYDEZARAGOZA")
                }
                parameter("per_page", 50)
            }

            if (response.status == HttpStatusCode.OK) {
                val categories = response.body<List<Categories>>()
                NetworkResult.Success(categories)
            } else {
                NetworkResult.Error(null, "Error fetching categories: ${response.status}")
            }
        } catch (e: Exception) {
            NetworkResult.Error(null, e.message ?: "Unknown error")
        }
    }

    override suspend fun getPostById(postId: Int): NetworkResult<Post> {
        return try {
            val response = httpClient.get("$BASE_URL_API$POSTS_END_POINT/$postId?&_embed=tru") {
                headers {
                    append(HttpHeaders.Authorization, "Basic $AUTH_KEY_SOYDEZARAGOZA")
                }
                parameter("_embed", true)
            }

            if (response.status == HttpStatusCode.OK) {
                val post = response.body<Post>()
                NetworkResult.Success(post)
            } else {
                NetworkResult.Error(null, "Error fetching post by id: ${response.status}")
            }
        } catch (e: Exception) {
            NetworkResult.Error(null, e.message ?: "Unknown error")
        }
    }

    override suspend fun getPostComments(postId: Int): NetworkResult<List<Comments>> {
        return try {
            val response = httpClient.get("$BASE_URL_API$COMMENTS_END_POINT") {
                headers {
                    append(HttpHeaders.Authorization, "Basic $AUTH_KEY_SOYDEZARAGOZA")
                }
                parameter("post", postId)
            }

            if (response.status == HttpStatusCode.OK) {
                val comments = response.body<List<Comments>>()
                NetworkResult.Success(comments)
            } else {
                NetworkResult.Error(null, "Error fetching comments: ${response.status}")
            }
        } catch (e: Exception) {
            NetworkResult.Error(null, e.message ?: "Unknown error")
        }
    }
}