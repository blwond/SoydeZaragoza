package com.quehacerenzaragoza.soydezaragoza.presentation.screens.news

import com.quehacerenzaragoza.soydezaragoza.data.model.category.Categories
import com.quehacerenzaragoza.soydezaragoza.data.model.comments.Comments
import com.quehacerenzaragoza.soydezaragoza.data.model.post.Post
import com.quehacerenzaragoza.soydezaragoza.presentation.components.ObtainDataState

data class NewsScreenState(
    val postsState: ObtainDataState<List<Post>> = ObtainDataState.Idle,
    val categoriesState: ObtainDataState<List<Categories>> = ObtainDataState.Idle,
    val selectedPostState: ObtainDataState<Post> = ObtainDataState.Idle,
    val commentsState: ObtainDataState<List<Comments>> = ObtainDataState.Idle
)