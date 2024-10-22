package com.example.fizzbuzz.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.fizzbuzz.database.ScoreDao
import com.example.fizzbuzz.database.ScoreDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("nickname_prefs", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideScoreDatabase(@ApplicationContext context: Context): ScoreDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ScoreDatabase::class.java,
            "score_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideScoreDao(database: ScoreDatabase): ScoreDao {
        return database.dao
    }
}
