package com.example.fizzbuzz.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ScoreDao {

    @Upsert
    suspend fun saveScore(score: Score)

    @Query("SELECT * FROM score ORDER BY scoreValue DESC")
    fun getLeaderboard(): Flow<List<Score>>

    @Query("SELECT * FROM score WHERE nickname = :nickname LIMIT 1")
    suspend fun getScoreByNickname(nickname: String): Score?

    @Query("SELECT MAX(scoreValue) FROM score WHERE nickname = :nickname")
    suspend fun getHighestScoreForNickname(nickname: String): Int?

    //    @Query("SELECT * FROM score ORDER BY id DESC LIMIT 1")
    //    suspend fun getLatestScore(): Score?

//    @Query("DELETE FROM score WHERE id = 8")
//    suspend fun getScoreById(): Score?

}