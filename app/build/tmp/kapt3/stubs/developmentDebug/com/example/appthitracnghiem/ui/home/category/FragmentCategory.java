package com.example.appthitracnghiem.ui.home.category;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.*;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import com.example.appthitracnghiem.R;
import com.example.appthitracnghiem.databinding.FragmentCategoryBinding;
import com.example.appthitracnghiem.model.Subject;
import com.example.appthitracnghiem.ui.base.BaseFragment;
import com.example.appthitracnghiem.ui.department.listdepartment.ListDepartmentViewModel;
import com.example.appthitracnghiem.ui.department.listdepartment.RequestDepartmentInfo;
import com.example.appthitracnghiem.ui.home.category.adapter.SubjectAdapter;
import com.example.appthitracnghiem.ui.home.category.search.SearchSubject;
import com.example.appthitracnghiem.utils.PreferenceKey;

@kotlin.Suppress(names = {"DEPRECATION"})
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0011\u001a\u00020\u0012H\u0017J\b\u0010\u0013\u001a\u00020\u0012H\u0002J$\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u0012H\u0016J\b\u0010\u001d\u001a\u00020\u001eH\u0016J\u001a\u0010\u001f\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\u00152\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\r\u0010!\u001a\u00020\u0012H\u0000\u00a2\u0006\u0002\b\"J\b\u0010#\u001a\u00020\u0012H\u0002J\b\u0010$\u001a\u00020\u0012H\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00058BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\bR*\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\fX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010\u00a8\u0006%"}, d2 = {"Lcom/example/appthitracnghiem/ui/home/category/FragmentCategory;", "Lcom/example/appthitracnghiem/ui/base/BaseFragment;", "Lcom/example/appthitracnghiem/ui/department/listdepartment/ListDepartmentViewModel;", "()V", "_binding", "Lcom/example/appthitracnghiem/databinding/FragmentCategoryBinding;", "binding", "getBinding", "()Lcom/example/appthitracnghiem/databinding/FragmentCategoryBinding;", "listCategory", "Ljava/util/ArrayList;", "Lcom/example/appthitracnghiem/model/Subject;", "Lkotlin/collections/ArrayList;", "getListCategory", "()Ljava/util/ArrayList;", "setListCategory", "(Ljava/util/ArrayList;)V", "bindData", "", "initUi", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onFragmentBack", "", "onViewCreated", "view", "scrollTop", "scrollTop$app_developmentDebug", "setStatusBar", "setText", "app_developmentDebug"})
public final class FragmentCategory extends com.example.appthitracnghiem.ui.base.BaseFragment<com.example.appthitracnghiem.ui.department.listdepartment.ListDepartmentViewModel> {
    private com.example.appthitracnghiem.databinding.FragmentCategoryBinding _binding;
    public java.util.ArrayList<com.example.appthitracnghiem.model.Subject> listCategory;
    
    public FragmentCategory() {
        super();
    }
    
    private final com.example.appthitracnghiem.databinding.FragmentCategoryBinding getBinding() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.ArrayList<com.example.appthitracnghiem.model.Subject> getListCategory() {
        return null;
    }
    
    public final void setListCategory(@org.jetbrains.annotations.NotNull
    java.util.ArrayList<com.example.appthitracnghiem.model.Subject> p0) {
    }
    
    @java.lang.Override
    public void onViewCreated(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @android.annotation.SuppressLint(value = {"NotifyDataSetChanged"})
    @java.lang.Override
    public void bindData() {
    }
    
    private final void setStatusBar() {
    }
    
    private final void initUi() {
    }
    
    private final void setText() {
    }
    
    public final void scrollTop$app_developmentDebug() {
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