package com.example.fizzbuzz.ui.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen{
    @Serializable
    object Home

    @Serializable
    object Play

    @Serializable
    object Leaderboard

    @Serializable
    object End
}
