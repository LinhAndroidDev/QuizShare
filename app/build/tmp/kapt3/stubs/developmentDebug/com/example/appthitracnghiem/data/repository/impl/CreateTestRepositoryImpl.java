package com.example.appthitracnghiem.data.repository.impl;

import com.example.appthitracnghiem.core.ResultState;
import com.example.appthitracnghiem.data.remote.ApiService;
import com.example.appthitracnghiem.data.remote.entity.CreateExamResponse;
import com.example.appthitracnghiem.data.remote.entity.UploadImageResponse;
import com.example.appthitracnghiem.domain.repository.CreateTestRepository;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\'\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ?\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0010H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0016"}, d2 = {"Lcom/example/appthitracnghiem/data/repository/impl/CreateTestRepositoryImpl;", "Lcom/example/appthitracnghiem/domain/repository/CreateTestRepository;", "apiService", "Lcom/example/appthitracnghiem/data/remote/ApiService;", "(Lcom/example/appthitracnghiem/data/remote/ApiService;)V", "createExam", "Lcom/example/appthitracnghiem/core/ResultState;", "Lcom/example/appthitracnghiem/data/remote/entity/CreateExamResponse;", "accessToken", "", "request", "", "(Ljava/lang/String;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadFile", "Lcom/example/appthitracnghiem/data/remote/entity/UploadImageResponse;", "userId", "Lokhttp3/RequestBody;", "file", "Lokhttp3/MultipartBody$Part;", "folderName", "fileName", "(Ljava/lang/String;Lokhttp3/RequestBody;Lokhttp3/MultipartBody$Part;Lokhttp3/RequestBody;Lokhttp3/RequestBody;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_developmentDebug"})
public final class CreateTestRepositoryImpl implements com.example.appthitracnghiem.domain.repository.CreateTestRepository {
    private final com.example.appthitracnghiem.data.remote.ApiService apiService = null;
    
    @javax.inject.Inject
    public CreateTestRepositoryImpl(@org.jetbrains.annotations.NotNull
    com.example.appthitracnghiem.data.remote.ApiService apiService) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object createExam(@org.jetbrains.annotations.NotNull
    java.lang.String accessToken, @org.jetbrains.annotations.NotNull
    java.lang.Object request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.appthitracnghiem.core.ResultState<com.example.appthitracnghiem.data.remote.entity.CreateExamResponse>> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object uploadFile(@org.jetbrains.annotations.NotNull
    java.lang.String accessToken, @org.jetbrains.annotations.NotNull
    okhttp3.RequestBody userId, @org.jetbrains.annotations.NotNull
    okhttp3.MultipartBody.Part file, @org.jetbrains.annotations.NotNull
    okhttp3.RequestBody folderName, @org.jetbrains.annotations.NotNull
    okhttp3.RequestBody fileName, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.appthitracnghiem.core.ResultState<com.example.appthitracnghiem.data.remote.entity.UploadImageResponse>> continuation) {
        return null;
    }
}