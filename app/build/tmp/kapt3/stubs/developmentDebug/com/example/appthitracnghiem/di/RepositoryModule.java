package com.example.appthitracnghiem.di;

import com.example.appthitracnghiem.data.repository.impl.AuthRepositoryImpl;
import com.example.appthitracnghiem.data.repository.impl.CreateTestRepositoryImpl;
import com.example.appthitracnghiem.data.repository.impl.ExamRepositoryImpl;
import com.example.appthitracnghiem.data.repository.impl.HistoryRepositoryImpl;
import com.example.appthitracnghiem.data.repository.impl.HomeRepositoryImpl;
import com.example.appthitracnghiem.data.repository.impl.ProfileRepositoryImpl;
import com.example.appthitracnghiem.domain.repository.AuthRepository;
import com.example.appthitracnghiem.domain.repository.CreateTestRepository;
import com.example.appthitracnghiem.domain.repository.ExamRepository;
import com.example.appthitracnghiem.domain.repository.HistoryRepository;
import com.example.appthitracnghiem.domain.repository.HomeRepository;
import com.example.appthitracnghiem.domain.repository.ProfileRepository;
import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;

@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\tH\'J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\fH\'J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u000fH\'J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u0012H\'J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u0015H\'\u00a8\u0006\u0016"}, d2 = {"Lcom/example/appthitracnghiem/di/RepositoryModule;", "", "()V", "bindAuthRepository", "Lcom/example/appthitracnghiem/domain/repository/AuthRepository;", "impl", "Lcom/example/appthitracnghiem/data/repository/impl/AuthRepositoryImpl;", "bindCreateTestRepository", "Lcom/example/appthitracnghiem/domain/repository/CreateTestRepository;", "Lcom/example/appthitracnghiem/data/repository/impl/CreateTestRepositoryImpl;", "bindExamRepository", "Lcom/example/appthitracnghiem/domain/repository/ExamRepository;", "Lcom/example/appthitracnghiem/data/repository/impl/ExamRepositoryImpl;", "bindHistoryRepository", "Lcom/example/appthitracnghiem/domain/repository/HistoryRepository;", "Lcom/example/appthitracnghiem/data/repository/impl/HistoryRepositoryImpl;", "bindHomeRepository", "Lcom/example/appthitracnghiem/domain/repository/HomeRepository;", "Lcom/example/appthitracnghiem/data/repository/impl/HomeRepositoryImpl;", "bindProfileRepository", "Lcom/example/appthitracnghiem/domain/repository/ProfileRepository;", "Lcom/example/appthitracnghiem/data/repository/impl/ProfileRepositoryImpl;", "app_developmentDebug"})
@dagger.Module
public abstract class RepositoryModule {
    
    public RepositoryModule() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @javax.inject.Singleton
    @dagger.Binds
    public abstract com.example.appthitracnghiem.domain.repository.AuthRepository bindAuthRepository(@org.jetbrains.annotations.NotNull
    com.example.appthitracnghiem.data.repository.impl.AuthRepositoryImpl impl);
    
    @org.jetbrains.annotations.NotNull
    @javax.inject.Singleton
    @dagger.Binds
    public abstract com.example.appthitracnghiem.domain.repository.HomeRepository bindHomeRepository(@org.jetbrains.annotations.NotNull
    com.example.appthitracnghiem.data.repository.impl.HomeRepositoryImpl impl);
    
    @org.jetbrains.annotations.NotNull
    @javax.inject.Singleton
    @dagger.Binds
    public abstract com.example.appthitracnghiem.domain.repository.ExamRepository bindExamRepository(@org.jetbrains.annotations.NotNull
    com.example.appthitracnghiem.data.repository.impl.ExamRepositoryImpl impl);
    
    @org.jetbrains.annotations.NotNull
    @javax.inject.Singleton
    @dagger.Binds
    public abstract com.example.appthitracnghiem.domain.repository.HistoryRepository bindHistoryRepository(@org.jetbrains.annotations.NotNull
    com.example.appthitracnghiem.data.repository.impl.HistoryRepositoryImpl impl);
    
    @org.jetbrains.annotations.NotNull
    @javax.inject.Singleton
    @dagger.Binds
    public abstract com.example.appthitracnghiem.domain.repository.ProfileRepository bindProfileRepository(@org.jetbrains.annotations.NotNull
    com.example.appthitracnghiem.data.repository.impl.ProfileRepositoryImpl impl);
    
    @org.jetbrains.annotations.NotNull
    @javax.inject.Singleton
    @dagger.Binds
    public abstract com.example.appthitracnghiem.domain.repository.CreateTestRepository bindCreateTestRepository(@org.jetbrains.annotations.NotNull
    com.example.appthitracnghiem.data.repository.impl.CreateTestRepositoryImpl impl);
}