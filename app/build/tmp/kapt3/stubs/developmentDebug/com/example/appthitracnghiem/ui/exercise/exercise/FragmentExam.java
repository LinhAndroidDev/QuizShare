package com.example.appthitracnghiem.ui.exercise.exercise;

import java.lang.System;

@kotlin.Suppress(names = {"DEPRECATION", "NAME_SHADOWING"})
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u0018H\u0002J&\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\b\u0010\"\u001a\u00020#H\u0016J\u001a\u0010$\u001a\u00020\u00182\u0006\u0010%\u001a\u00020\u001b2\b\u0010 \u001a\u0004\u0018\u00010!H\u0017J\u0010\u0010&\u001a\u00020\u00182\u0006\u0010\'\u001a\u00020\u0005H\u0007J\u0010\u0010(\u001a\u00020\u00182\u0006\u0010)\u001a\u00020\u0005H\u0002J\b\u0010*\u001a\u00020\u0018H\u0002J0\u0010+\u001a\u00020\u00182\u0006\u0010%\u001a\u00020\u001b2\u0006\u0010,\u001a\u00020\u00052\u0006\u0010-\u001a\u00020\u00052\u0006\u0010.\u001a\u00020\u00052\u0006\u0010/\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\u00050\tj\b\u0012\u0004\u0012\u00020\u0005`\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00060"}, d2 = {"Lcom/example/appthitracnghiem/ui/exercise/exercise/FragmentExam;", "Lcom/example/appthitracnghiem/ui/base/BaseFragment;", "Lcom/example/appthitracnghiem/ui/exercise/exercise/ExamViewModel;", "()V", "SIZE_LIST_QUIZ", "", "countDownTimer", "Landroid/os/CountDownTimer;", "listAnswer", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "listExamQuestion", "", "Lcom/example/appthitracnghiem/model/ExamQuestion;", "listQuestion", "Lcom/example/appthitracnghiem/model/PositiveQuestion;", "menuQuestionAdapter", "Lcom/example/appthitracnghiem/ui/exercise/exercise/adapter/MenuQuestionAdapter;", "minutes", "positionQuestion", "positiveAnswer", "seconds", "totalCount", "bindData", "", "initUi", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onFragmentBack", "", "onViewCreated", "view", "setTextView", "psQuestion", "setTime", "time", "showLayoutSubmit", "showMenuQuestion", "popupViewId", "x", "y", "gravity", "app_developmentDebug"})
public final class FragmentExam extends com.example.appthitracnghiem.ui.base.BaseFragment<com.example.appthitracnghiem.ui.exercise.exercise.ExamViewModel> {
    private java.util.List<com.example.appthitracnghiem.model.PositiveQuestion> listQuestion;
    private com.example.appthitracnghiem.ui.exercise.exercise.adapter.MenuQuestionAdapter menuQuestionAdapter;
    private android.os.CountDownTimer countDownTimer;
    private int totalCount = 0;
    private int minutes = 0;
    private int seconds = 0;
    private int positionQuestion = 0;
    private int positiveAnswer = 0;
    private int SIZE_LIST_QUIZ = 0;
    private java.util.List<com.example.appthitracnghiem.model.ExamQuestion> listExamQuestion;
    private java.util.ArrayList<java.lang.Integer> listAnswer;
    private java.util.HashMap _$_findViewCache;
    
    public FragmentExam() {
        super();
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n", "ResourceAsColor"})
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void bindData() {
    }
    
    private final void setTime(int time) {
    }
    
    private final void initUi() {
    }
    
    private final void showLayoutSubmit() {
    }
    
    private final void showMenuQuestion(android.view.View view, int popupViewId, int x, int y, int gravity) {
    }
    
    @android.annotation.SuppressLint(value = {"ResourceAsColor"})
    public final void setTextView(int psQuestion) {
    }
    
    @java.lang.Override()
    public boolean onFragmentBack() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
}