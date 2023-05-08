package com.example.appthitracnghiem.data.remote;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\n\u0010\u000e\u001a\u0004\u0018\u00010\bH\u0002J\u0006\u0010\u000f\u001a\u00020\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/example/appthitracnghiem/data/remote/ApiClient;", "", "()V", "API_SERVICE", "Lcom/example/appthitracnghiem/data/remote/ApiService;", "BASE_URL", "", "RETROFIT", "Lretrofit2/Retrofit;", "STATUS_CODE_SERVER_NOT_RESPONSE", "", "STATUS_CODE_SUCCESS", "STATUS_INVALID_TOKEN", "STATUS_USER_EXIST", "getClient", "shared", "app_developmentDebug"})
public final class ApiClient {
    @org.jetbrains.annotations.NotNull()
    public static final com.example.appthitracnghiem.data.remote.ApiClient INSTANCE = null;
    public static final int STATUS_CODE_SUCCESS = 200;
    public static final int STATUS_USER_EXIST = 400;
    public static final int STATUS_INVALID_TOKEN = 401;
    public static final int STATUS_CODE_SERVER_NOT_RESPONSE = 500;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BASE_URL = "https://asia-northeast1-quiz-app-traning.cloudfunctions.net/";
    private static retrofit2.Retrofit RETROFIT;
    private static com.example.appthitracnghiem.data.remote.ApiService API_SERVICE;
    
    private ApiClient() {
        super();
    }
    
    private final retrofit2.Retrofit getClient() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.appthitracnghiem.data.remote.ApiService shared() {
        return null;
    }
}