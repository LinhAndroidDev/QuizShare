package com.example.appthitracnghiem.ui.exercise.exercise.answer;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0018R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001d\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0007R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0007\u00a8\u0006\u0019"}, d2 = {"Lcom/example/appthitracnghiem/ui/exercise/exercise/answer/AnswerViewModel;", "Lcom/example/appthitracnghiem/ui/base/BaseViewModel;", "()V", "listAnswerLiveData", "Landroidx/lifecycle/MutableLiveData;", "", "getListAnswerLiveData", "()Landroidx/lifecycle/MutableLiveData;", "listExamQuestionLiveData", "Ljava/util/ArrayList;", "Lcom/example/appthitracnghiem/model/ExamQuestion;", "getListExamQuestionLiveData", "loadingLiveData", "", "getLoadingLiveData", "title", "getTitle", "getExamListQuestion", "", "header", "requestExamQuestion", "Lcom/example/appthitracnghiem/ui/exercise/exercise/exam/RequestExamQuestion;", "getExamResult", "requestAnswer", "Lcom/example/appthitracnghiem/ui/exercise/exercise/answer/RequestAnswer;", "app_developmentDebug"})
public final class AnswerViewModel extends com.example.appthitracnghiem.ui.base.BaseViewModel {
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.ArrayList<com.example.appthitracnghiem.model.ExamQuestion>> listExamQuestionLiveData = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> title = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> loadingLiveData = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> listAnswerLiveData = null;
    
    public AnswerViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.util.ArrayList<com.example.appthitracnghiem.model.ExamQuestion>> getListExamQuestionLiveData() {
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
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.String> getListAnswerLiveData() {
        return null;
    }
    
    public final void getExamListQuestion(@org.jetbrains.annotations.NotNull()
    java.lang.String header, @org.jetbrains.annotations.NotNull()
    com.example.appthitracnghiem.ui.exercise.exercise.exam.RequestExamQuestion requestExamQuestion) {
    }
    
    public final void getExamResult(@org.jetbrains.annotations.NotNull()
    java.lang.String header, @org.jetbrains.annotations.NotNull()
    com.example.appthitracnghiem.ui.exercise.exercise.answer.RequestAnswer requestAnswer) {
    }
}