package com.example.appthitracnghiem.ui.home.history.test.general;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.appthitracnghiem.R;
import com.example.appthitracnghiem.databinding.FragmentHistoryTestBinding;
import com.example.appthitracnghiem.model.HistoryExam;
import com.example.appthitracnghiem.ui.base.BaseFragment;
import com.example.appthitracnghiem.ui.home.HomeActivity;
import com.example.appthitracnghiem.ui.home.history.test.general.adapter.HistoryTestAdapter;
import com.example.appthitracnghiem.utils.PreferenceKey;

@kotlin.Suppress(names = {"DEPRECATION"})
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0011H\u0002J$\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u0011H\u0016J\b\u0010\u001c\u001a\u00020\u001dH\u0016J\u001a\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\u00142\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010 \u001a\u00020\u0011H\u0002J0\u0010!\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020\u00142\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020$2\u0006\u0010\'\u001a\u00020$H\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00058BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\nX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f\u00a8\u0006("}, d2 = {"Lcom/example/appthitracnghiem/ui/home/history/test/general/FragmentHistoryTest;", "Lcom/example/appthitracnghiem/ui/base/BaseFragment;", "Lcom/example/appthitracnghiem/ui/home/history/test/general/HistoryTestViewModel;", "()V", "_binding", "Lcom/example/appthitracnghiem/databinding/FragmentHistoryTestBinding;", "binding", "getBinding", "()Lcom/example/appthitracnghiem/databinding/FragmentHistoryTestBinding;", "testAdapter", "Lcom/example/appthitracnghiem/ui/home/history/test/general/adapter/HistoryTestAdapter;", "testAdapterUser", "getTestAdapterUser", "()Lcom/example/appthitracnghiem/ui/home/history/test/general/adapter/HistoryTestAdapter;", "setTestAdapterUser", "(Lcom/example/appthitracnghiem/ui/home/history/test/general/adapter/HistoryTestAdapter;)V", "bindData", "", "initUi", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onFragmentBack", "", "onViewCreated", "view", "setText", "showPopupMenu", "anchor", "layout", "", "x", "y", "position", "app_developmentDebug"})
public final class FragmentHistoryTest extends com.example.appthitracnghiem.ui.base.BaseFragment<com.example.appthitracnghiem.ui.home.history.test.general.HistoryTestViewModel> {
    private com.example.appthitracnghiem.databinding.FragmentHistoryTestBinding _binding;
    private com.example.appthitracnghiem.ui.home.history.test.general.adapter.HistoryTestAdapter testAdapter;
    public com.example.appthitracnghiem.ui.home.history.test.general.adapter.HistoryTestAdapter testAdapterUser;
    
    public FragmentHistoryTest() {
        super();
    }
    
    private final com.example.appthitracnghiem.databinding.FragmentHistoryTestBinding getBinding() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.appthitracnghiem.ui.home.history.test.general.adapter.HistoryTestAdapter getTestAdapterUser() {
        return null;
    }
    
    public final void setTestAdapterUser(@org.jetbrains.annotations.NotNull
    com.example.appthitracnghiem.ui.home.history.test.general.adapter.HistoryTestAdapter p0) {
    }
    
    @java.lang.Override
    public void onViewCreated(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override
    public void bindData() {
    }
    
    private final void initUi() {
    }
    
    private final void showPopupMenu(android.view.View anchor, int layout, int x, int y, int position) {
    }
    
    private final void setText() {
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