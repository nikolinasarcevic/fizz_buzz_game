package com.example.fizzbuzz.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.fizzbuzz.utils.DatabaseHelper

@Database(
    entities = [Score::class],
    version = 2
)

@TypeConverters(DatabaseHelper::class)
abstract class ScoreDatabase: RoomDatabase() {
    abstract val dao: ScoreDao
}