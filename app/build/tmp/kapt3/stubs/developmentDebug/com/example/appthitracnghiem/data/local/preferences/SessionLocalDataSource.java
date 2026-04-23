package com.example.appthitracnghiem.data.local.preferences;

import android.content.SharedPreferences;
import com.example.appthitracnghiem.utils.PreferenceKey;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\u0006J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/example/appthitracnghiem/data/local/preferences/SessionLocalDataSource;", "", "sharedPreferences", "Landroid/content/SharedPreferences;", "(Landroid/content/SharedPreferences;)V", "isFirstInstallDone", "", "isUserLoggedIn", "saveUserSession", "", "accessToken", "", "userId", "", "setFirstInstallDone", "app_developmentDebug"})
public final class SessionLocalDataSource {
    private final android.content.SharedPreferences sharedPreferences = null;
    
    @javax.inject.Inject
    public SessionLocalDataSource(@org.jetbrains.annotations.NotNull
    android.content.SharedPreferences sharedPreferences) {
        super();
    }
    
    public final boolean isFirstInstallDone() {
        return false;
    }
    
    public final void setFirstInstallDone() {
    }
    
    public final boolean isUserLoggedIn() {
        return false;
    }
    
    public final void saveUserSession(@org.jetbrains.annotations.NotNull
    java.lang.String accessToken, int userId) {
    }
}