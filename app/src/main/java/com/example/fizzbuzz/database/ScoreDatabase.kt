package com.example.fizzbuzz.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [Score::class],
    version = 2
)

@TypeConverters(DateConverter2::class)
abstract class ScoreDatabase: RoomDatabase() {
    abstract val dao: ScoreDao
}