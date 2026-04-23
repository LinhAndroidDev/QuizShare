package com.example.appthitracnghiem.data.repository.impl;

import com.example.appthitracnghiem.core.ResultState;
import com.example.appthitracnghiem.data.remote.ApiService;
import com.example.appthitracnghiem.data.remote.dto.request.RequestGetListDepartment;
import com.example.appthitracnghiem.data.remote.dto.request.RequestUserInfo;
import com.example.appthitracnghiem.domain.model.UserProfile;
import com.example.appthitracnghiem.domain.repository.HomeRepository;
import com.example.appthitracnghiem.model.Department;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J5\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\nH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ5\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\nH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ5\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\nH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\'\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0014"}, d2 = {"Lcom/example/appthitracnghiem/data/repository/impl/HomeRepositoryImpl;", "Lcom/example/appthitracnghiem/domain/repository/HomeRepository;", "apiService", "Lcom/example/appthitracnghiem/data/remote/ApiService;", "(Lcom/example/appthitracnghiem/data/remote/ApiService;)V", "getDepartments", "Lcom/example/appthitracnghiem/core/ResultState;", "", "Lcom/example/appthitracnghiem/model/Department;", "accessToken", "", "userId", "", "keyword", "(Ljava/lang/String;ILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSystemDepartments", "getUserDepartments", "getUserProfile", "Lcom/example/appthitracnghiem/domain/model/UserProfile;", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_developmentDebug"})
public final class HomeRepositoryImpl implements com.example.appthitracnghiem.domain.repository.HomeRepository {
    private final com.example.appthitracnghiem.data.remote.ApiService apiService = null;
    
    @javax.inject.Inject
    public HomeRepositoryImpl(@org.jetbrains.annotations.NotNull
    com.example.appthitracnghiem.data.remote.ApiService apiService) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object getSystemDepartments(@org.jetbrains.annotations.NotNull
    java.lang.String accessToken, int userId, @org.jetbrains.annotations.NotNull
    java.lang.String keyword, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.appthitracnghiem.core.ResultState<? extends java.util.List<com.example.appthitracnghiem.model.Department>>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object getUserDepartments(@org.jetbrains.annotations.NotNull
    java.lang.String accessToken, int userId, @org.jetbrains.annotations.NotNull
    java.lang.String keyword, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.appthitracnghiem.core.ResultState<? extends java.util.List<com.example.appthitracnghiem.model.Department>>> continuation) {
        return null;
    }
    
    private final java.lang.Object getDepartments(java.lang.String accessToken, int userId, java.lang.String keyword, kotlin.coroutines.Continuation<? super com.example.appthitracnghiem.core.ResultState<? extends java.util.List<com.example.appthitracnghiem.model.Department>>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object getUserProfile(@org.jetbrains.annotations.NotNull
    java.lang.String accessToken, int userId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.appthitracnghiem.core.ResultState<com.example.appthitracnghiem.domain.model.UserProfile>> continuation) {
        return null;
    }
}