package com.example.appthitracnghiem.ui.login;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentTransaction;
import com.example.appthitracnghiem.R;
import com.example.appthitracnghiem.databinding.FragmentLoginBinding;
import com.example.appthitracnghiem.ui.base.BaseFragment;
import com.example.appthitracnghiem.ui.home.HomeActivity;
import com.example.appthitracnghiem.ui.login.forgetpassword.FragmentForgetPassword;
import com.example.appthitracnghiem.ui.register.RegisterActivity;
import com.example.appthitracnghiem.utils.PreferenceKey;

@kotlin.Suppress(names = {"DEPRECATION"})
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\"\u001a\u00020#H\u0016J\b\u0010$\u001a\u00020#H\u0002J\u0018\u0010%\u001a\u00020#2\u0006\u0010&\u001a\u00020\'2\u0006\u0010(\u001a\u00020)H\u0002J\b\u0010*\u001a\u00020#H\u0003J$\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u0001002\b\u00101\u001a\u0004\u0018\u000102H\u0016J\b\u00103\u001a\u00020#H\u0016J\b\u00104\u001a\u00020\nH\u0016J\u001a\u00105\u001a\u00020#2\u0006\u00106\u001a\u00020,2\b\u00101\u001a\u0004\u0018\u000102H\u0016J\u0018\u00107\u001a\u00020#2\u0006\u00108\u001a\u00020\u001a2\u0006\u0010&\u001a\u00020\u001aH\u0002J\u0018\u00109\u001a\u00020#2\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020;H\u0002J\b\u0010=\u001a\u00020#H\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00058BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0019\u001a\u00020\u001aX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u001f\u001a\u00020\u001aX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u001c\"\u0004\b!\u0010\u001e\u00a8\u0006>"}, d2 = {"Lcom/example/appthitracnghiem/ui/login/FragmentLogin;", "Lcom/example/appthitracnghiem/ui/base/BaseFragment;", "Lcom/example/appthitracnghiem/ui/login/LoginViewModel;", "()V", "_binding", "Lcom/example/appthitracnghiem/databinding/FragmentLoginBinding;", "binding", "getBinding", "()Lcom/example/appthitracnghiem/databinding/FragmentLoginBinding;", "checkSave", "", "getCheckSave", "()Z", "setCheckSave", "(Z)V", "editor", "Landroid/content/SharedPreferences$Editor;", "progressDialog", "Landroid/app/ProgressDialog;", "getProgressDialog", "()Landroid/app/ProgressDialog;", "setProgressDialog", "(Landroid/app/ProgressDialog;)V", "sharedPreferences", "Landroid/content/SharedPreferences;", "strEmail", "", "getStrEmail", "()Ljava/lang/String;", "setStrEmail", "(Ljava/lang/String;)V", "strPassword", "getStrPassword", "setStrPassword", "bindData", "", "checkSaveAccount", "hidePassword", "password", "Landroid/widget/EditText;", "hide", "Landroid/widget/ImageView;", "initUi", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onFragmentBack", "onViewCreated", "view", "saveAccount", "email", "setNote", "string", "", "color", "setText", "app_developmentDebug"})
public final class FragmentLogin extends com.example.appthitracnghiem.ui.base.BaseFragment<com.example.appthitracnghiem.ui.login.LoginViewModel> {
    private com.example.appthitracnghiem.databinding.FragmentLoginBinding _binding;
    private android.content.SharedPreferences sharedPreferences;
    private android.content.SharedPreferences.Editor editor;
    private boolean checkSave = false;
    public java.lang.String strEmail;
    public java.lang.String strPassword;
    public android.app.ProgressDialog progressDialog;
    
    public FragmentLogin() {
        super();
    }
    
    private final com.example.appthitracnghiem.databinding.FragmentLoginBinding getBinding() {
        return null;
    }
    
    public final boolean getCheckSave() {
        return false;
    }
    
    public final void setCheckSave(boolean p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getStrEmail() {
        return null;
    }
    
    public final void setStrEmail(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getStrPassword() {
        return null;
    }
    
    public final void setStrPassword(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final android.app.ProgressDialog getProgressDialog() {
        return null;
    }
    
    public final void setProgressDialog(@org.jetbrains.annotations.NotNull
    android.app.ProgressDialog p0) {
    }
    
    @java.lang.Override
    public void onViewCreated(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override
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
    
    private final void checkSaveAccount() {
    }
    
    private final void hidePassword(android.widget.EditText password, android.widget.ImageView hide) {
    }
    
    private final void setNote(int string, int color) {
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override
    public void onDestroyView() {
    }
    
    @java.lang.Override
    public boolean onFragmentBack() {
        return false;
    }
}