package com.quehacerenzaragoza.soydezaragoza.data.model.category

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val count: Int,
    val id: Int,
    val name: String,
    val slug: String
)