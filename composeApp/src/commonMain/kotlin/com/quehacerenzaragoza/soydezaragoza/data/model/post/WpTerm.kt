package com.quehacerenzaragoza.soydezaragoza.data.model.post

import kotlinx.serialization.Serializable

@Serializable
data class WpTerm(
    val id: Int,
    val name: String
)