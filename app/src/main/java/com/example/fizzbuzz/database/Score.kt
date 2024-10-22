package com.example.fizzbuzz.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Score(
    val nickname:String,
    val scoreValue: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)
