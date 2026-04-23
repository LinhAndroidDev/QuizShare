package com.example.appthitracnghiem.ui.home;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.appthitracnghiem.R;
import com.example.appthitracnghiem.connectivity.CheckConnect;
import com.example.appthitracnghiem.databinding.ActivityHomePageBinding;
import com.example.appthitracnghiem.ui.base.BaseActivity;
import com.example.appthitracnghiem.ui.base.BaseFragment;
import com.example.appthitracnghiem.ui.home.category.FragmentCategory;
import com.example.appthitracnghiem.ui.home.createtest.FragmentCreateTest;
import com.example.appthitracnghiem.ui.home.history.FragmentHistory;
import com.example.appthitracnghiem.ui.home.home.FragmentHome;
import com.example.appthitracnghiem.ui.home.profile.FragmentProfile;

@kotlin.Suppress(names = {"DEPRECATION", "DEPRECATED_IDENTITY_EQUALS"})
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\r\u0010\u000e\u001a\u00020\tH\u0000\u00a2\u0006\u0002\b\u000fJ\b\u0010\u0010\u001a\u00020\tH\u0002J\u0015\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u0013H\u0000\u00a2\u0006\u0002\b\u0014J\b\u0010\u0015\u001a\u00020\tH\u0016J\u0012\u0010\u0016\u001a\u00020\t2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0015J\b\u0010\u0019\u001a\u00020\tH\u0002J\b\u0010\u001a\u001a\u00020\tH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/example/appthitracnghiem/ui/home/HomeActivity;", "Lcom/example/appthitracnghiem/ui/base/BaseActivity;", "Lcom/example/appthitracnghiem/ui/home/HomeViewModel;", "()V", "backPressTime", "", "binding", "Lcom/example/appthitracnghiem/databinding/ActivityHomePageBinding;", "attachFragment", "", "fragmentHolderLayoutId", "", "fragment", "Landroidx/fragment/app/Fragment;", "clickAvatar", "clickAvatar$app_developmentDebug", "initUi", "loadingVisible", "isLoading", "", "loadingVisible$app_developmentDebug", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "resetTab", "setSelectIcon", "app_developmentDebug"})
public final class HomeActivity extends com.example.appthitracnghiem.ui.base.BaseActivity<com.example.appthitracnghiem.ui.home.HomeViewModel> {
    private com.example.appthitracnghiem.databinding.ActivityHomePageBinding binding;
    private long backPressTime = 0L;
    
    public HomeActivity() {
        super();
    }
    
    @android.annotation.SuppressLint(value = {"ClickableViewAccessibility", "CommitPrefEdits"})
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initUi() {
    }
    
    /**
     * Select Icon
     */
    private final void setSelectIcon() {
    }
    
    /**
     * Reset Select Icon
     */
    private final void resetTab() {
    }
    
    public final void loadingVisible$app_developmentDebug(boolean isLoading) {
    }
    
    public final void clickAvatar$app_developmentDebug() {
    }
    
    private final void attachFragment(int fragmentHolderLayoutId, androidx.fragment.app.Fragment fragment) {
    }
    
    /**
     * Click Back
     */
    @java.lang.Override
    public void onBackPressed() {
    }
}