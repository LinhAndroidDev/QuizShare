package com.example.appthitracnghiem.ui.home.createtest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appthitracnghiem.R;
import com.example.appthitracnghiem.databinding.FragmentCreateTestBinding;
import com.example.appthitracnghiem.ui.EmptyViewModel;
import com.example.appthitracnghiem.ui.base.BaseFragment;
import com.example.appthitracnghiem.ui.department.listdepartment.ListDepartmentViewModel;
import com.example.appthitracnghiem.ui.department.listdepartment.RequestDepartmentInfo;
import com.example.appthitracnghiem.ui.home.createtest.adapter.CreateDepartmentAdapter;
import com.example.appthitracnghiem.ui.home.createtest.question.CreateTestActivity;
import com.example.appthitracnghiem.utils.PreferenceKey;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

@kotlin.Suppress(names = {"DEPRECATION"})
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000f\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\\\u001a\u00020]H\u0016J\b\u0010^\u001a\u00020]H\u0002J\b\u0010_\u001a\u00020]H\u0003J\"\u0010`\u001a\u00020]2\u0006\u0010a\u001a\u00020\u00052\u0006\u0010b\u001a\u00020\u00052\b\u0010c\u001a\u0004\u0018\u00010dH\u0017J$\u0010e\u001a\u00020f2\u0006\u0010g\u001a\u00020h2\b\u0010i\u001a\u0004\u0018\u00010j2\b\u0010k\u001a\u0004\u0018\u00010lH\u0016J\b\u0010m\u001a\u00020]H\u0016J\b\u0010n\u001a\u00020oH\u0016J\u001a\u0010p\u001a\u00020]2\u0006\u0010q\u001a\u00020f2\b\u0010k\u001a\u0004\u0018\u00010lH\u0016J\r\u0010r\u001a\u00020]H\u0000\u00a2\u0006\u0002\bsJ\b\u0010t\u001a\u00020]H\u0003J\b\u0010u\u001a\u00020]H\u0003J\b\u0010v\u001a\u00020]H\u0002J0\u0010w\u001a\u00020]2\u0006\u0010x\u001a\u00020f2\u0006\u0010y\u001a\u00020f2\u0006\u0010z\u001a\u00020\u00052\u0006\u0010{\u001a\u00020\u00052\u0006\u0010|\u001a\u00020\u0005H\u0002J\f\u0010}\u001a\u00020]*\u00020fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000b8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\t8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u00128BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u00168BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u001a8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\u00020\u001a8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001e\u0010\u001cR\u0014\u0010\u001f\u001a\u00020\u00168BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b \u0010\u0018R\u0014\u0010!\u001a\u00020\u001a8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\"\u0010\u001cR\u0014\u0010#\u001a\u00020\u00168BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b$\u0010\u0018R\u0014\u0010%\u001a\u00020\u00168BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b&\u0010\u0018R\u0014\u0010\'\u001a\u00020(8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b)\u0010*R\u0014\u0010+\u001a\u00020\u000b8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b,\u0010\rR\u0014\u0010-\u001a\u00020(8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b.\u0010*R\u0014\u0010/\u001a\u00020(8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b0\u0010*R\u0014\u00101\u001a\u0002028BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b3\u00104R\u0014\u00105\u001a\u00020\u000b8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b6\u0010\rR\u0014\u00107\u001a\u0002088BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b9\u0010:R\u0014\u0010;\u001a\u00020\u000b8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b<\u0010\rR\u0014\u0010=\u001a\u00020\u000b8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b>\u0010\rR\u0014\u0010?\u001a\u00020\u000b8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b@\u0010\rR\u0014\u0010A\u001a\u00020\u000b8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\bB\u0010\rR\u001c\u0010C\u001a\n D*\u0004\u0018\u00010(0(8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\bE\u0010*R\u001c\u0010F\u001a\n D*\u0004\u0018\u00010(0(8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\bG\u0010*R\u0014\u0010H\u001a\u00020\u001a8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\bI\u0010\u001cR\u0014\u0010J\u001a\u00020\u001a8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\bK\u0010\u001cR\u0014\u0010L\u001a\u00020\u001a8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\bM\u0010\u001cR\u0014\u0010N\u001a\u00020\u001a8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\bO\u0010\u001cR\u0014\u0010P\u001a\u00020\u001a8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\bQ\u0010\u001cR\u0014\u0010R\u001a\u00020\u001a8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\bS\u0010\u001cR\u0014\u0010T\u001a\u00020\u001a8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\bU\u0010\u001cR\u0014\u0010V\u001a\u00020\u001a8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\bW\u0010\u001cR\u0014\u0010X\u001a\u00020\u001a8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\bY\u0010\u001cR\u000e\u0010Z\u001a\u00020[X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006~"}, d2 = {"Lcom/example/appthitracnghiem/ui/home/createtest/FragmentCreateTest;", "Lcom/example/appthitracnghiem/ui/base/BaseFragment;", "Lcom/example/appthitracnghiem/ui/department/listdepartment/ListDepartmentViewModel;", "()V", "DEPARTMENT_ID", "", "GALLERY_RED_CODE", "SUBJECT_ID", "_binding", "Lcom/example/appthitracnghiem/databinding/FragmentCreateTestBinding;", "addCoverImage", "Landroid/widget/ImageView;", "getAddCoverImage", "()Landroid/widget/ImageView;", "binding", "getBinding", "()Lcom/example/appthitracnghiem/databinding/FragmentCreateTestBinding;", "createTest", "Landroidx/cardview/widget/CardView;", "getCreateTest", "()Landroidx/cardview/widget/CardView;", "edtDescribeQuiz", "Landroid/widget/EditText;", "getEdtDescribeQuiz", "()Landroid/widget/EditText;", "edtSelectDepartment", "Landroid/widget/TextView;", "getEdtSelectDepartment", "()Landroid/widget/TextView;", "edtSelectLevel", "getEdtSelectLevel", "edtSelectNumberQuiz", "getEdtSelectNumberQuiz", "edtSelectSubject", "getEdtSelectSubject", "edtSelectTime", "getEdtSelectTime", "edtSelectTitle", "getEdtSelectTitle", "googleSheet", "Landroid/widget/LinearLayout;", "getGoogleSheet", "()Landroid/widget/LinearLayout;", "imageCover", "getImageCover", "layoutCreateTest", "getLayoutCreateTest", "layoutCreateTestCover", "getLayoutCreateTestCover", "layoutSelectSubject", "Landroid/widget/RelativeLayout;", "getLayoutSelectSubject", "()Landroid/widget/RelativeLayout;", "menuCreateTest", "getMenuCreateTest", "scrollCreateTest", "Landroidx/core/widget/NestedScrollView;", "getScrollCreateTest", "()Landroidx/core/widget/NestedScrollView;", "selectDepartment", "getSelectDepartment", "selectMode", "getSelectMode", "selectSubject", "getSelectSubject", "shareTest", "getShareTest", "shareWithFacebook", "kotlin.jvm.PlatformType", "getShareWithFacebook", "shareWithMail", "getShareWithMail", "txtAddTest", "getTxtAddTest", "txtDetail", "getTxtDetail", "txtDownTest", "getTxtDownTest", "txtNumberQuestion", "getTxtNumberQuestion", "txtSelectDepartment", "getTxtSelectDepartment", "txtSelectMode", "getTxtSelectMode", "txtSelectSubject", "getTxtSelectSubject", "txtTiltle", "getTxtTiltle", "txtTimeDoTest", "getTxtTimeDoTest", "uriImage", "", "bindData", "", "clearFocusTextView", "initUi", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onFragmentBack", "", "onViewCreated", "view", "scrollTop", "scrollTop$app_developmentDebug", "setBottomShare", "setStatusBar", "setText", "showMenuCreate", "popView", "anchor", "x", "y", "position", "hideKeyboard", "app_developmentDebug"})
public final class FragmentCreateTest extends com.example.appthitracnghiem.ui.base.BaseFragment<com.example.appthitracnghiem.ui.department.listdepartment.ListDepartmentViewModel> {
    private com.example.appthitracnghiem.databinding.FragmentCreateTestBinding _binding;
    private final int GALLERY_RED_CODE = 1000;
    private int DEPARTMENT_ID = -1;
    private int SUBJECT_ID = -1;
    private java.lang.String uriImage = "";
    
    public FragmentCreateTest() {
        super();
    }
    
    private final com.example.appthitracnghiem.databinding.FragmentCreateTestBinding getBinding() {
        return null;
    }
    
    private final androidx.core.widget.NestedScrollView getScrollCreateTest() {
        return null;
    }
    
    private final android.widget.LinearLayout getLayoutCreateTest() {
        return null;
    }
    
    private final android.widget.LinearLayout getLayoutCreateTestCover() {
        return null;
    }
    
    private final android.widget.ImageView getMenuCreateTest() {
        return null;
    }
    
    private final android.widget.ImageView getSelectDepartment() {
        return null;
    }
    
    private final android.widget.TextView getTxtSelectSubject() {
        return null;
    }
    
    private final android.widget.RelativeLayout getLayoutSelectSubject() {
        return null;
    }
    
    private final android.widget.ImageView getSelectSubject() {
        return null;
    }
    
    private final android.widget.ImageView getSelectMode() {
        return null;
    }
    
    private final android.widget.ImageView getAddCoverImage() {
        return null;
    }
    
    private final android.widget.LinearLayout getGoogleSheet() {
        return null;
    }
    
    private final androidx.cardview.widget.CardView getCreateTest() {
        return null;
    }
    
    private final android.widget.LinearLayout getShareWithFacebook() {
        return null;
    }
    
    private final android.widget.LinearLayout getShareWithMail() {
        return null;
    }
    
    private final android.widget.ImageView getShareTest() {
        return null;
    }
    
    private final android.widget.TextView getEdtSelectDepartment() {
        return null;
    }
    
    private final android.widget.TextView getEdtSelectSubject() {
        return null;
    }
    
    private final android.widget.TextView getEdtSelectLevel() {
        return null;
    }
    
    private final android.widget.EditText getEdtSelectTitle() {
        return null;
    }
    
    private final android.widget.EditText getEdtSelectTime() {
        return null;
    }
    
    private final android.widget.EditText getEdtSelectNumberQuiz() {
        return null;
    }
    
    private final android.widget.EditText getEdtDescribeQuiz() {
        return null;
    }
    
    private final android.widget.ImageView getImageCover() {
        return null;
    }
    
    private final android.widget.TextView getTxtAddTest() {
        return null;
    }
    
    private final android.widget.TextView getTxtTiltle() {
        return null;
    }
    
    private final android.widget.TextView getTxtDownTest() {
        return null;
    }
    
    private final android.widget.TextView getTxtSelectDepartment() {
        return null;
    }
    
    private final android.widget.TextView getTxtSelectMode() {
        return null;
    }
    
    private final android.widget.TextView getTxtTimeDoTest() {
        return null;
    }
    
    private final android.widget.TextView getTxtNumberQuestion() {
        return null;
    }
    
    private final android.widget.TextView getTxtDetail() {
        return null;
    }
    
    @java.lang.Override
    public void onViewCreated(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override
    public void bindData() {
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.M)
    private final void setStatusBar() {
    }
    
    private final void hideKeyboard(android.view.View $this$hideKeyboard) {
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.M)
    @android.annotation.SuppressLint(value = {"ClickableViewAccessibility"})
    private final void initUi() {
    }
    
    /**
     * Share Test
     */
    @android.annotation.SuppressLint(value = {"ClickableViewAccessibility"})
    private final void setBottomShare() {
    }
    
    /**
     * Get Image from Storage
     */
    @java.lang.Override
    @java.lang.Deprecated
    public void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable
    android.content.Intent data) {
    }
    
    /**
     * show menu add test
     */
    private final void showMenuCreate(android.view.View popView, android.view.View anchor, int x, int y, int position) {
    }
    
    /**
     * set font
     */
    private final void setText() {
    }
    
    public final void scrollTop$app_developmentDebug() {
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
    
    @java.lang.Override
    public boolean onFragmentBack() {
        return false;
    }
    
    private final void clearFocusTextView() {
    }
}