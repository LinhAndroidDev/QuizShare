package com.example.appthitracnghiem.ui.department.listdepartment;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001d\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0007\u00a8\u0006\u0015"}, d2 = {"Lcom/example/appthitracnghiem/ui/department/listdepartment/ListDepartmentViewModel;", "Lcom/example/appthitracnghiem/ui/base/BaseViewModel;", "()V", "idDepartmentLiveData", "Landroidx/lifecycle/MutableLiveData;", "", "getIdDepartmentLiveData", "()Landroidx/lifecycle/MutableLiveData;", "listDepartmentLiveData", "", "Lcom/example/appthitracnghiem/model/DetailDepartment;", "getListDepartmentLiveData", "loadingDepartmentLiveData", "", "getLoadingDepartmentLiveData", "getDataDepartmentDetail", "", "header", "", "requestDepartmentInfo", "Lcom/example/appthitracnghiem/ui/department/listdepartment/RequestDepartmentInfo;", "app_developmentDebug"})
public final class ListDepartmentViewModel extends com.example.appthitracnghiem.ui.base.BaseViewModel {
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.example.appthitracnghiem.model.DetailDepartment>> listDepartmentLiveData = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> loadingDepartmentLiveData = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Integer> idDepartmentLiveData = null;
    
    public ListDepartmentViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.util.List<com.example.appthitracnghiem.model.DetailDepartment>> getListDepartmentLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.Boolean> getLoadingDepartmentLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.Integer> getIdDepartmentLiveData() {
        return null;
    }
    
    public final void getDataDepartmentDetail(@org.jetbrains.annotations.NotNull()
    java.lang.String header, @org.jetbrains.annotations.NotNull()
    com.example.appthitracnghiem.ui.department.listdepartment.RequestDepartmentInfo requestDepartmentInfo) {
    }
}