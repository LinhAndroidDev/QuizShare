package com.example.appthitracnghiem.ui.register;

import android.util.Patterns;
import androidx.lifecycle.MutableLiveData;
import com.example.appthitracnghiem.R;
import com.example.appthitracnghiem.data.remote.ApiClient;
import com.example.appthitracnghiem.data.repository.impl.AuthRepositoryImpl;
import com.example.appthitracnghiem.core.ResultState;
import com.example.appthitracnghiem.core.UiState;
import com.example.appthitracnghiem.domain.usecase.RegisterUseCase;
import com.example.appthitracnghiem.ui.base.BaseViewModel;
import com.example.appthitracnghiem.ui.login.ValidateModel;
import com.example.appthitracnghiem.utils.Email;

@kotlin.Suppress(names = {"UNREACHABLE_CODE"})
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J6\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u0015J0\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u0015H\u0002J8\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u0015H\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001d\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0007R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0007R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007\u00a8\u0006\u001d"}, d2 = {"Lcom/example/appthitracnghiem/ui/register/RegisterViewModel;", "Lcom/example/appthitracnghiem/ui/base/BaseViewModel;", "()V", "loadingLiveData", "Landroidx/lifecycle/MutableLiveData;", "", "getLoadingLiveData", "()Landroidx/lifecycle/MutableLiveData;", "registerUiState", "Lcom/example/appthitracnghiem/core/UiState;", "getRegisterUiState", "registerUseCase", "Lcom/example/appthitracnghiem/domain/usecase/RegisterUseCase;", "successRegisterLiveData", "getSuccessRegisterLiveData", "validateLiveData", "Lcom/example/appthitracnghiem/ui/login/ValidateModel;", "getValidateLiveData", "register", "", "strEmail", "", "strName", "strPhone", "strYearOfBirth", "strPassword", "strPasswordRepeat", "requestRegister", "validateRegister", "app_developmentDebug"})
public final class RegisterViewModel extends com.example.appthitracnghiem.ui.base.BaseViewModel {
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> loadingLiveData = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> successRegisterLiveData = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<com.example.appthitracnghiem.ui.login.ValidateModel> validateLiveData = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<com.example.appthitracnghiem.core.UiState<java.lang.Boolean>> registerUiState = null;
    private final com.example.appthitracnghiem.domain.usecase.RegisterUseCase registerUseCase = null;
    
    public RegisterViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<java.lang.Boolean> getLoadingLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<java.lang.Boolean> getSuccessRegisterLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.example.appthitracnghiem.ui.login.ValidateModel> getValidateLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.example.appthitracnghiem.core.UiState<java.lang.Boolean>> getRegisterUiState() {
        return null;
    }
    
    private final com.example.appthitracnghiem.ui.login.ValidateModel validateRegister(java.lang.String strEmail, java.lang.String strName, java.lang.String strPhone, java.lang.String strYearOfBirth, java.lang.String strPassword, java.lang.String strPasswordRepeat) {
        return null;
    }
    
    public final void register(@org.jetbrains.annotations.NotNull
    java.lang.String strEmail, @org.jetbrains.annotations.NotNull
    java.lang.String strName, @org.jetbrains.annotations.NotNull
    java.lang.String strPhone, @org.jetbrains.annotations.NotNull
    java.lang.String strYearOfBirth, @org.jetbrains.annotations.NotNull
    java.lang.String strPassword, @org.jetbrains.annotations.NotNull
    java.lang.String strPasswordRepeat) {
    }
    
    private final void requestRegister(java.lang.String strEmail, java.lang.String strName, java.lang.String strPhone, java.lang.String strYearOfBirth, java.lang.String strPassword) {
    }
}