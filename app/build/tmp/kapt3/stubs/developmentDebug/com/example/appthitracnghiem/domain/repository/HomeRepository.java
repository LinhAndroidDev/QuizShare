package com.example.appthitracnghiem.domain.repository;

import com.example.appthitracnghiem.core.ResultState;
import com.example.appthitracnghiem.domain.model.UserProfile;
import com.example.appthitracnghiem.model.Department;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J5\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ5\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\'\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0010"}, d2 = {"Lcom/example/appthitracnghiem/domain/repository/HomeRepository;", "", "getSystemDepartments", "Lcom/example/appthitracnghiem/core/ResultState;", "", "Lcom/example/appthitracnghiem/model/Department;", "accessToken", "", "userId", "", "keyword", "(Ljava/lang/String;ILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUserDepartments", "getUserProfile", "Lcom/example/appthitracnghiem/domain/model/UserProfile;", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_developmentDebug"})
public abstract interface HomeRepository {
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getSystemDepartments(@org.jetbrains.annotations.NotNull
    java.lang.String accessToken, int userId, @org.jetbrains.annotations.NotNull
    java.lang.String keyword, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.appthitracnghiem.core.ResultState<? extends java.util.List<com.example.appthitracnghiem.model.Department>>> continuation);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getUserDepartments(@org.jetbrains.annotations.NotNull
    java.lang.String accessToken, int userId, @org.jetbrains.annotations.NotNull
    java.lang.String keyword, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.appthitracnghiem.core.ResultState<? extends java.util.List<com.example.appthitracnghiem.model.Department>>> continuation);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getUserProfile(@org.jetbrains.annotations.NotNull
    java.lang.String accessToken, int userId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.appthitracnghiem.core.ResultState<com.example.appthitracnghiem.domain.model.UserProfile>> continuation);
}