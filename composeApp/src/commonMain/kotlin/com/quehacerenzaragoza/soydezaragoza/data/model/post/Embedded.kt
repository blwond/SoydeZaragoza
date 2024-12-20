package com.quehacerenzaragoza.soydezaragoza.data.model.post


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Embedded(
    @SerialName("wp:featuredmedia")
    val wp_FeaturedMedia: List<WpFeaturedmedia>? = null,
    @SerialName("wp:term")
    val wp_Term: List<List<WpTerm>>? = null
)