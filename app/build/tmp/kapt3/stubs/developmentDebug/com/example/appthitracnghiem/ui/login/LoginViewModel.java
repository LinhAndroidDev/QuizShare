package com.example.appthitracnghiem.ui.login;

import android.annotation.SuppressLint;
import androidx.lifecycle.MutableLiveData;
import com.example.appthitracnghiem.R;
import com.example.appthitracnghiem.data.remote.ApiClient;
import com.example.appthitracnghiem.data.repository.impl.AuthRepositoryImpl;
import com.example.appthitracnghiem.domain.usecase.LoginUseCase;
import com.example.appthitracnghiem.core.ResultState;
import com.example.appthitracnghiem.core.UiState;
import com.example.appthitracnghiem.ui.base.BaseViewModel;
import com.example.appthitracnghiem.utils.Email;
import com.example.appthitracnghiem.utils.PreferenceKey;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0012\u001a\u00020\u0013J\u0018\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0007J\u0018\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0002J\u0018\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u001cH\u0007J\u0018\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001d\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0007R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0007R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007\u00a8\u0006\u001e"}, d2 = {"Lcom/example/appthitracnghiem/ui/login/LoginViewModel;", "Lcom/example/appthitracnghiem/ui/base/BaseViewModel;", "()V", "loadingLiveData", "Landroidx/lifecycle/MutableLiveData;", "", "getLoadingLiveData", "()Landroidx/lifecycle/MutableLiveData;", "loginUiState", "Lcom/example/appthitracnghiem/core/UiState;", "getLoginUiState", "loginUseCase", "Lcom/example/appthitracnghiem/domain/usecase/LoginUseCase;", "successLoginLiveData", "getSuccessLoginLiveData", "validateLiveData", "Lcom/example/appthitracnghiem/ui/login/ValidateModel;", "getValidateLiveData", "confirmLoggedIn", "", "login", "strEmail", "", "strPassword", "requestLogin", "savedAuthentication", "token", "id", "", "validateLogin", "app_developmentDebug"})
public final class LoginViewModel extends com.example.appthitracnghiem.ui.base.BaseViewModel {
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> loadingLiveData = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> successLoginLiveData = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<com.example.appthitracnghiem.ui.login.ValidateModel> validateLiveData = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<com.example.appthitracnghiem.core.UiState<java.lang.Boolean>> loginUiState = null;
    private final com.example.appthitracnghiem.domain.usecase.LoginUseCase loginUseCase = null;
    
    public LoginViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<java.lang.Boolean> getLoadingLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<java.lang.Boolean> getSuccessLoginLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.example.appthitracnghiem.ui.login.ValidateModel> getValidateLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.example.appthitracnghiem.core.UiState<java.lang.Boolean>> getLoginUiState() {
        return null;
    }
    
    public final void confirmLoggedIn() {
    }
    
    @android.annotation.SuppressLint(value = {"CommitPrefEdits"})
    public final void savedAuthentication(@org.jetbrains.annotations.NotNull
    java.lang.String token, int id) {
    }
    
    private final com.example.appthitracnghiem.ui.login.ValidateModel validateLogin(java.lang.String strEmail, java.lang.String strPassword) {
        return null;
    }
    
    @android.annotation.SuppressLint(value = {"SuspiciousIndentation"})
    public final void login(@org.jetbrains.annotations.NotNull
    java.lang.String strEmail, @org.jetbrains.annotations.NotNull
    java.lang.String strPassword) {
    }
    
    private final void requestLogin(java.lang.String strEmail, java.lang.String strPassword) {
    }
}