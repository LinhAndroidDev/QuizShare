package com.example.appthitracnghiem.presentation.main;

import androidx.lifecycle.ViewModel;
import com.example.appthitracnghiem.data.local.preferences.SessionLocalDataSource;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;

@dagger.hilt.android.lifecycle.HiltViewModel
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\u0006J\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/example/appthitracnghiem/presentation/main/SplashViewModel;", "Landroidx/lifecycle/ViewModel;", "sessionLocalDataSource", "Lcom/example/appthitracnghiem/data/local/preferences/SessionLocalDataSource;", "(Lcom/example/appthitracnghiem/data/local/preferences/SessionLocalDataSource;)V", "isFirstInstallDone", "", "isUserLoggedIn", "markFirstInstallDone", "", "app_developmentDebug"})
public final class SplashViewModel extends androidx.lifecycle.ViewModel {
    private final com.example.appthitracnghiem.data.local.preferences.SessionLocalDataSource sessionLocalDataSource = null;
    
    @javax.inject.Inject
    public SplashViewModel(@org.jetbrains.annotations.NotNull
    com.example.appthitracnghiem.data.local.preferences.SessionLocalDataSource sessionLocalDataSource) {
        super();
    }
    
    public final boolean isFirstInstallDone() {
        return false;
    }
    
    public final void markFirstInstallDone() {
    }
    
    public final boolean isUserLoggedIn() {
        return false;
    }
}