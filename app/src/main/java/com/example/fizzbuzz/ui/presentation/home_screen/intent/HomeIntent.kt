package com.example.fizzbuzz.ui.presentation.home_screen.intent

sealed class HomeIntent {
    data class EnterNickname(val nickname: String) : HomeIntent()
    data object SaveNickname : HomeIntent()
}
