package com.example.fizzbuzz.ui.presentation.play_game_screen.intent

sealed interface PlayGameIntent {
    data object FizzClicked : PlayGameIntent
    data object BuzzClicked : PlayGameIntent
    data object FizzBuzzClicked : PlayGameIntent
    data object NextClicked : PlayGameIntent
}