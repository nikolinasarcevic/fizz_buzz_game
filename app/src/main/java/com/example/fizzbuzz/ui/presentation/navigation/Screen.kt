package com.example.fizzbuzz.ui.presentation.navigation


sealed class Screen(val route: String) {
    data object Home: Screen("home")
    data object Play: Screen("play")
    data object End: Screen("End")
    data object Leaderboard: Screen("history")
}