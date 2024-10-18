package com.example.fizzbuzz.ui.presentation.home_screen.intent

sealed interface HomeIntent {
    data class EnterNickname(val nickname: String) : HomeIntent
    data class SaveNickname(val nickname: String) : HomeIntent
    data object ShowExitDialog : HomeIntent
    data object DismissExitDialog : HomeIntent
    data object ShowNicknameDialog : HomeIntent
    data object DismissNicknameDialog : HomeIntent
}
