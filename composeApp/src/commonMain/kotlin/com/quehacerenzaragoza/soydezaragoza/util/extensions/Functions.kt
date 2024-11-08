package com.quehacerenzaragoza.soydezaragoza.util.extensions

fun String.removeHtmlTags(): String {
    return this.replace(Regex("<.*?>"), "").trim()
}