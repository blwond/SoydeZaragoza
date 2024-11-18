package com.quehacerenzaragoza.soydezaragoza.data.model.comment

import kotlinx.serialization.Serializable

@Serializable
data class Comment(
    val author_name: String,
    val content: String,
    val date: String,
    val id: Int,
    val post: Int
)