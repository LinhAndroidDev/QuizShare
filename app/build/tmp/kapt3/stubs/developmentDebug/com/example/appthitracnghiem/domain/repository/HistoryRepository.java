package com.example.appthitracnghiem.domain.repository;

import com.example.appthitracnghiem.core.ResultState;
import com.example.appthitracnghiem.data.remote.entity.DepartmentSavedResponse;
import com.example.appthitracnghiem.data.remote.entity.ExamHistoryResponse;
import com.example.appthitracnghiem.data.remote.entity.HistoryTopicResponse;
import com.example.appthitracnghiem.data.remote.entity.SaveSubjectResponse;
import com.example.appthitracnghiem.data.remote.entity.TestSavedResponse;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\'\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0001H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ/\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\'\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0001H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\'\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0001H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\'\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0001H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0015"}, d2 = {"Lcom/example/appthitracnghiem/domain/repository/HistoryRepository;", "", "getExamHistory", "Lcom/example/appthitracnghiem/core/ResultState;", "Lcom/example/appthitracnghiem/data/remote/entity/ExamHistoryResponse;", "accessToken", "", "request", "(Ljava/lang/String;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getExamHistoryDetail", "Lcom/example/appthitracnghiem/data/remote/entity/HistoryTopicResponse;", "userId", "", "examHistoryId", "(Ljava/lang/String;IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSavedDepartments", "Lcom/example/appthitracnghiem/data/remote/entity/DepartmentSavedResponse;", "getSavedSubjects", "Lcom/example/appthitracnghiem/data/remote/entity/SaveSubjectResponse;", "getSavedTests", "Lcom/example/appthitracnghiem/data/remote/entity/TestSavedResponse;", "app_developmentDebug"})
public abstract interface HistoryRepository {
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getExamHistory(@org.jetbrains.annotations.NotNull
    java.lang.String accessToken, @org.jetbrains.annotations.NotNull
    java.lang.Object request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.appthitracnghiem.core.ResultState<com.example.appthitracnghiem.data.remote.entity.ExamHistoryResponse>> continuation);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getExamHistoryDetail(@org.jetbrains.annotations.NotNull
    java.lang.String accessToken, int userId, int examHistoryId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.appthitracnghiem.core.ResultState<com.example.appthitracnghiem.data.remote.entity.HistoryTopicResponse>> continuation);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getSavedDepartments(@org.jetbrains.annotations.NotNull
    java.lang.String accessToken, @org.jetbrains.annotations.NotNull
    java.lang.Object request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.appthitracnghiem.core.ResultState<com.example.appthitracnghiem.data.remote.entity.DepartmentSavedResponse>> continuation);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getSavedSubjects(@org.jetbrains.annotations.NotNull
    java.lang.String accessToken, @org.jetbrains.annotations.NotNull
    java.lang.Object request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.appthitracnghiem.core.ResultState<com.example.appthitracnghiem.data.remote.entity.SaveSubjectResponse>> continuation);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getSavedTests(@org.jetbrains.annotations.NotNull
    java.lang.String accessToken, @org.jetbrains.annotations.NotNull
    java.lang.Object request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.appthitracnghiem.core.ResultState<com.example.appthitracnghiem.data.remote.entity.TestSavedResponse>> continuation);
}