package com.example.appthitracnghiem.ui.login;

import java.lang.System;

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentLogin.newInstance] factory method to
 * create an instance of this fragment.
 */
@kotlin.Suppress(names = {"DEPRECATION"})
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010&\u001a\u00020\'H\u0016J\u0006\u0010(\u001a\u00020\'J\u0018\u0010)\u001a\u00020\'2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0002J\b\u0010.\u001a\u00020\'H\u0003J&\u0010/\u001a\u0004\u0018\u0001002\u0006\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u0001042\b\u00105\u001a\u0004\u0018\u000106H\u0016J\b\u00107\u001a\u00020\u0005H\u0016J\u001a\u00108\u001a\u00020\'2\u0006\u00109\u001a\u0002002\b\u00105\u001a\u0004\u0018\u000106H\u0016J\u0018\u0010:\u001a\u00020\'2\u0006\u0010;\u001a\u00020\u00152\u0006\u0010*\u001a\u00020\u0015H\u0002J\u0016\u0010<\u001a\u00020\'2\u0006\u0010=\u001a\u00020!2\u0006\u0010>\u001a\u00020!J\b\u0010?\u001a\u00020\'H\u0002R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u00020\rX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u00020\u0015X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u0015X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0017\"\u0004\b\u001c\u0010\u0019R\u001a\u0010\u001d\u001a\u00020\u0015X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0017\"\u0004\b\u001f\u0010\u0019R\u001a\u0010 \u001a\u00020!X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%\u00a8\u0006@"}, d2 = {"Lcom/example/appthitracnghiem/ui/login/FragmentLogin;", "Lcom/example/appthitracnghiem/ui/base/BaseFragment;", "Lcom/example/appthitracnghiem/ui/login/LoginViewModel;", "()V", "checkSave", "", "getCheckSave", "()Z", "setCheckSave", "(Z)V", "editor", "Landroid/content/SharedPreferences$Editor;", "progressDialog", "Landroid/app/ProgressDialog;", "getProgressDialog", "()Landroid/app/ProgressDialog;", "setProgressDialog", "(Landroid/app/ProgressDialog;)V", "sharedPreferences", "Landroid/content/SharedPreferences;", "strEmail", "", "getStrEmail", "()Ljava/lang/String;", "setStrEmail", "(Ljava/lang/String;)V", "strPassword", "getStrPassword", "setStrPassword", "token", "getToken", "setToken", "useId", "", "getUseId", "()I", "setUseId", "(I)V", "bindData", "", "checkSaveAccount", "hidePassword", "password", "Landroid/widget/EditText;", "hide", "Landroid/widget/ImageView;", "initUi", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onFragmentBack", "onViewCreated", "view", "saveAccount", "email", "setNote", "string", "color", "setText", "app_developmentDebug"})
public final class FragmentLogin extends com.example.appthitracnghiem.ui.base.BaseFragment<com.example.appthitracnghiem.ui.login.LoginViewModel> {
    private android.content.SharedPreferences sharedPreferences;
    private android.content.SharedPreferences.Editor editor;
    private boolean checkSave = false;
    public java.lang.String strEmail;
    public java.lang.String strPassword;
    public android.app.ProgressDialog progressDialog;
    public java.lang.String token;
    private int useId = 0;
    private java.util.HashMap _$_findViewCache;
    
    public FragmentLogin() {
        super();
    }
    
    public final boolean getCheckSave() {
        return false;
    }
    
    public final void setCheckSave(boolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getStrEmail() {
        return null;
    }
    
    public final void setStrEmail(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getStrPassword() {
        return null;
    }
    
    public final void setStrPassword(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.app.ProgressDialog getProgressDialog() {
        return null;
    }
    
    public final void setProgressDialog(@org.jetbrains.annotations.NotNull()
    android.app.ProgressDialog p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getToken() {
        return null;
    }
    
    public final void setToken(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final int getUseId() {
        return 0;
    }
    
    public final void setUseId(int p0) {
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void bindData() {
    }
    
    /**
     * set font
     */
    private final void setText() {
    }
    
    @android.annotation.SuppressLint(value = {"ResourceAsColor"})
    private final void initUi() {
    }
    
    private final void saveAccount(java.lang.String email, java.lang.String password) {
    }
    
    public final void checkSaveAccount() {
    }
    
    private final void hidePassword(android.widget.EditText password, android.widget.ImageView hide) {
    }
    
    public final void setNote(int string, int color) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public boolean onFragmentBack() {
        return false;
    }
}