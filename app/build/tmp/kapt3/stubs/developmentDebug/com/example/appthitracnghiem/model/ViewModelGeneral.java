package com.example.appthitracnghiem.model;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u001b\u001a\u00020\u001cJ\u0006\u0010\u001d\u001a\u00020\u001cJ\b\u0010\u001e\u001a\u00020\u001cH\u0002J\b\u0010\u001f\u001a\u00020\u001cH\u0002R\u001d\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\bR\u001f\u0010\f\u001a\u0010\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\r0\r0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\bR\u001f\u0010\u0010\u001a\u0010\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\r0\r0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\bR\u001a\u0010\u0012\u001a\u00020\u0013X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0013X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\u0017\u00a8\u0006 "}, d2 = {"Lcom/example/appthitracnghiem/model/ViewModelGeneral;", "Landroidx/lifecycle/ViewModel;", "()V", "listQuizLive", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/example/appthitracnghiem/model/Department;", "getListQuizLive", "()Landroidx/lifecycle/MutableLiveData;", "listSubjectLive", "Lcom/example/appthitracnghiem/model/RecommandSubject;", "getListSubjectLive", "loadingQuizLive", "", "kotlin.jvm.PlatformType", "getLoadingQuizLive", "loadingSubjectLive", "getLoadingSubjectLive", "postRetrofit", "Lretrofit2/Retrofit;", "getPostRetrofit", "()Lretrofit2/Retrofit;", "setPostRetrofit", "(Lretrofit2/Retrofit;)V", "retrofit", "getRetrofit", "setRetrofit", "getDataQuiz", "", "getListSubject", "initPostRetrofit", "initRetrofit", "app_developmentDebug"})
public final class ViewModelGeneral extends androidx.lifecycle.ViewModel {
    public retrofit2.Retrofit retrofit;
    public retrofit2.Retrofit postRetrofit;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.example.appthitracnghiem.model.Department>> listQuizLive = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> loadingQuizLive = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.example.appthitracnghiem.model.RecommandSubject>> listSubjectLive = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> loadingSubjectLive = null;
    
    public ViewModelGeneral() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final retrofit2.Retrofit getRetrofit() {
        return null;
    }
    
    public final void setRetrofit(@org.jetbrains.annotations.NotNull()
    retrofit2.Retrofit p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final retrofit2.Retrofit getPostRetrofit() {
        return null;
    }
    
    public final void setPostRetrofit(@org.jetbrains.annotations.NotNull()
    retrofit2.Retrofit p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.util.List<com.example.appthitracnghiem.model.Department>> getListQuizLive() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.Boolean> getLoadingQuizLive() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.util.List<com.example.appthitracnghiem.model.RecommandSubject>> getListSubjectLive() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.Boolean> getLoadingSubjectLive() {
        return null;
    }
    
    private final void initRetrofit() {
    }
    
    private final void initPostRetrofit() {
    }
    
    public final void getDataQuiz() {
    }
    
    public final void getListSubject() {
    }
}