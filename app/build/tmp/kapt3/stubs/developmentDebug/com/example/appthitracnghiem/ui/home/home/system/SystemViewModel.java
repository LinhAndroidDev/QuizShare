package com.example.appthitracnghiem.ui.home.home.system;

import androidx.lifecycle.MutableLiveData;
import com.example.appthitracnghiem.data.remote.ApiClient;
import com.example.appthitracnghiem.data.repository.impl.HomeRepositoryImpl;
import com.example.appthitracnghiem.core.ResultState;
import com.example.appthitracnghiem.core.UiState;
import com.example.appthitracnghiem.domain.usecase.GetDepartmentsUseCase;
import com.example.appthitracnghiem.model.Department;
import com.example.appthitracnghiem.ui.base.BaseViewModel;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R&\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR \u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\n\"\u0004\b\u0010\u0010\fR#\u0010\u0011\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00130\u00120\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\n\u00a8\u0006\u001b"}, d2 = {"Lcom/example/appthitracnghiem/ui/home/home/system/SystemViewModel;", "Lcom/example/appthitracnghiem/ui/base/BaseViewModel;", "()V", "getDepartmentsUseCase", "Lcom/example/appthitracnghiem/domain/usecase/GetDepartmentsUseCase;", "listDepartmentLiveData", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/example/appthitracnghiem/model/Department;", "getListDepartmentLiveData", "()Landroidx/lifecycle/MutableLiveData;", "setListDepartmentLiveData", "(Landroidx/lifecycle/MutableLiveData;)V", "loadingData", "", "getLoadingData", "setLoadingData", "uiState", "Lcom/example/appthitracnghiem/core/UiState;", "", "getUiState", "getDataDepartment", "", "accessToken", "", "requestGetListDepartment", "Lcom/example/appthitracnghiem/ui/home/home/system/RequestGetListDepartment;", "app_developmentDebug"})
public final class SystemViewModel extends com.example.appthitracnghiem.ui.base.BaseViewModel {
    @org.jetbrains.annotations.NotNull
    private androidx.lifecycle.MutableLiveData<java.lang.Boolean> loadingData;
    @org.jetbrains.annotations.NotNull
    private androidx.lifecycle.MutableLiveData<java.util.List<com.example.appthitracnghiem.model.Department>> listDepartmentLiveData;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<com.example.appthitracnghiem.core.UiState<java.util.List<com.example.appthitracnghiem.model.Department>>> uiState = null;
    private final com.example.appthitracnghiem.domain.usecase.GetDepartmentsUseCase getDepartmentsUseCase = null;
    
    public SystemViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<java.lang.Boolean> getLoadingData() {
        return null;
    }
    
    public final void setLoadingData(@org.jetbrains.annotations.NotNull
    androidx.lifecycle.MutableLiveData<java.lang.Boolean> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<java.util.List<com.example.appthitracnghiem.model.Department>> getListDepartmentLiveData() {
        return null;
    }
    
    public final void setListDepartmentLiveData(@org.jetbrains.annotations.NotNull
    androidx.lifecycle.MutableLiveData<java.util.List<com.example.appthitracnghiem.model.Department>> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.example.appthitracnghiem.core.UiState<java.util.List<com.example.appthitracnghiem.model.Department>>> getUiState() {
        return null;
    }
    
    public final void getDataDepartment(@org.jetbrains.annotations.NotNull
    java.lang.String accessToken, @org.jetbrains.annotations.NotNull
    com.example.appthitracnghiem.ui.home.home.system.RequestGetListDepartment requestGetListDepartment) {
    }
}