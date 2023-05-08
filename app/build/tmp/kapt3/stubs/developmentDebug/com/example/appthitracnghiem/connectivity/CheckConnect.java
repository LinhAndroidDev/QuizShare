package com.example.appthitracnghiem.connectivity;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u001a\u0010\u0007\u001a\u00020\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\t\u001a\u0004\u0018\u00010\n\u00a8\u0006\u000b"}, d2 = {"Lcom/example/appthitracnghiem/connectivity/CheckConnect;", "", "()V", "haveNetworkConnected", "", "context", "Landroid/content/Context;", "showToastShort", "", "thongbao", "", "app_developmentDebug"})
public final class CheckConnect {
    @org.jetbrains.annotations.NotNull()
    public static final com.example.appthitracnghiem.connectivity.CheckConnect INSTANCE = null;
    
    private CheckConnect() {
        super();
    }
    
    @android.annotation.SuppressLint(value = {"SuspiciousIndentation"})
    public final boolean haveNetworkConnected(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return false;
    }
    
    public final void showToastShort(@org.jetbrains.annotations.Nullable()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    java.lang.String thongbao) {
    }
}