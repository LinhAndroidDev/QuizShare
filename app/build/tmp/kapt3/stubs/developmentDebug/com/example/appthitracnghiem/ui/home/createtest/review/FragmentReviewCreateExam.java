package com.example.appthitracnghiem.ui.home.createtest.review;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.appthitracnghiem.R;
import com.example.appthitracnghiem.databinding.FragmentReviewCreateExamBinding;
import com.example.appthitracnghiem.model.CreateQuestion;
import com.example.appthitracnghiem.ui.base.BaseFragment;
import com.example.appthitracnghiem.ui.home.createtest.manager.FragmentManageExam;
import com.example.appthitracnghiem.utils.Const;
import com.example.appthitracnghiem.utils.PreferenceKey;
import com.example.appthitracnghiem.utils.UriConvertFile;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import java.io.File;
import java.lang.reflect.Type;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010#\u001a\u00020$H\u0016J&\u0010%\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010&0\nj\n\u0012\u0006\u0012\u0004\u0018\u00010&`\f2\b\u0010\'\u001a\u0004\u0018\u00010(H\u0002J\b\u0010)\u001a\u00020$H\u0003J$\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010/2\b\u00100\u001a\u0004\u0018\u000101H\u0016J\b\u00102\u001a\u00020$H\u0016J\u001a\u00103\u001a\u00020$2\u0006\u00104\u001a\u00020+2\b\u00100\u001a\u0004\u0018\u000101H\u0016J\b\u00105\u001a\u00020$H\u0002J\u0010\u00106\u001a\u00020$2\u0006\u00107\u001a\u00020\u0012H\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00058BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\bR*\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0018X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u00020\u0012X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0014\"\u0004\b\u001f\u0010\u0016R\u001a\u0010 \u001a\u00020\u0012X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0014\"\u0004\b\"\u0010\u0016\u00a8\u00068"}, d2 = {"Lcom/example/appthitracnghiem/ui/home/createtest/review/FragmentReviewCreateExam;", "Lcom/example/appthitracnghiem/ui/base/BaseFragment;", "Lcom/example/appthitracnghiem/ui/home/createtest/review/CreateExamViewModel;", "()V", "_binding", "Lcom/example/appthitracnghiem/databinding/FragmentReviewCreateExamBinding;", "binding", "getBinding", "()Lcom/example/appthitracnghiem/databinding/FragmentReviewCreateExamBinding;", "listTextViewAnswer", "Ljava/util/ArrayList;", "Landroid/widget/TextView;", "Lkotlin/collections/ArrayList;", "getListTextViewAnswer", "()Ljava/util/ArrayList;", "setListTextViewAnswer", "(Ljava/util/ArrayList;)V", "numberQuiz", "", "getNumberQuiz", "()I", "setNumberQuiz", "(I)V", "positionReviewAdapter", "Lcom/example/appthitracnghiem/ui/home/createtest/review/PositionReviewAdapter;", "getPositionReviewAdapter", "()Lcom/example/appthitracnghiem/ui/home/createtest/review/PositionReviewAdapter;", "setPositionReviewAdapter", "(Lcom/example/appthitracnghiem/ui/home/createtest/review/PositionReviewAdapter;)V", "questionIndex", "getQuestionIndex", "setQuestionIndex", "time", "getTime", "setTime", "bindData", "", "getListQuestion", "Lcom/example/appthitracnghiem/model/CreateQuestion;", "key", "", "initUi", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "setText", "setTextExam", "index", "app_developmentDebug"})
public final class FragmentReviewCreateExam extends com.example.appthitracnghiem.ui.base.BaseFragment<com.example.appthitracnghiem.ui.home.createtest.review.CreateExamViewModel> {
    private com.example.appthitracnghiem.databinding.FragmentReviewCreateExamBinding _binding;
    public com.example.appthitracnghiem.ui.home.createtest.review.PositionReviewAdapter positionReviewAdapter;
    private int questionIndex = 0;
    private int numberQuiz = 0;
    @org.jetbrains.annotations.NotNull
    private java.util.ArrayList<android.widget.TextView> listTextViewAnswer;
    private int time = 0;
    
    public FragmentReviewCreateExam() {
        super();
    }
    
    private final com.example.appthitracnghiem.databinding.FragmentReviewCreateExamBinding getBinding() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.appthitracnghiem.ui.home.createtest.review.PositionReviewAdapter getPositionReviewAdapter() {
        return null;
    }
    
    public final void setPositionReviewAdapter(@org.jetbrains.annotations.NotNull
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
    
    @org.jetbrains.annotations.NotNull
    public final java.util.ArrayList<android.widget.TextView> getListTextViewAnswer() {
        return null;
    }
    
    public final void setListTextViewAnswer(@org.jetbrains.annotations.NotNull
    java.util.ArrayList<android.widget.TextView> p0) {
    }
    
    public final int getTime() {
        return 0;
    }
    
    public final void setTime(int p0) {
    }
    
    @java.lang.Override
    public void onViewCreated(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override
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
}