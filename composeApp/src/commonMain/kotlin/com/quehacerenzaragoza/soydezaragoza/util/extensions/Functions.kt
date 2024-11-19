package com.quehacerenzaragoza.soydezaragoza.util.extensions

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

fun String.removeHtmlTags(): String {
    return this.replace(Regex("<.*?>"), "").trim()
}

fun getRelativeTimeFromNow(dateIso: String): String {
    val madridTimeZone = TimeZone.of("Europe/Madrid")

    val targetInstant = try {
        Instant.parse(dateIso)
    } catch (e: Exception) {
        LocalDateTime.parse(dateIso).toInstant(madridTimeZone)
    }

    val nowInstant = Clock.System.now()

    val duration: Duration = nowInstant - targetInstant

    return when {
        duration.inWholeMinutes < 1 -> "Hace un momento"
        duration.inWholeMinutes < 60 -> "Hace ${duration.inWholeMinutes}m"
        duration.inWholeHours < 24 -> "Hace ${duration.inWholeHours}h"
        duration.inWholeDays < 7 -> "Hace ${duration.inWholeDays}d"
        else -> {
            val weeks = duration.inWholeDays / 7
            "Hace ${weeks} sem"
        }
    }
}