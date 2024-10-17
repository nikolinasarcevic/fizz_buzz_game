package com.example.fizzbuzz.ui.presentation.home_screen.components

import android.content.Context

fun saveNicknameToPreferences(context: Context, nickname: String) {
    val sharedPref = context.getSharedPreferences("nickname_prefs", Context.MODE_PRIVATE)
    with(sharedPref.edit()) {
        putString("nickname", nickname)
        apply()
    }
}