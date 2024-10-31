package com.example.fizzbuzz.utils

import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DatabaseHelper {

    private val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")

    @TypeConverter
    fun dateToString(time: LocalDateTime): String {
        return dateFormatter.format(time)
    }

    @TypeConverter
    fun stringToDate(string: String): LocalDateTime? {
        return LocalDateTime.parse(string, dateFormatter)
    }
}