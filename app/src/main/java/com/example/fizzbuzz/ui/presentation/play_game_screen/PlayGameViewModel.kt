package com.example.fizzbuzz.ui.presentation.play_game_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fizzbuzz.database.Score
import com.example.fizzbuzz.database.ScoreDao
import com.example.fizzbuzz.domain.repository.NicknameRepository
import com.example.fizzbuzz.ui.presentation.play_game_screen.intent.PlayGameIntent
import com.example.fizzbuzz.ui.presentation.play_game_screen.intent.PlayGameState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PlayGameViewModel @Inject constructor(
    private val dao: ScoreDao,
    private val nicknameRepository: NicknameRepository
) : ViewModel() {

    private var _state = MutableStateFlow(PlayGameState())

    val state: StateFlow<PlayGameState>
        get() = _state


    fun processIntent(intent: PlayGameIntent) {
        if (_state.value.gameOver) return

        when (intent) {
            PlayGameIntent.FizzClicked -> onFizzClick()
            PlayGameIntent.BuzzClicked -> onBuzzClick()
            PlayGameIntent.FizzBuzzClicked -> onFizzBuzzClick()
            PlayGameIntent.NextClicked -> onNextClick()
        }
    }

    private fun endGame() {
        viewModelScope.launch {
            val latestNickname = nicknameRepository.getNickname() ?: "Player"

            dao.saveScore(Score(nickname = latestNickname, scoreValue = _state.value.score))

            _state.update {
                it.copy(currentNumber = 1, score = 0, gameOver = true)
            }
        }
    }

    private fun onFizzClick() {
        if (_state.value.currentNumber % 3 == 0 && _state.value.currentNumber % 5 != 0) {
            _state.update {
                val newScore = it.score + 1
                it.copy(score = newScore, currentNumber = it.currentNumber + 1)
            }

        } else {
            endGame()
        }
    }

    private fun onBuzzClick() {
        _state.update {
            val newScore = if (it.currentNumber % 5 == 0 && it.currentNumber % 3 != 0) {
                it.score + 1
            } else {
                endGame()
                return
            }

            it.copy(score = newScore, currentNumber = it.currentNumber + 1)
        }.also { newState ->

            Timber.d("New State after buzz Click: $newState")
        }
    }

    private fun onFizzBuzzClick() {
        _state.update {
            val newScore = if (it.currentNumber % 15 == 0) {
                it.score + 1
            } else {
                endGame()
                return
            }

            it.copy(score = newScore, currentNumber = it.currentNumber + 1)
        }.also { newState ->

            Timber.d("New State after FizzBuzz Click: $newState")
        }
    }

    private fun onNextClick() {
        _state.update {
            val newScore = if (it.currentNumber % 3 != 0 && it.currentNumber % 5 != 0) {
                it.score + 1
            } else {
                Timber.d("Game over for number: ${it.score}")
                endGame()
                return
            }
            it.copy(score = newScore, currentNumber = it.currentNumber + 1)
        }
    }
}