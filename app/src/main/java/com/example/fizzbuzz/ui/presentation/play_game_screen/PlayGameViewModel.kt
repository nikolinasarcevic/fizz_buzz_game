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
import java.time.LocalDateTime
import javax.inject.Inject
import kotlin.math.ceil

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


    fun processIntent(intent: PlayGameIntent) {
        when (intent) {
            is PlayGameIntent.FizzClicked -> onFizzClick(intent.remainingSeconds)
            is PlayGameIntent.BuzzClicked -> onBuzzClick(intent.remainingSeconds)
            is PlayGameIntent.FizzBuzzClicked -> onFizzBuzzClick(intent.remainingSeconds)
            is PlayGameIntent.NextClicked -> onNextClick(intent.remainingSeconds)
        }
    }

    private fun endGame() {
        viewModelScope.launch {
            val latestNickname = nicknameRepository.getNickname() ?: "Player"

            val existingScore = dao.getScoreByNickname(latestNickname)

            if (existingScore != null) {
                if (_state.value.score > existingScore.scoreValue) {
                    val updatedScore =
                        existingScore.copy(scoreValue = _state.value.score, playedAt = LocalDateTime.now())
                    dao.saveScore(updatedScore)
                    _state.value.isHighScore = true
                }
            } else {
                _state.value.isHighScore = false
                val newScore = Score(
                    nickname = latestNickname,
                    scoreValue = _state.value.score,
                    playedAt = LocalDateTime.now()
                )
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

    private fun onFizzClick(remainingSeconds: Long) {
        val points = ceil(remainingSeconds / 1000.0).toInt()
        if (_state.value.currentNumber % 3 == 0 && _state.value.currentNumber % 5 != 0) {
            _state.update {
                it.copy(
                    score = it.score + points,
                    currentNumber = it.currentNumber + 1
                )
            }
        } else {
            endGame()
        }
    }

    private fun onBuzzClick(remainingSeconds: Long) {
        if (_state.value.currentNumber % 5 == 0 && _state.value.currentNumber % 3 != 0) {
            val points = ceil(remainingSeconds / 1000.0).toInt()
            _state.update {
                it.copy(
                    score = it.score + points,
                    currentNumber = it.currentNumber + 1
                )
            }
        } else {
            endGame()
        }
    }

    private fun onFizzBuzzClick(remainingSeconds: Long) {
        if (_state.value.currentNumber % 15 == 0) {
            val points = ceil(remainingSeconds / 1000.0).toInt()
            _state.update {
                it.copy(
                    score = it.score + points,
                    currentNumber = it.currentNumber + 1
                )
            }
        } else {
            endGame()
        }
    }

    private fun onNextClick(remainingSeconds: Long) {
        if (_state.value.currentNumber % 3 != 0 && _state.value.currentNumber % 5 != 0) {
            val points = ceil(remainingSeconds / 1000.0).toInt()
            _state.update {
                it.copy(
                    score = it.score + points,
                    currentNumber = it.currentNumber + 1
                )
            }
        } else {
            endGame()
        }
    }
}
