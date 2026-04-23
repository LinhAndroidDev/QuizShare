package com.example.appthitracnghiem.ui.exercise.exercise.point;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.*;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import com.example.appthitracnghiem.R;
import com.example.appthitracnghiem.databinding.FragmentPointBinding;
import com.example.appthitracnghiem.model.ExamQuestion;
import com.example.appthitracnghiem.ui.base.BaseFragment;
import com.example.appthitracnghiem.ui.exercise.exercise.ExamActivity;
import com.example.appthitracnghiem.ui.exercise.exercise.answer.FragmentAnswer;
import com.example.appthitracnghiem.ui.home.HomeActivity;
import com.example.appthitracnghiem.utils.PreferenceKey;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;
import kotlin.collections.ArrayList;
import kotlin.collections.HashMap;

@kotlin.Suppress(names = {"DEPRECATION"})
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\r\u001a\u00020\u000eH\u0017J\"\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00100\nj\b\u0012\u0004\u0012\u00020\u0010`\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u000eH\u0002J$\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u000eH\u0016J\b\u0010\u001d\u001a\u00020\u001eH\u0016J\u001a\u0010\u001f\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\u00152\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\b\u0010!\u001a\u00020\u000eH\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00058BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\fX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2 = {"Lcom/example/appthitracnghiem/ui/exercise/exercise/point/FragmentPoint;", "Lcom/example/appthitracnghiem/ui/base/BaseFragment;", "Lcom/example/appthitracnghiem/ui/exercise/exercise/point/PointViewModel;", "()V", "_binding", "Lcom/example/appthitracnghiem/databinding/FragmentPointBinding;", "binding", "getBinding", "()Lcom/example/appthitracnghiem/databinding/FragmentPointBinding;", "listExamQuestion", "Ljava/util/ArrayList;", "Lcom/example/appthitracnghiem/model/ExamQuestion;", "Lkotlin/collections/ArrayList;", "bindData", "", "getListAnswer", "", "key", "", "initUi", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onFragmentBack", "", "onViewCreated", "view", "setStatusBar", "app_developmentDebug"})
public final class FragmentPoint extends com.example.appthitracnghiem.ui.base.BaseFragment<com.example.appthitracnghiem.ui.exercise.exercise.point.PointViewModel> {
    private com.example.appthitracnghiem.databinding.FragmentPointBinding _binding;
    private java.util.ArrayList<com.example.appthitracnghiem.model.ExamQuestion> listExamQuestion;
    
    public FragmentPoint() {
        super();
    }
    
    private final com.example.appthitracnghiem.databinding.FragmentPointBinding getBinding() {
        return null;
    }
    
    @java.lang.Override
    public void onViewCreated(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n", "SimpleDateFormat"})
    @java.lang.Override
    public void bindData() {
    }
    
    private final void setStatusBar() {
    }
    
    private final java.util.ArrayList<java.lang.Integer> getListAnswer(java.lang.String key) {
        return null;
    }
    
    private final void initUi() {
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