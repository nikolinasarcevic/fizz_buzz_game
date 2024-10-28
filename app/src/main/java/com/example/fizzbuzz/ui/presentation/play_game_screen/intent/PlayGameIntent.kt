package com.example.fizzbuzz.ui.presentation.play_game_screen.intent

sealed interface PlayGameIntent {
    data class FizzClicked(val remainingSeconds: Long) : PlayGameIntent
    data class BuzzClicked(val remainingSeconds: Long) : PlayGameIntent
    data class FizzBuzzClicked(val remainingSeconds: Long) : PlayGameIntent
    data class NextClicked(val remainingSeconds: Long) : PlayGameIntent
}