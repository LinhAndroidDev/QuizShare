package com.example.appthitracnghiem.ui.home.history.saved.test;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Gravity;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.appthitracnghiem.R;
import com.example.appthitracnghiem.databinding.FragmentHistoryTestSavedBinding;
import com.example.appthitracnghiem.model.Test;
import com.example.appthitracnghiem.ui.base.BaseFragment;
import com.example.appthitracnghiem.ui.home.history.saved.test.adapter.HistoryTestAdapter;
import com.example.appthitracnghiem.ui.home.history.test.general.RequestExamHistory;
import com.example.appthitracnghiem.utils.PreferenceKey;

@kotlin.Suppress(names = {"DEPRECATION"})
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u0000 $2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001$B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u000f\u001a\u00020\u0010H\u0017J\b\u0010\u0011\u001a\u00020\u0010H\u0003J$\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0010H\u0016J\u001a\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u00132\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J0\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020 2\u0006\u0010#\u001a\u00020 H\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00058BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\u00a8\u0006%"}, d2 = {"Lcom/example/appthitracnghiem/ui/home/history/saved/test/FragmentHistoryTestSaved;", "Lcom/example/appthitracnghiem/ui/base/BaseFragment;", "Lcom/example/appthitracnghiem/ui/home/history/saved/test/TestSavedViewModel;", "()V", "_binding", "Lcom/example/appthitracnghiem/databinding/FragmentHistoryTestSavedBinding;", "binding", "getBinding", "()Lcom/example/appthitracnghiem/databinding/FragmentHistoryTestSavedBinding;", "historyTestAdapter", "Lcom/example/appthitracnghiem/ui/home/history/saved/test/adapter/HistoryTestAdapter;", "getHistoryTestAdapter", "()Lcom/example/appthitracnghiem/ui/home/history/saved/test/adapter/HistoryTestAdapter;", "setHistoryTestAdapter", "(Lcom/example/appthitracnghiem/ui/home/history/saved/test/adapter/HistoryTestAdapter;)V", "bindData", "", "initUi", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "showPopupMenu", "anchor", "layout", "", "x", "y", "position", "Companion", "app_developmentDebug"})
public final class FragmentHistoryTestSaved extends com.example.appthitracnghiem.ui.base.BaseFragment<com.example.appthitracnghiem.ui.home.history.saved.test.TestSavedViewModel> {
    private com.example.appthitracnghiem.databinding.FragmentHistoryTestSavedBinding _binding;
    public com.example.appthitracnghiem.ui.home.history.saved.test.adapter.HistoryTestAdapter historyTestAdapter;
    @org.jetbrains.annotations.NotNull
    public static final com.example.appthitracnghiem.ui.home.history.saved.test.FragmentHistoryTestSaved.Companion Companion = null;
    @org.jetbrains.annotations.NotNull
    private static java.lang.String department = "";
    @org.jetbrains.annotations.NotNull
    private static java.lang.String subject = "";
    
    public FragmentHistoryTestSaved() {
        super();
    }
    
    private final com.example.appthitracnghiem.databinding.FragmentHistoryTestSavedBinding getBinding() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.appthitracnghiem.ui.home.history.saved.test.adapter.HistoryTestAdapter getHistoryTestAdapter() {
        return null;
    }
    
    public final void setHistoryTestAdapter(@org.jetbrains.annotations.NotNull
    com.example.appthitracnghiem.ui.home.history.saved.test.adapter.HistoryTestAdapter p0) {
    }
    
    @java.lang.Override
    public void onViewCreated(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    @java.lang.Override
    public void bindData() {
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    private final void initUi() {
    }
    
    private final void showPopupMenu(android.view.View anchor, int layout, int x, int y, int position) {
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
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\b\u00a8\u0006\f"}, d2 = {"Lcom/example/appthitracnghiem/ui/home/history/saved/test/FragmentHistoryTestSaved$Companion;", "", "()V", "department", "", "getDepartment", "()Ljava/lang/String;", "setDepartment", "(Ljava/lang/String;)V", "subject", "getSubject", "setSubject", "app_developmentDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getDepartment() {
            return null;
        }
        
        public final void setDepartment(@org.jetbrains.annotations.NotNull
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getSubject() {
            return null;
        }
        
        public final void setSubject(@org.jetbrains.annotations.NotNull
        java.lang.String p0) {
        }
    }
}