package com.example.appthitracnghiem.ui.home.home;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.*;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.example.appthitracnghiem.R;
import com.example.appthitracnghiem.databinding.FragmentHomeBinding;
import com.example.appthitracnghiem.ui.base.BaseFragment;
import com.example.appthitracnghiem.ui.home.HomeActivity;
import com.example.appthitracnghiem.ui.home.HomeViewModel;
import com.example.appthitracnghiem.ui.home.RequestUserInfo;
import com.example.appthitracnghiem.ui.home.home.adapter.ViewPagerDepartment;
import com.example.appthitracnghiem.ui.home.profile.FragmentProfile;
import com.example.appthitracnghiem.utils.PreferenceKey;
import com.squareup.picasso.Picasso;

@kotlin.Suppress(names = {"DEPRECATION"})
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\r\u001a\u00020\u000eH\u0017J\b\u0010\u000f\u001a\u00020\u000eH\u0002J$\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u000eH\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\u001a\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u00112\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\r\u0010\u001d\u001a\u00020\u000eH\u0000\u00a2\u0006\u0002\b\u001eJ\b\u0010\u001f\u001a\u00020\u000eH\u0002J\b\u0010 \u001a\u00020\u000eH\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00058BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/example/appthitracnghiem/ui/home/home/FragmentHome;", "Lcom/example/appthitracnghiem/ui/base/BaseFragment;", "Lcom/example/appthitracnghiem/ui/home/HomeViewModel;", "()V", "_binding", "Lcom/example/appthitracnghiem/databinding/FragmentHomeBinding;", "binding", "getBinding", "()Lcom/example/appthitracnghiem/databinding/FragmentHomeBinding;", "scrollPosition", "", "viewPagerDepartment", "Lcom/example/appthitracnghiem/ui/home/home/adapter/ViewPagerDepartment;", "bindData", "", "initUi", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onFragmentBack", "", "onViewCreated", "view", "scrollTop", "scrollTop$app_developmentDebug", "setStatusBar", "setText", "app_developmentDebug"})
public final class FragmentHome extends com.example.appthitracnghiem.ui.base.BaseFragment<com.example.appthitracnghiem.ui.home.HomeViewModel> {
    private com.example.appthitracnghiem.databinding.FragmentHomeBinding _binding;
    private com.example.appthitracnghiem.ui.home.home.adapter.ViewPagerDepartment viewPagerDepartment;
    private double scrollPosition = 0.0;
    
    public FragmentHome() {
        super();
    }
    
    private final com.example.appthitracnghiem.databinding.FragmentHomeBinding getBinding() {
        return null;
    }
    
    @java.lang.Override
    public void onViewCreated(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initUi() {
    }
    
    private final void setStatusBar() {
    }
    
    @android.annotation.SuppressLint(value = {"CommitPrefEdits"})
    @java.lang.Override
    public void bindData() {
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