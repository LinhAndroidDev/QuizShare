package com.example.appthitracnghiem.ui.home.history.saved.subject;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0003\u0010\u0006\"\u0004\b\u0007\u0010\bR(\u0010\t\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n0\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\u0006\"\u0004\b\r\u0010\b\u00a8\u0006\u0014"}, d2 = {"Lcom/example/appthitracnghiem/ui/home/history/saved/subject/HistorySubjectSavedViewModel;", "Lcom/example/appthitracnghiem/ui/base/BaseViewModel;", "()V", "isLoadingLiveData", "Landroidx/lifecycle/MutableLiveData;", "", "()Landroidx/lifecycle/MutableLiveData;", "setLoadingLiveData", "(Landroidx/lifecycle/MutableLiveData;)V", "listSubjectSaved", "Ljava/util/ArrayList;", "Lcom/example/appthitracnghiem/data/remote/entity/SaveSubjectResponse$Result;", "getListSubjectSaved", "setListSubjectSaved", "saveSubject", "", "header", "", "requestSubjectSaved", "Lcom/example/appthitracnghiem/ui/home/history/saved/subject/RequestSubjectSaved;", "app_developmentDebug"})
public final class HistorySubjectSavedViewModel extends com.example.appthitracnghiem.ui.base.BaseViewModel {
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.MutableLiveData<java.lang.Boolean> isLoadingLiveData;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.MutableLiveData<java.util.ArrayList<com.example.appthitracnghiem.data.remote.entity.SaveSubjectResponse.Result>> listSubjectSaved;
    
    public HistorySubjectSavedViewModel() {
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
    public final androidx.lifecycle.MutableLiveData<java.util.ArrayList<com.example.appthitracnghiem.data.remote.entity.SaveSubjectResponse.Result>> getListSubjectSaved() {
        return null;
    }
    
    public final void setListSubjectSaved(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<java.util.ArrayList<com.example.appthitracnghiem.data.remote.entity.SaveSubjectResponse.Result>> p0) {
    }
    
    public final void saveSubject(@org.jetbrains.annotations.NotNull()
    java.lang.String header, @org.jetbrains.annotations.NotNull()
    com.example.appthitracnghiem.ui.home.history.saved.subject.RequestSubjectSaved requestSubjectSaved) {
    }
}