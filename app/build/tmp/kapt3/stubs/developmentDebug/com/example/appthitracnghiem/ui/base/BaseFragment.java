package com.example.appthitracnghiem.ui.base;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u00032\u00020\u0004B\u0005\u00a2\u0006\u0002\u0010\u0005J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u001a\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016R\u001c\u0010\u0006\u001a\u00028\u0000X\u0086.\u00a2\u0006\u0010\n\u0002\u0010\u000b\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lcom/example/appthitracnghiem/ui/base/BaseFragment;", "V", "Lcom/example/appthitracnghiem/ui/base/BaseViewModel;", "Landroidx/fragment/app/Fragment;", "Lcom/example/appthitracnghiem/ui/base/IOnFragmentBackListener;", "()V", "viewModel", "getViewModel", "()Lcom/example/appthitracnghiem/ui/base/BaseViewModel;", "setViewModel", "(Lcom/example/appthitracnghiem/ui/base/BaseViewModel;)V", "Lcom/example/appthitracnghiem/ui/base/BaseViewModel;", "bindData", "", "onFragmentBack", "", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "app_developmentDebug"})
public abstract class BaseFragment<V extends com.example.appthitracnghiem.ui.base.BaseViewModel> extends androidx.fragment.app.Fragment implements com.example.appthitracnghiem.ui.base.IOnFragmentBackListener {
    public V viewModel;
    private java.util.HashMap _$_findViewCache;
    
    public BaseFragment() {
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
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public boolean onFragmentBack() {
        return false;
    }
    
    public void bindData() {
    }
}