package com.example.appthitracnghiem.ui.department.listtest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.PopupWindow;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.appthitracnghiem.R;
import com.example.appthitracnghiem.databinding.FragmentListTestBinding;
import com.example.appthitracnghiem.ui.base.BaseFragment;
import com.example.appthitracnghiem.ui.department.listtest.adapter.TestAdapter;
import com.example.appthitracnghiem.utils.PreferenceKey;

@kotlin.Suppress(names = {"DEPRECATION"})
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0010H\u0003J$\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0010H\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\u001a\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u00132\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\b\u0010\u001f\u001a\u00020\u0010H\u0002J0\u0010 \u001a\u00020\u00102\u0006\u0010!\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020#2\u0006\u0010&\u001a\u00020#H\u0002J\f\u0010\'\u001a\u00020\u0010*\u00020\u0013H\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00058BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\u00a8\u0006("}, d2 = {"Lcom/example/appthitracnghiem/ui/department/listtest/FragmentListTest;", "Lcom/example/appthitracnghiem/ui/base/BaseFragment;", "Lcom/example/appthitracnghiem/ui/department/listtest/ListTestViewModel;", "()V", "_binding", "Lcom/example/appthitracnghiem/databinding/FragmentListTestBinding;", "binding", "getBinding", "()Lcom/example/appthitracnghiem/databinding/FragmentListTestBinding;", "testAdapter", "Lcom/example/appthitracnghiem/ui/department/listtest/adapter/TestAdapter;", "getTestAdapter", "()Lcom/example/appthitracnghiem/ui/department/listtest/adapter/TestAdapter;", "setTestAdapter", "(Lcom/example/appthitracnghiem/ui/department/listtest/adapter/TestAdapter;)V", "bindData", "", "initUi", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onFragmentBack", "", "onViewCreated", "view", "setText", "showMenuCreate", "anchor", "layout", "", "x", "y", "position", "hideKeyboard", "app_developmentDebug"})
public final class FragmentListTest extends com.example.appthitracnghiem.ui.base.BaseFragment<com.example.appthitracnghiem.ui.department.listtest.ListTestViewModel> {
    private com.example.appthitracnghiem.databinding.FragmentListTestBinding _binding;
    public com.example.appthitracnghiem.ui.department.listtest.adapter.TestAdapter testAdapter;
    
    public FragmentListTest() {
        super();
    }
    
    private final com.example.appthitracnghiem.databinding.FragmentListTestBinding getBinding() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.appthitracnghiem.ui.department.listtest.adapter.TestAdapter getTestAdapter() {
        return null;
    }
    
    public final void setTestAdapter(@org.jetbrains.annotations.NotNull
    com.example.appthitracnghiem.ui.department.listtest.adapter.TestAdapter p0) {
    }
    
    @java.lang.Override
    public void onViewCreated(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void hideKeyboard(android.view.View $this$hideKeyboard) {
    }
    
    private final void setText() {
    }
    
    @java.lang.Override
    public void bindData() {
    }
    
    @android.annotation.SuppressLint(value = {"ClickableViewAccessibility"})
    private final void initUi() {
    }
    
    private final void showMenuCreate(android.view.View anchor, int layout, int x, int y, int position) {
    }
    
    @java.lang.Override
    public boolean onFragmentBack() {
        return false;
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
}