package com.example.appthitracnghiem.ui.exercise.exercise;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0013R\u001d\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\bR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\b\u00a8\u0006\u0014"}, d2 = {"Lcom/example/appthitracnghiem/ui/exercise/exercise/ExamViewModel;", "Lcom/example/appthitracnghiem/ui/base/BaseViewModel;", "()V", "listExamQuestionLiveData", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/example/appthitracnghiem/model/ExamQuestion;", "getListExamQuestionLiveData", "()Landroidx/lifecycle/MutableLiveData;", "loadingLiveData", "", "getLoadingLiveData", "title", "", "getTitle", "getExamListQuestion", "", "header", "requestExamQuestion", "Lcom/example/appthitracnghiem/ui/exercise/exercise/RequestExamQuestion;", "app_developmentDebug"})
public final class ExamViewModel extends com.example.appthitracnghiem.ui.base.BaseViewModel {
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.example.appthitracnghiem.model.ExamQuestion>> listExamQuestionLiveData = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> title = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> loadingLiveData = null;
    
    public ExamViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.util.List<com.example.appthitracnghiem.model.ExamQuestion>> getListExamQuestionLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.String> getTitle() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.Boolean> getLoadingLiveData() {
        return null;
    }
    
    public final void getExamListQuestion(@org.jetbrains.annotations.NotNull()
    java.lang.String header, @org.jetbrains.annotations.NotNull()
    com.example.appthitracnghiem.ui.exercise.exercise.RequestExamQuestion requestExamQuestion) {
    }
}