package com.example.appthitracnghiem.domain.repository;

import com.example.appthitracnghiem.core.ResultState;
import com.example.appthitracnghiem.data.remote.entity.CreateExamResponse;
import com.example.appthitracnghiem.data.remote.entity.UploadImageResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\'\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0001H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ?\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\fH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0012"}, d2 = {"Lcom/example/appthitracnghiem/domain/repository/CreateTestRepository;", "", "createExam", "Lcom/example/appthitracnghiem/core/ResultState;", "Lcom/example/appthitracnghiem/data/remote/entity/CreateExamResponse;", "accessToken", "", "request", "(Ljava/lang/String;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadFile", "Lcom/example/appthitracnghiem/data/remote/entity/UploadImageResponse;", "userId", "Lokhttp3/RequestBody;", "file", "Lokhttp3/MultipartBody$Part;", "folderName", "fileName", "(Ljava/lang/String;Lokhttp3/RequestBody;Lokhttp3/MultipartBody$Part;Lokhttp3/RequestBody;Lokhttp3/RequestBody;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_developmentDebug"})
public abstract interface CreateTestRepository {
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object createExam(@org.jetbrains.annotations.NotNull
    java.lang.String accessToken, @org.jetbrains.annotations.NotNull
    java.lang.Object request, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.appthitracnghiem.core.ResultState<com.example.appthitracnghiem.data.remote.entity.CreateExamResponse>> continuation);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object uploadFile(@org.jetbrains.annotations.NotNull
    java.lang.String accessToken, @org.jetbrains.annotations.NotNull
    okhttp3.RequestBody userId, @org.jetbrains.annotations.NotNull
    okhttp3.MultipartBody.Part file, @org.jetbrains.annotations.NotNull
    okhttp3.RequestBody folderName, @org.jetbrains.annotations.NotNull
    okhttp3.RequestBody fileName, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.appthitracnghiem.core.ResultState<com.example.appthitracnghiem.data.remote.entity.UploadImageResponse>> continuation);
}