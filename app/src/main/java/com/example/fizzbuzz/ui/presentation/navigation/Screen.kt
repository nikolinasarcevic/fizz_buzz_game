package com.example.fizzbuzz.ui.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen{
    @Serializable
    data object Home: Screen()

    @Serializable
    data object Play: Screen()

    @Serializable
    data object Leaderboard: Screen()

    @Serializable
    data class End(val score: Int, val isHighScore: Boolean): Screen()
}
