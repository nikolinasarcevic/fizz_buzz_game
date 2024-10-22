package com.example.fizzbuzz.di

import com.example.fizzbuzz.data.repository.NicknameRepositoryImpl
import com.example.fizzbuzz.database.ScoreDao
import com.example.fizzbuzz.domain.repository.NicknameRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppBindsModule {

    @Binds
    @Singleton
    abstract fun bindNicknameRepository(
        nicknameRepositoryImpl: NicknameRepositoryImpl
    ): NicknameRepository
}
