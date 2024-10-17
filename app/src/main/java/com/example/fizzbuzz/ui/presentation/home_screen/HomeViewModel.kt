package com.example.fizzbuzz.ui.presentation.home_screen

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.fizzbuzz.ui.presentation.home_screen.intent.HomeIntent
import com.example.fizzbuzz.ui.presentation.home_screen.intent.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel  @Inject constructor(@ApplicationContext private val context: Context) : ViewModel() {

    private val sharedPref = context.getSharedPreferences("nickname_prefs", Context.MODE_PRIVATE)
    private var _state = MutableStateFlow(HomeState())

    val state: StateFlow<HomeState>
        get() = _state

    fun processIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.EnterNickname -> enterNickname(intent.nickname)
            is HomeIntent.SaveNickname -> saveNickname()
        }
    }

    private fun enterNickname(nickname: String) {

        _state.update { it.copy(nickname=nickname) }
    }

    private fun saveNickname() {
        val nickname = _state.value.nickname
        with(sharedPref.edit()) {
            putString("nickname", nickname)
            apply()
        }
    }
}