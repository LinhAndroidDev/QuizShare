package com.example.appthitracnghiem.ui.login;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\r\u001a\u00020\u000eJ\u0018\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0007J\u0018\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0002J\u0018\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0017H\u0007J\u0018\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0007\u00a8\u0006\u0019"}, d2 = {"Lcom/example/appthitracnghiem/ui/login/LoginViewModel;", "Lcom/example/appthitracnghiem/ui/base/BaseViewModel;", "()V", "loadingLiveData", "Landroidx/lifecycle/MutableLiveData;", "", "getLoadingLiveData", "()Landroidx/lifecycle/MutableLiveData;", "successLoginLiveData", "getSuccessLoginLiveData", "validateLiveData", "Lcom/example/appthitracnghiem/ui/login/ValidateModel;", "getValidateLiveData", "confirmLoggedIn", "", "login", "strEmail", "", "strPassword", "requestLogin", "savedAuthentication", "token", "id", "", "validateLogin", "app_developmentDebug"})
public final class LoginViewModel extends com.example.appthitracnghiem.ui.base.BaseViewModel {
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> loadingLiveData = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> successLoginLiveData = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.example.appthitracnghiem.ui.login.ValidateModel> validateLiveData = null;
    
    public LoginViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.Boolean> getLoadingLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.Boolean> getSuccessLoginLiveData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<com.example.appthitracnghiem.ui.login.ValidateModel> getValidateLiveData() {
        return null;
    }
    
    public final void confirmLoggedIn() {
    }
    
    @android.annotation.SuppressLint(value = {"CommitPrefEdits"})
    public final void savedAuthentication(@org.jetbrains.annotations.NotNull()
    java.lang.String token, int id) {
    }
    
    private final com.example.appthitracnghiem.ui.login.ValidateModel validateLogin(java.lang.String strEmail, java.lang.String strPassword) {
        return null;
    }
    
    @android.annotation.SuppressLint(value = {"SuspiciousIndentation"})
    public final void login(@org.jetbrains.annotations.NotNull()
    java.lang.String strEmail, @org.jetbrains.annotations.NotNull()
    java.lang.String strPassword) {
    }
    
    private final void requestLogin(java.lang.String strEmail, java.lang.String strPassword) {
    }
}