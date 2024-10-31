package com.example.fizzbuzz.utils

import androidx.compose.runtime.Composable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun dateFormatterHelper (playedAt: LocalDateTime): String {
    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    return dateFormatter.format(playedAt)
}