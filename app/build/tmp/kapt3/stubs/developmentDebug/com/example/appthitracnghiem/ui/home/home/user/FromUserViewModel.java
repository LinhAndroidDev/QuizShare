package com.example.appthitracnghiem.ui.home.home.user;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014R&\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR \u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lcom/example/appthitracnghiem/ui/home/home/user/FromUserViewModel;", "Lcom/example/appthitracnghiem/ui/base/BaseViewModel;", "()V", "listDepartmentFromUserLiveData", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/example/appthitracnghiem/model/Department;", "getListDepartmentFromUserLiveData", "()Landroidx/lifecycle/MutableLiveData;", "setListDepartmentFromUserLiveData", "(Landroidx/lifecycle/MutableLiveData;)V", "loadingFromUserData", "", "getLoadingFromUserData", "setLoadingFromUserData", "getDataDepartmentFromUser", "", "accessToken", "", "requestGetListDepartment", "Lcom/example/appthitracnghiem/ui/home/home/system/RequestGetListDepartment;", "app_developmentDebug"})
public final class FromUserViewModel extends com.example.appthitracnghiem.ui.base.BaseViewModel {
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.MutableLiveData<java.lang.Boolean> loadingFromUserData;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.MutableLiveData<java.util.List<com.example.appthitracnghiem.model.Department>> listDepartmentFromUserLiveData;
    
    public FromUserViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.Boolean> getLoadingFromUserData() {
        return null;
    }
    
    public final void setLoadingFromUserData(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<java.lang.Boolean> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.util.List<com.example.appthitracnghiem.model.Department>> getListDepartmentFromUserLiveData() {
        return null;
    }
    
    public final void setListDepartmentFromUserLiveData(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<java.util.List<com.example.appthitracnghiem.model.Department>> p0) {
    }
    
    public final void getDataDepartmentFromUser(@org.jetbrains.annotations.NotNull()
    java.lang.String accessToken, @org.jetbrains.annotations.NotNull()
    com.example.appthitracnghiem.ui.home.home.system.RequestGetListDepartment requestGetListDepartment) {
    }
}