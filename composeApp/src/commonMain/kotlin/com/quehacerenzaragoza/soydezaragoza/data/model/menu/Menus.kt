package com.quehacerenzaragoza.soydezaragoza.data.model.menu

import kotlinx.serialization.Serializable

@Serializable
data class Menus(
    val id: Int,
    val name: String,
    val slug: String
)