package com.example.appthitracnghiem.ui.home.history.test.general;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR \u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\f\u0010\tR&\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0007\"\u0004\b\u0011\u0010\t\u00a8\u0006\u0018"}, d2 = {"Lcom/example/appthitracnghiem/ui/home/history/test/general/HistoryTestViewModel;", "Lcom/example/appthitracnghiem/ui/base/BaseViewModel;", "()V", "idExamHistoryLiveData", "Landroidx/lifecycle/MutableLiveData;", "", "getIdExamHistoryLiveData", "()Landroidx/lifecycle/MutableLiveData;", "setIdExamHistoryLiveData", "(Landroidx/lifecycle/MutableLiveData;)V", "isLoadingLiveData", "", "setLoadingLiveData", "listExamHistoryLiveData", "Ljava/util/ArrayList;", "Lcom/example/appthitracnghiem/model/HistoryExam;", "getListExamHistoryLiveData", "setListExamHistoryLiveData", "getExamHistory", "", "header", "", "requestExamHistory", "Lcom/example/appthitracnghiem/ui/home/history/test/general/RequestExamHistory;", "app_developmentDebug"})
public final class HistoryTestViewModel extends com.example.appthitracnghiem.ui.base.BaseViewModel {
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.MutableLiveData<java.lang.Boolean> isLoadingLiveData;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.MutableLiveData<java.util.ArrayList<com.example.appthitracnghiem.model.HistoryExam>> listExamHistoryLiveData;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.MutableLiveData<java.lang.Integer> idExamHistoryLiveData;
    
    public HistoryTestViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.Boolean> isLoadingLiveData() {
        return null;
    }
    
    public final void setLoadingLiveData(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<java.lang.Boolean> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.util.ArrayList<com.example.appthitracnghiem.model.HistoryExam>> getListExamHistoryLiveData() {
        return null;
    }
    
    public final void setListExamHistoryLiveData(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<java.util.ArrayList<com.example.appthitracnghiem.model.HistoryExam>> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.Integer> getIdExamHistoryLiveData() {
        return null;
    }
    
    public final void setIdExamHistoryLiveData(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<java.lang.Integer> p0) {
    }
    
    public final void getExamHistory(@org.jetbrains.annotations.NotNull()
    java.lang.String header, @org.jetbrains.annotations.NotNull()
    com.example.appthitracnghiem.ui.home.history.test.general.RequestExamHistory requestExamHistory) {
    }
}