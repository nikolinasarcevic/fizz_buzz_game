package com.example.fizzbuzz.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ScoreDao {

    @Upsert
    suspend fun saveScore(score: Score)

    @Query("SELECT * FROM score ORDER BY scoreValue DESC")
    fun getLeaderboard(): Flow<List<Score>>

//    @Query("SELECT * FROM score ORDER BY id DESC LIMIT 1")
//    suspend fun getLatestScore(): Score?

}