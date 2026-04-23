package com.example.appthitracnghiem.ui.intro;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.example.appthitracnghiem.R;
import com.example.appthitracnghiem.connectivity.CheckConnect;
import com.example.appthitracnghiem.databinding.ActivityIntroBinding;
import com.example.appthitracnghiem.ui.EmptyViewModel;
import com.example.appthitracnghiem.ui.base.BaseActivity;
import com.example.appthitracnghiem.ui.intro.adapter.ViewPagerAdapter;

@kotlin.Suppress(names = {"DEPRECATION"})
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\b\u001a\u00020\tH\u0002J\b\u0010\n\u001a\u00020\tH\u0002J\u0012\u0010\u000b\u001a\u00020\t2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014J\b\u0010\u000e\u001a\u00020\tH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/example/appthitracnghiem/ui/intro/IntroActivity;", "Lcom/example/appthitracnghiem/ui/base/BaseActivity;", "Lcom/example/appthitracnghiem/ui/EmptyViewModel;", "()V", "binding", "Lcom/example/appthitracnghiem/databinding/ActivityIntroBinding;", "viewPagerAdapter", "Lcom/example/appthitracnghiem/ui/intro/adapter/ViewPagerAdapter;", "hideButton", "", "initUi", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setStatusBar", "app_developmentDebug"})
public final class IntroActivity extends com.example.appthitracnghiem.ui.base.BaseActivity<com.example.appthitracnghiem.ui.EmptyViewModel> {
    private com.example.appthitracnghiem.ui.intro.adapter.ViewPagerAdapter viewPagerAdapter;
    private com.example.appthitracnghiem.databinding.ActivityIntroBinding binding;
    
    public IntroActivity() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setStatusBar() {
    }
    
    /**
     * Next ViewPager
     */
    private final void initUi() {
    }
    
    private final void hideButton() {
    }
}