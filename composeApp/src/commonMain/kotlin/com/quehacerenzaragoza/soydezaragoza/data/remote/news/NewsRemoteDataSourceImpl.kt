package com.quehacerenzaragoza.soydezaragoza.data.remote.news

import com.quehacerenzaragoza.soydezaragoza.data.model.category.Categories
import com.quehacerenzaragoza.soydezaragoza.data.model.comments.Comments
import com.quehacerenzaragoza.soydezaragoza.data.model.post.Post
import com.quehacerenzaragoza.soydezaragoza.data.remote.NetworkResult
import com.quehacerenzaragoza.soydezaragoza.util.extensions.AUTH_KEY_SOYDEZARAGOZA
import com.quehacerenzaragoza.soydezaragoza.util.extensions.BASE_URL_API
import com.quehacerenzaragoza.soydezaragoza.util.extensions.CATEGORIES_END_POINT
import com.quehacerenzaragoza.soydezaragoza.util.extensions.COMMENTS_END_POINT
import com.quehacerenzaragoza.soydezaragoza.util.extensions.POSTS_END_POINT
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.request
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headers

class NewsRemoteDataSourceImplementation(
    private val httpClient: HttpClient
) : NewsRemoteDataSource() {

    private suspend inline fun <reified T> getRequest(endpoint: String, params: Map<String, Any>? = null): NetworkResult<T> {
        return try {
            val response = httpClient.get("$BASE_URL_API$endpoint") {
                headers {
                    append(HttpHeaders.Authorization, AUTH_KEY_SOYDEZARAGOZA)
                }
                params?.forEach { (key, value) -> parameter(key, value) }
            }

            if (response.status == HttpStatusCode.OK) {
                val body = response.body<T>()
                NetworkResult.Success(body)
            } else {
                NetworkResult.Error(null, "Error fetching data: ${response.status}")
            }
        } catch (e: Exception) {
            NetworkResult.Error(null, e.message ?: "Unknown error")
        }
    }

    override suspend fun getPostsByCategories(): NetworkResult<List<Post>> {
        val params = mapOf(
            //"categories" to 1,
            "page" to 1,
            "per_page" to 10,
            "_embed" to true,
            "order" to "desc",
            "orderby" to "date"
        )
        return getRequest(POSTS_END_POINT, params)
    }

    override suspend fun getCategories(): NetworkResult<List<Categories>> {
        val params = mapOf("per_page" to 50)
        return getRequest(CATEGORIES_END_POINT, params)
    }

    override suspend fun getTrendingPosts(): NetworkResult<List<Post>> {
        val params = mapOf(
            "_embed" to true,
            "tags" to 232
        )
        return getRequest(POSTS_END_POINT, params)
    }

    override suspend fun getPostById(postId: Int): NetworkResult<Post> {
        val params = mapOf("_embed" to true)
        return getRequest("${POSTS_END_POINT}/$postId", params)
    }

    override suspend fun getPostComments(postId: Int): NetworkResult<List<Comments>> {
        val params = mapOf("post" to postId)
        return getRequest(COMMENTS_END_POINT, params)
    }

}