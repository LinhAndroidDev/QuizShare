package com.example.appthitracnghiem.ui.home.createtest.review;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u001e\u001a\u00020\u001fH\u0016J&\u0010 \u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010!0\u0005j\n\u0012\u0006\u0012\u0004\u0018\u00010!`\u00072\b\u0010\"\u001a\u0004\u0018\u00010#H\u0002J\b\u0010$\u001a\u00020\u001fH\u0003J&\u0010%\u001a\u0004\u0018\u00010&2\u0006\u0010\'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010*2\b\u0010+\u001a\u0004\u0018\u00010,H\u0016J\u001a\u0010-\u001a\u00020\u001f2\u0006\u0010.\u001a\u00020&2\b\u0010+\u001a\u0004\u0018\u00010,H\u0016J\b\u0010/\u001a\u00020\u001fH\u0002J\u0010\u00100\u001a\u00020\u001f2\u0006\u00101\u001a\u00020\rH\u0002R*\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u000f\"\u0004\b\u001a\u0010\u0011R\u001a\u0010\u001b\u001a\u00020\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u000f\"\u0004\b\u001d\u0010\u0011\u00a8\u00062"}, d2 = {"Lcom/example/appthitracnghiem/ui/home/createtest/review/FragmentReviewCreateExam;", "Lcom/example/appthitracnghiem/ui/base/BaseFragment;", "Lcom/example/appthitracnghiem/ui/home/createtest/review/CreateExamViewModel;", "()V", "listTextViewAnswer", "Ljava/util/ArrayList;", "Landroid/widget/TextView;", "Lkotlin/collections/ArrayList;", "getListTextViewAnswer", "()Ljava/util/ArrayList;", "setListTextViewAnswer", "(Ljava/util/ArrayList;)V", "numberQuiz", "", "getNumberQuiz", "()I", "setNumberQuiz", "(I)V", "positionReviewAdapter", "Lcom/example/appthitracnghiem/ui/home/createtest/review/PositionReviewAdapter;", "getPositionReviewAdapter", "()Lcom/example/appthitracnghiem/ui/home/createtest/review/PositionReviewAdapter;", "setPositionReviewAdapter", "(Lcom/example/appthitracnghiem/ui/home/createtest/review/PositionReviewAdapter;)V", "questionIndex", "getQuestionIndex", "setQuestionIndex", "time", "getTime", "setTime", "bindData", "", "getListQuestion", "Lcom/example/appthitracnghiem/model/CreateQuestion;", "key", "", "initUi", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "setText", "setTextExam", "index", "app_developmentDebug"})
public final class FragmentReviewCreateExam extends com.example.appthitracnghiem.ui.base.BaseFragment<com.example.appthitracnghiem.ui.home.createtest.review.CreateExamViewModel> {
    public com.example.appthitracnghiem.ui.home.createtest.review.PositionReviewAdapter positionReviewAdapter;
    private int questionIndex = 0;
    private int numberQuiz = 0;
    @org.jetbrains.annotations.NotNull()
    private java.util.ArrayList<android.widget.TextView> listTextViewAnswer;
    private int time = 0;
    private java.util.HashMap _$_findViewCache;
    
    public FragmentReviewCreateExam() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.appthitracnghiem.ui.home.createtest.review.PositionReviewAdapter getPositionReviewAdapter() {
        return null;
    }
    
    public final void setPositionReviewAdapter(@org.jetbrains.annotations.NotNull()
    com.example.appthitracnghiem.ui.home.createtest.review.PositionReviewAdapter p0) {
    }
    
    public final int getQuestionIndex() {
        return 0;
    }
    
    public final void setQuestionIndex(int p0) {
    }
    
    public final int getNumberQuiz() {
        return 0;
    }
    
    public final void setNumberQuiz(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<android.widget.TextView> getListTextViewAnswer() {
        return null;
    }
    
    public final void setListTextViewAnswer(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<android.widget.TextView> p0) {
    }
    
    public final int getTime() {
        return 0;
    }
    
    public final void setTime(int p0) {
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void bindData() {
    }
    
    private final void setTextExam(int index) {
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    private final void initUi() {
    }
    
    private final java.util.ArrayList<com.example.appthitracnghiem.model.CreateQuestion> getListQuestion(java.lang.String key) {
        return null;
    }
    
    private final void setText() {
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