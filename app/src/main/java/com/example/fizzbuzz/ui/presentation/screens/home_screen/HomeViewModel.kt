package com.example.fizzbuzz.ui.presentation.screens.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fizzbuzz.domain.repository.NicknameRepository
import com.example.fizzbuzz.ui.presentation.screens.home_screen.intent.HomeIntent
import com.example.fizzbuzz.ui.presentation.screens.home_screen.intent.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: NicknameRepository
) : ViewModel() {

    private var _state = MutableStateFlow(HomeState())

    val state: StateFlow<HomeState>
        get() = _state

    init {
        loadNickname()
    }

    fun processIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.EnterNickname -> enterNickname(intent.nickname)
            is HomeIntent.SaveNickname -> saveNickname(intent.nickname)
            HomeIntent.ShowExitDialog -> showExitDialog()
            HomeIntent.DismissExitDialog -> dismissExitDialog()
            HomeIntent.ShowNicknameDialog -> showNicknameDialog()
            HomeIntent.DismissNicknameDialog -> dismissNicknameDialog()
        }
    }

    private fun loadNickname()
    {
        viewModelScope.launch {
            val savedNickname = repository.getNickname()
            _state.update { it.copy(nickname = savedNickname) }
        }
    }

    private fun enterNickname(nickname: String) {
        saveNickname(nickname)
        _state.update { it.copy(nickname = nickname) }
    }

    private fun saveNickname(nickname: String) {
        viewModelScope.launch {
            repository.saveNickname(nickname)
        }
    }

    private fun showExitDialog() {
        _state.update { it.copy(exitDialog = true) }
    }

    private fun dismissExitDialog() {
        _state.update { it.copy(exitDialog = false) }
    }

    private fun showNicknameDialog() {
        _state.update { it.copy(nicknameDialog = true) }
    }

    private fun dismissNicknameDialog() {
        _state.update { it.copy(nicknameDialog = false) }
    }
}