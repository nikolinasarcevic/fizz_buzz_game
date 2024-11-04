package com.example.fizzbuzz.ui.presentation.screens.end_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fizzbuzz.domain.repository.NicknameRepository
import com.example.fizzbuzz.domain.repository.ScoreRepository
import com.example.fizzbuzz.ui.presentation.screens.end_screen.intent.EndScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EndViewModel @Inject constructor(
    private val scoreRepository: ScoreRepository,
    private val nicknameRepository: NicknameRepository
) : ViewModel() {

    private var _state = MutableStateFlow(EndScreenState())

    val state: StateFlow<EndScreenState>
        get() = _state

    init {
        getHighestScoreForNickname()
    }

    private fun getHighestScoreForNickname() {
        viewModelScope.launch {

            val highestScore = scoreRepository.getHighestScoreForNickname(nicknameRepository.getNickname() ?: "Player")


            _state.update {
                it.copy(
                    highestScore = highestScore ?: 0
                )
            }
        }
    }
}