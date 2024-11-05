package com.quehacerenzaragoza.soydezaragoza

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform