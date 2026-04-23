package com.example.appthitracnghiem.ui.home.createtest.question;

import android.content.Intent;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.appthitracnghiem.R;
import com.example.appthitracnghiem.databinding.ActivityCreateTestBinding;
import com.example.appthitracnghiem.model.PositiveQuestion;
import com.example.appthitracnghiem.ui.EmptyViewModel;
import com.example.appthitracnghiem.ui.base.BaseActivity;
import com.example.appthitracnghiem.ui.base.BaseFragment;
import com.example.appthitracnghiem.ui.home.createtest.question.adapter.PositiveQuestionAdapter;
import com.example.appthitracnghiem.utils.PreferenceKey;

@kotlin.Suppress(names = {"DEPRECATION"})
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0012\u0010\b\u001a\u00020\u00072\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0014J\u0010\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\rH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/example/appthitracnghiem/ui/home/createtest/question/CreateTestActivity;", "Lcom/example/appthitracnghiem/ui/base/BaseActivity;", "Lcom/example/appthitracnghiem/ui/EmptyViewModel;", "()V", "binding", "Lcom/example/appthitracnghiem/databinding/ActivityCreateTestBinding;", "onBackPressed", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "replaceFragment", "fg", "Landroidx/fragment/app/Fragment;", "app_developmentDebug"})
public final class CreateTestActivity extends com.example.appthitracnghiem.ui.base.BaseActivity<com.example.appthitracnghiem.ui.EmptyViewModel> {
    private com.example.appthitracnghiem.databinding.ActivityCreateTestBinding binding;
    
    public CreateTestActivity() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void replaceFragment(androidx.fragment.app.Fragment fg) {
    }
    
    @java.lang.Override
    public void onBackPressed() {
    }
}