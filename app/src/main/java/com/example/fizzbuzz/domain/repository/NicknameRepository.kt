package com.example.fizzbuzz.domain.repository

interface NicknameRepository {
    suspend fun saveNickname(nickname: String)
    suspend fun getNickname(): String?
}