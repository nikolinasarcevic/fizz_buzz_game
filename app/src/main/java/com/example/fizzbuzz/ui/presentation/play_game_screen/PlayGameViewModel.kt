package com.example.fizzbuzz.ui.presentation.play_game_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fizzbuzz.domain.repository.NicknameRepository
import com.example.fizzbuzz.ui.presentation.home_screen.intent.HomeIntent
import com.example.fizzbuzz.ui.presentation.home_screen.intent.HomeState
import com.example.fizzbuzz.ui.presentation.play_game_screen.intent.PlayGameIntent
import com.example.fizzbuzz.ui.presentation.play_game_screen.intent.PlayGameState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlayGameViewModel @Inject constructor(
    private val repository: NicknameRepository
): ViewModel() {

    private var _state = MutableStateFlow(PlayGameState())

    val state: StateFlow<PlayGameState>
        get() = _state

    init {
        viewModelScope.launch {
            val savedScore = repository
            _state.update {  }
        }
    }

    fun processIntent(intent: PlayGameIntent) {
        when (intent) {
            PlayGameIntent.FizzClicked -> onFizzClick()
            PlayGameIntent.BuzzClicked -> onBuzzClick()
            PlayGameIntent.FizzBuzzClicked -> onFizzBuzzClick()
            PlayGameIntent.NextClicked -> onNextClick()
        }
    }

    private fun onFizzClick() {
        _state.update { it.copy(score) }
    }

    private fun onBuzzClick() {
        _state.update { it.copy(score) }
    }

    private fun onFizzBuzzClick() {
        _state.update { it.copy(score) }
    }

    private fun onNextClick() {
        _state.update { it.copy(score) }
    }
}