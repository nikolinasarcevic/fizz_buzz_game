package com.example.fizzbuzz.data.repository

import android.content.SharedPreferences
import com.example.fizzbuzz.domain.repository.NicknameRepository
import javax.inject.Inject

class NicknameRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) : NicknameRepository {


    companion object {
        private const val NICKNAME_KEY = "nickname"
    }

    override suspend fun saveNickname(nickname: String) {
        sharedPreferences.edit()
            .putString(NICKNAME_KEY, nickname)
            .apply()
    }

    override suspend fun getNickname(): String? {
        return sharedPreferences.getString(NICKNAME_KEY, null)
    }
}