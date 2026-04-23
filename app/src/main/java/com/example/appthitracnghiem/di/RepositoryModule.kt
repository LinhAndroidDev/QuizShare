package com.example.appthitracnghiem.di

import com.example.appthitracnghiem.data.repository.impl.AuthRepositoryImpl
import com.example.appthitracnghiem.data.repository.impl.CreateTestRepositoryImpl
import com.example.appthitracnghiem.data.repository.impl.ExamRepositoryImpl
import com.example.appthitracnghiem.data.repository.impl.HistoryRepositoryImpl
import com.example.appthitracnghiem.data.repository.impl.HomeRepositoryImpl
import com.example.appthitracnghiem.data.repository.impl.ProfileRepositoryImpl
import com.example.appthitracnghiem.domain.repository.AuthRepository
import com.example.appthitracnghiem.domain.repository.CreateTestRepository
import com.example.appthitracnghiem.domain.repository.ExamRepository
import com.example.appthitracnghiem.domain.repository.HistoryRepository
import com.example.appthitracnghiem.domain.repository.HomeRepository
import com.example.appthitracnghiem.domain.repository.ProfileRepository
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
    abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindHomeRepository(impl: HomeRepositoryImpl): HomeRepository

    @Binds
    @Singleton
    abstract fun bindExamRepository(impl: ExamRepositoryImpl): ExamRepository

    @Binds
    @Singleton
    abstract fun bindHistoryRepository(impl: HistoryRepositoryImpl): HistoryRepository

    @Binds
    @Singleton
    abstract fun bindProfileRepository(impl: ProfileRepositoryImpl): ProfileRepository

    @Binds
    @Singleton
    abstract fun bindCreateTestRepository(impl: CreateTestRepositoryImpl): CreateTestRepository
}
