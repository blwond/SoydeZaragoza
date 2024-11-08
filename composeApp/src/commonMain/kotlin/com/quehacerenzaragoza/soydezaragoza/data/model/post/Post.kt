package com.quehacerenzaragoza.soydezaragoza.data.model.post

import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val _embedded: Embedded,
    val content: Content,
    val date: String,
    val id: Int,
    val title: Title,
    val primary_category: String,
    val primary_category_id: Int,
    val excerpt: Excerpt
)