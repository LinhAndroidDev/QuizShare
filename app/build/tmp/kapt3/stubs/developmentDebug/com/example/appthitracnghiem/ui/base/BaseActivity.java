package com.example.appthitracnghiem.ui.base;

import java.lang.System;

@kotlin.Suppress(names = {"DEPRECATION"})
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\fH\u0016J\u0012\u0010\u000e\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014R\u001c\u0010\u0005\u001a\u00028\u0000X\u0086.\u00a2\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t\u00a8\u0006\u0011"}, d2 = {"Lcom/example/appthitracnghiem/ui/base/BaseActivity;", "V", "Lcom/example/appthitracnghiem/ui/base/BaseViewModel;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "viewModel", "getViewModel", "()Lcom/example/appthitracnghiem/ui/base/BaseViewModel;", "setViewModel", "(Lcom/example/appthitracnghiem/ui/base/BaseViewModel;)V", "Lcom/example/appthitracnghiem/ui/base/BaseViewModel;", "bindData", "", "finish", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_developmentDebug"})
public abstract class BaseActivity<V extends com.example.appthitracnghiem.ui.base.BaseViewModel> extends androidx.appcompat.app.AppCompatActivity {
    public V viewModel;
    private java.util.HashMap _$_findViewCache;
    
    public BaseActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final V getViewModel() {
        return null;
    }
    
    public final void setViewModel(@org.jetbrains.annotations.NotNull()
    V p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    public void bindData() {
    }
    
    @java.lang.Override()
    public void finish() {
    }
}