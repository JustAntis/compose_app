package com.example.firstcomposeapp.data.mappers

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

object DateFormatter {
    fun String.toFormattedDate(): String {
        return try {
            DATE_FORMATTER.format(Instant.parse(this))
        } catch (e: Exception) {
            this
        }
    }

    private val DATE_FORMATTER: DateTimeFormatter =
        DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm", Locale.getDefault())
            .withZone(ZoneId.systemDefault())

}