package com.example.fizzbuzz.utils

import androidx.compose.runtime.Composable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun timeFormatterHelper(playedAt: LocalDateTime): String {
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    return timeFormatter.format(playedAt)
}