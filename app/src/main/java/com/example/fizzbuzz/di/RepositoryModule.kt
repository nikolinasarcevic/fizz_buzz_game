package com.example.fizzbuzz.di

import com.example.fizzbuzz.data.repository.NicknameRepositoryImpl
import com.example.fizzbuzz.domain.repository.NicknameRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMyRepository(
        nicknameRepositoryImpl: NicknameRepositoryImpl
    ): NicknameRepository

}