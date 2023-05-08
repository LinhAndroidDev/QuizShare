package com.example.appthitracnghiem.ui.base.animation;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0012B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001a\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f\u00a8\u0006\u0013"}, d2 = {"Lcom/example/appthitracnghiem/ui/base/animation/TranslateAnimation;", "Landroid/view/View$OnTouchListener;", "context", "Landroid/content/Context;", "viewAnimation", "Landroid/view/View;", "(Landroid/content/Context;Landroid/view/View;)V", "gestureDetector", "Landroid/view/GestureDetector;", "getGestureDetector", "()Landroid/view/GestureDetector;", "setGestureDetector", "(Landroid/view/GestureDetector;)V", "onTouch", "", "p0", "p1", "Landroid/view/MotionEvent;", "SimpleGestureDetector", "app_developmentDebug"})
public final class TranslateAnimation implements android.view.View.OnTouchListener {
    @org.jetbrains.annotations.NotNull()
    private android.view.GestureDetector gestureDetector;
    
    public TranslateAnimation(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.view.View viewAnimation) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.view.GestureDetector getGestureDetector() {
        return null;
    }
    
    public final void setGestureDetector(@org.jetbrains.annotations.NotNull()
    android.view.GestureDetector p0) {
    }
    
    @java.lang.Override()
    public boolean onTouch(@org.jetbrains.annotations.Nullable()
    android.view.View p0, @org.jetbrains.annotations.NotNull()
    android.view.MotionEvent p1) {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\r\u001a\u00020\u000eH\u0002J(\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0016J\b\u0010\u0016\u001a\u00020\u000eH\u0002R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\u0004\u00a8\u0006\u0017"}, d2 = {"Lcom/example/appthitracnghiem/ui/base/animation/TranslateAnimation$SimpleGestureDetector;", "Landroid/view/GestureDetector$SimpleOnGestureListener;", "viewAnimation", "Landroid/view/View;", "(Landroid/view/View;)V", "isFinishAnimation", "", "()Z", "setFinishAnimation", "(Z)V", "getViewAnimation", "()Landroid/view/View;", "setViewAnimation", "hiddenView", "", "onScroll", "e1", "Landroid/view/MotionEvent;", "e2", "distanceX", "", "distanceY", "showView", "app_developmentDebug"})
    public static final class SimpleGestureDetector extends android.view.GestureDetector.SimpleOnGestureListener {
        @org.jetbrains.annotations.NotNull()
        private android.view.View viewAnimation;
        private boolean isFinishAnimation = true;
        
        public SimpleGestureDetector(@org.jetbrains.annotations.NotNull()
        android.view.View viewAnimation) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.view.View getViewAnimation() {
            return null;
        }
        
        public final void setViewAnimation(@org.jetbrains.annotations.NotNull()
        android.view.View p0) {
        }
        
        public final boolean isFinishAnimation() {
            return false;
        }
        
        public final void setFinishAnimation(boolean p0) {
        }
        
        @java.lang.Override()
        public boolean onScroll(@org.jetbrains.annotations.NotNull()
        android.view.MotionEvent e1, @org.jetbrains.annotations.NotNull()
        android.view.MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }
        
        private final void showView() {
        }
        
        private final void hiddenView() {
        }
    }
}