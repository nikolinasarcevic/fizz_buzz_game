package com.example.fizzbuzz.ui.presentation.screens.home_screen.intent

data class HomeState(
    val nickname: String? = null,
    val exitDialog: Boolean = false,
    val nicknameDialog: Boolean = false
)
