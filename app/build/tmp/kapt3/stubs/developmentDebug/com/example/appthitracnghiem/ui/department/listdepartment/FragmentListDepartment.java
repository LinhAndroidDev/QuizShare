package com.example.appthitracnghiem.ui.department.listdepartment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.appthitracnghiem.R;
import com.example.appthitracnghiem.databinding.FragmentListDepartmentBinding;
import com.example.appthitracnghiem.ui.base.BaseFragment;
import com.example.appthitracnghiem.ui.department.listdepartment.adapter.ListDepartmentAdapter;
import com.example.appthitracnghiem.utils.PreferenceKey;

@kotlin.Suppress(names = {"DEPRECATION"})
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u001cH\u0003J$\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\b\u0010&\u001a\u00020\u001cH\u0016J\b\u0010\'\u001a\u00020(H\u0016J\u001a\u0010)\u001a\u00020\u001c2\u0006\u0010*\u001a\u00020\u001f2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\f\u0010+\u001a\u00020\u001c*\u00020\u001fH\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\u00058BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a\u00a8\u0006,"}, d2 = {"Lcom/example/appthitracnghiem/ui/department/listdepartment/FragmentListDepartment;", "Lcom/example/appthitracnghiem/ui/base/BaseFragment;", "Lcom/example/appthitracnghiem/ui/department/listdepartment/ListDepartmentViewModel;", "()V", "_binding", "Lcom/example/appthitracnghiem/databinding/FragmentListDepartmentBinding;", "accessToken", "", "getAccessToken", "()Ljava/lang/String;", "setAccessToken", "(Ljava/lang/String;)V", "binding", "getBinding", "()Lcom/example/appthitracnghiem/databinding/FragmentListDepartmentBinding;", "listDepartmentAdapter", "Lcom/example/appthitracnghiem/ui/department/listdepartment/adapter/ListDepartmentAdapter;", "getListDepartmentAdapter", "()Lcom/example/appthitracnghiem/ui/department/listdepartment/adapter/ListDepartmentAdapter;", "setListDepartmentAdapter", "(Lcom/example/appthitracnghiem/ui/department/listdepartment/adapter/ListDepartmentAdapter;)V", "userId", "", "getUserId", "()I", "setUserId", "(I)V", "bindData", "", "initUi", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onFragmentBack", "", "onViewCreated", "view", "hideKeyboard", "app_developmentDebug"})
public final class FragmentListDepartment extends com.example.appthitracnghiem.ui.base.BaseFragment<com.example.appthitracnghiem.ui.department.listdepartment.ListDepartmentViewModel> {
    private com.example.appthitracnghiem.databinding.FragmentListDepartmentBinding _binding;
    public com.example.appthitracnghiem.ui.department.listdepartment.adapter.ListDepartmentAdapter listDepartmentAdapter;
    public java.lang.String accessToken;
    private int userId = 0;
    
    public FragmentListDepartment() {
        super();
    }
    
    private final com.example.appthitracnghiem.databinding.FragmentListDepartmentBinding getBinding() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.appthitracnghiem.ui.department.listdepartment.adapter.ListDepartmentAdapter getListDepartmentAdapter() {
        return null;
    }
    
    public final void setListDepartmentAdapter(@org.jetbrains.annotations.NotNull
    com.example.appthitracnghiem.ui.department.listdepartment.adapter.ListDepartmentAdapter p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getAccessToken() {
        return null;
    }
    
    public final void setAccessToken(@org.jetbrains.annotations.NotNull
    java.lang.String p0) {
    }
    
    public final int getUserId() {
        return 0;
    }
    
    public final void setUserId(int p0) {
    }
    
    @java.lang.Override
    public void onViewCreated(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void hideKeyboard(android.view.View $this$hideKeyboard) {
    }
    
    @java.lang.Override
    public void bindData() {
    }
    
    @android.annotation.SuppressLint(value = {"ClickableViewAccessibility"})
    private final void initUi() {
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