package com.quehacerenzaragoza.soydezaragoza.presentation.screens.news

import com.quehacerenzaragoza.soydezaragoza.data.model.category.Category
import com.quehacerenzaragoza.soydezaragoza.data.model.comment.Comment
import com.quehacerenzaragoza.soydezaragoza.data.model.post.Post
import com.quehacerenzaragoza.soydezaragoza.presentation.components.ObtainDataState

data class NewsScreenState(
    val postsState: ObtainDataState<List<Post>> = ObtainDataState.Idle,
    val categoriesState: ObtainDataState<List<Category>> = ObtainDataState.Idle,
    val trendingPostsState: ObtainDataState<List<Post>> = ObtainDataState.Idle,
    val selectedPostState: ObtainDataState<Post> = ObtainDataState.Idle,
    val commentsState: ObtainDataState<List<Comment>> = ObtainDataState.Idle,
    val selectedCategory: Category? = null,
    val onCategorySelected: (Category) -> Unit = {}
)