package com.example.appthitracnghiem.data.remote;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0004\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u0004\u0018\u00018\u0000X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0015\u0010\u000b\u001a\u0004\u0018\u00010\f\u00a2\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u0010"}, d2 = {"Lcom/example/appthitracnghiem/data/remote/BaseResponse;", "R", "", "()V", "message", "", "getMessage", "()Ljava/lang/String;", "result", "getResult", "()Ljava/lang/Object;", "statusCode", "", "getStatusCode", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "app_developmentDebug"})
public abstract class BaseResponse<R extends java.lang.Object> {
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer statusCode = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String message = null;
    
    public BaseResponse() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getStatusCode() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public abstract R getResult();
}