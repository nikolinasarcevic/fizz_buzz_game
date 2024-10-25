package com.example.fizzbuzz.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.Date

@Entity
data class Score(
    val nickname:String,
    val scoreValue: Int,
    val playedAt: LocalDateTime,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)
