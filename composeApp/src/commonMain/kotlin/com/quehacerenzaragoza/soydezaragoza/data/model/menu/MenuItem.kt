package com.quehacerenzaragoza.soydezaragoza.data.model.menu

import com.quehacerenzaragoza.soydezaragoza.data.model.post.Title
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuItem(
    val id: Int,
    val title: Title,
    val name: String,
    val slug: String,
    @SerialName("object")
    val objectType: String
)