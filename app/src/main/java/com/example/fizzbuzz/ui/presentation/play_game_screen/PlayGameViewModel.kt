package com.example.fizzbuzz.ui.presentation.play_game_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fizzbuzz.database.Score
import com.example.fizzbuzz.database.ScoreDao
import com.example.fizzbuzz.domain.repository.NicknameRepository
import com.example.fizzbuzz.ui.presentation.play_game_screen.intent.PlayGameEvent
import com.example.fizzbuzz.ui.presentation.play_game_screen.intent.PlayGameIntent
import com.example.fizzbuzz.ui.presentation.play_game_screen.intent.PlayGameState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayGameViewModel @Inject constructor(
    private val dao: ScoreDao,
    private val nicknameRepository: NicknameRepository
) : ViewModel() {

    private val _gameOverChannel = Channel<PlayGameEvent>()
    val gameOverChannel = _gameOverChannel.receiveAsFlow()

    private var _state = MutableStateFlow(PlayGameState())

    val state: StateFlow<PlayGameState>
        get() = _state


    private val startTime = System.currentTimeMillis()

    fun processIntent(intent: PlayGameIntent) {
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

            val existingScore = dao.getScoreByNickname(latestNickname)

            if (existingScore != null) {

                if (_state.value.score > existingScore.scoreValue) {

                    val updatedScore = existingScore.copy(scoreValue = _state.value.score)
                    dao.saveScore(updatedScore)
                }
            } else {

                val newScore = Score(nickname = latestNickname, scoreValue = _state.value.score)
                dao.saveScore(newScore)
            }

            _gameOverChannel.send(PlayGameEvent.GameOver)

            _state.update {
                it.copy(currentNumber = 1, score = 0)
            }
        }
    }

    fun triggerGameOverEvent() {
        endGame()
    }

    private fun calculateScore(): Int {
        val elapsedTime = ((System.currentTimeMillis() - startTime) / 1000).toInt()
        return when (elapsedTime) {
            in 1..1 -> 1
            in 2..2 -> 2
            in 3..3 -> 3
            in 4..4 -> 4
            in 5..5 -> 5
            else -> 0
        }
    }

    private fun onFizzClick() {
        if (_state.value.currentNumber % 3 == 0 && _state.value.currentNumber % 5 != 0) {
            _state.update {
                it.copy(score = it.score + 1, currentNumber = it.currentNumber + 1)
            }
        } else {
            endGame()
        }
    }

    private fun onBuzzClick() {
        if (_state.value.currentNumber % 5 == 0 && _state.value.currentNumber % 3 != 0) {
            _state.update {
                it.copy(score = it.score + 1, currentNumber = it.currentNumber + 1)
            }
        } else {
            endGame()
        }
    }

    private fun onFizzBuzzClick() {
        if (_state.value.currentNumber % 15 == 0) {
            _state.update {
                it.copy(score = it.score + 1, currentNumber = it.currentNumber + 1)
            }
        } else {
            endGame()
        }
    }

    private fun onNextClick() {
        if (_state.value.currentNumber % 3 != 0 && _state.value.currentNumber % 5 != 0) {
            _state.update {
                it.copy(score = it.score + 1, currentNumber = it.currentNumber + 1)
            }
        } else {
            endGame()
        }
    }
}
