package com.quehacerenzaragoza.soydezaragoza.data.model.comments

import kotlinx.serialization.Serializable

@Serializable
data class Comments(
    val author_name: String,
    val content: String,
    val date: String,
    val id: Int,
    val post: Int
)