package com.example.fizzbuzz.ui.presentation.play_game_screen.intent

sealed interface PlayGameIntent {
    data class FizzClicked(val remainingSeconds: Int) : PlayGameIntent
    data class BuzzClicked(val remainingSeconds: Int) : PlayGameIntent
    data class FizzBuzzClicked(val remainingSeconds: Int) : PlayGameIntent
    data class NextClicked(val remainingSeconds: Int) : PlayGameIntent
}