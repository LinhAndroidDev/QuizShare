package com.example.appthitracnghiem.data.repository.impl;

import com.example.appthitracnghiem.core.ResultState;
import com.example.appthitracnghiem.data.remote.ApiService;
import com.example.appthitracnghiem.data.remote.entity.AnswerResponse;
import com.example.appthitracnghiem.data.remote.entity.ExamQuestionResponse;
import com.example.appthitracnghiem.data.remote.entity.PointResponse;
import com.example.appthitracnghiem.domain.repository.ExamRepository;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\'\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ\'\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ\'\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0011"}, d2 = {"Lcom/example/appthitracnghiem/data/repository/impl/ExamRepositoryImpl;", "Lcom/example/appthitracnghiem/domain/repository/ExamRepository;", "apiService", "Lcom/example/appthitracnghiem/data/remote/ApiService;", "(Lcom/example/appthitracnghiem/data/remote/ApiService;)V", "getExamQuestions", "Lcom/example/appthitracnghiem/core/ResultState;", "Lcom/example/appthitracnghiem/data/remote/entity/ExamQuestionResponse;", "accessToken", "", "request", "", "(Ljava/lang/String;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getExamResult", "Lcom/example/appthitracnghiem/data/remote/entity/AnswerResponse;", "submitExam", "Lcom/example/appthitracnghiem/data/remote/entity/PointResponse;", "app_developmentDebug"})
public final class ExamRepositoryImpl implements com.example.appthitracnghiem.domain.repository.ExamRepository {
    private final com.example.appthitracnghiem.data.remote.ApiService apiService = null;
    
    @javax.inject.Inject
    public ExamRepositoryImpl(@org.jetbrains.annotations.NotNull
    com.example.appthitracnghiem.data.remote.ApiService apiService) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object getExamQuestions(@org.jetbrains.annotations.NotNull
    java.lang.String accessToken, @org.jetbrains.annotations.NotNull
    java.lang.Object request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.appthitracnghiem.core.ResultState<com.example.appthitracnghiem.data.remote.entity.ExamQuestionResponse>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object submitExam(@org.jetbrains.annotations.NotNull
    java.lang.String accessToken, @org.jetbrains.annotations.NotNull
    java.lang.Object request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.appthitracnghiem.core.ResultState<com.example.appthitracnghiem.data.remote.entity.PointResponse>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object getExamResult(@org.jetbrains.annotations.NotNull
    java.lang.String accessToken, @org.jetbrains.annotations.NotNull
    java.lang.Object request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.appthitracnghiem.core.ResultState<com.example.appthitracnghiem.data.remote.entity.AnswerResponse>> continuation) {
        return null;
    }
}