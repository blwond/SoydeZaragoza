package com.quehacerenzaragoza.soydezaragoza.presentation.screens.news

import com.quehacerenzaragoza.soydezaragoza.core.ViewModel
import com.quehacerenzaragoza.soydezaragoza.core.viewModelScope
import com.quehacerenzaragoza.soydezaragoza.data.remote.NetworkResult
import com.quehacerenzaragoza.soydezaragoza.domain.usecase.NewsUseCase
import com.quehacerenzaragoza.soydezaragoza.presentation.components.ObtainDataState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel class for managing and preparing data for UI [NewsScreen].
 *
 **/

class NewsViewModel(
    private val newsUseCase: NewsUseCase
) : ViewModel {

    private val _state = MutableStateFlow(NewsScreenState())
    val state = _state.asStateFlow()

    init {
        getPostsByCategories()
        getTrendingPosts()
        getCategories()
    }

    fun getPostsByCategories() {
        viewModelScope.launch {
            _state.update { it.copy(postsState = ObtainDataState.Loading) }
            newsUseCase.getPostsByCategories().collect { response ->
                when (response) {
                    is NetworkResult.Loading -> _state.update {
                        it.copy(postsState = ObtainDataState.Loading)
                    }

                    is NetworkResult.Success -> _state.update {
                        ObtainDataState.Success(response.data ?: emptyList()).let { successState ->
                            it.copy(postsState = successState)
                        }
                    }

                    is NetworkResult.Error -> _state.update {
                        it.copy(
                            postsState = ObtainDataState.Error(
                                response.message ?: "Unknown error"
                            )
                        )
                    }
                }
            }
        }
    }

    fun getCategories() {
        viewModelScope.launch {
            _state.update { it.copy(categoriesState = ObtainDataState.Loading) }
            newsUseCase.getCategories().collect { response ->
                when (response) {
                    is NetworkResult.Loading -> _state.update {
                        it.copy(categoriesState = ObtainDataState.Loading)
                    }

                    is NetworkResult.Success -> _state.update {
                        ObtainDataState.Success(response.data ?: emptyList()).let { successState ->
                            it.copy(categoriesState = successState)
                        }
                    }

                    is NetworkResult.Error -> _state.update {
                        it.copy(
                            categoriesState = ObtainDataState.Error(
                                response.message ?: "Unknown error"
                            )
                        )
                    }
                }
            }
        }
    }

    fun getTrendingPosts() {
        viewModelScope.launch {
            _state.update { it.copy(trendingPostsState = ObtainDataState.Loading) }
            newsUseCase.getTrendingPosts().collect { response ->
                when (response) {
                    is NetworkResult.Loading -> _state.update {
                        it.copy(trendingPostsState = ObtainDataState.Loading)
                    }

                    is NetworkResult.Success -> _state.update {
                        ObtainDataState.Success(response.data ?: emptyList()).let { successState ->
                            it.copy(trendingPostsState = successState)
                        }
                    }

                    is NetworkResult.Error -> _state.update {
                        it.copy(
                            trendingPostsState = ObtainDataState.Error(
                                response.message ?: "Unknown error"
                            )
                        )
                    }
                }
            }
        }
    }

    fun getPostById(postId: Int) {
        viewModelScope.launch {
            _state.update { it.copy(selectedPostState = ObtainDataState.Loading) }
            newsUseCase.getPostById(postId).collect { response ->
                when (response) {
                    is NetworkResult.Loading -> _state.update {
                        it.copy(selectedPostState = ObtainDataState.Loading)
                    }

                    is NetworkResult.Success -> {
                        response.data?.let { post ->
                            _state.update {
                                it.copy(selectedPostState = ObtainDataState.Success(post))
                            }
                        } ?: run {
                            _state.update {
                                it.copy(selectedPostState = ObtainDataState.Error("Post not found"))
                            }
                        }
                    }

                    is NetworkResult.Error -> _state.update {
                        it.copy(selectedPostState = ObtainDataState.Error(response.message ?: "Error loading post"))
                    }
                }
            }
        }
    }


    fun getPostComments(postId: Int) {
        viewModelScope.launch {
            _state.update { it.copy(commentsState = ObtainDataState.Loading) }
            newsUseCase.getPostComments(postId).collect { response ->
                when (response) {
                    is NetworkResult.Loading -> _state.update {
                        it.copy(commentsState = ObtainDataState.Loading)
                    }

                    is NetworkResult.Success -> _state.update {
                        ObtainDataState.Success(response.data ?: emptyList()).let { successState ->
                            it.copy(commentsState = successState)
                        }
                    }

                    is NetworkResult.Error -> _state.update {
                        it.copy(commentsState = ObtainDataState.Error(response.message ?: "Error loading comments"))
                    }
                }
            }
        }
    }

}