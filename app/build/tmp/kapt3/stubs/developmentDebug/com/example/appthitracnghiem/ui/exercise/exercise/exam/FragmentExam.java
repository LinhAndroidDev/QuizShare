package com.example.appthitracnghiem.ui.exercise.exercise.exam;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.view.*;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appthitracnghiem.R;
import com.example.appthitracnghiem.databinding.FragmentExamBinding;
import com.example.appthitracnghiem.model.ExamQuestion;
import com.example.appthitracnghiem.model.PositiveQuestion;
import com.example.appthitracnghiem.ui.base.BaseFragment;
import com.example.appthitracnghiem.ui.exercise.exercise.point.FragmentPoint;
import com.example.appthitracnghiem.ui.exercise.exercise.adapter.MenuQuestionAdapter;
import com.example.appthitracnghiem.utils.PreferenceKey;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

@kotlin.Suppress(names = {"DEPRECATION", "NAME_SHADOWING"})
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u00a2\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\u0018\u0000 o2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001oB\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010M\u001a\u00020>H\u0017J8\u0010N\u001a\u00020>2\u0016\u0010O\u001a\u0012\u0012\u0004\u0012\u00020F0/j\b\u0012\u0004\u0012\u00020F`02\u0006\u0010P\u001a\u00020F2\u0006\u0010Q\u001a\u00020\u00052\u0006\u0010R\u001a\u00020\u0005H\u0002J\"\u0010S\u001a\u0012\u0012\u0004\u0012\u00020\u00050/j\b\u0012\u0004\u0012\u00020\u0005`02\b\u0010T\u001a\u0004\u0018\u00010UH\u0002J\b\u0010V\u001a\u00020>H\u0003J$\u0010W\u001a\u00020&2\u0006\u0010X\u001a\u00020Y2\b\u0010Z\u001a\u0004\u0018\u00010[2\b\u0010\\\u001a\u0004\u0018\u00010]H\u0016J\b\u0010^\u001a\u00020>H\u0016J\b\u0010_\u001a\u00020=H\u0016J\u001a\u0010`\u001a\u00020>2\u0006\u0010a\u001a\u00020&2\b\u0010\\\u001a\u0004\u0018\u00010]H\u0017J*\u0010b\u001a\u00020>2\u0016\u0010c\u001a\u0012\u0012\u0004\u0012\u00020\u00050/j\b\u0012\u0004\u0012\u00020\u0005`02\b\u0010T\u001a\u0004\u0018\u00010UH\u0002J\b\u0010d\u001a\u00020>H\u0002J\u0010\u0010e\u001a\u00020>2\u0006\u0010f\u001a\u00020\u0005H\u0007J\u0010\u0010g\u001a\u00020>2\u0006\u0010h\u001a\u00020\u0005H\u0002J\b\u0010i\u001a\u00020>H\u0002J0\u0010j\u001a\u00020>2\u0006\u0010a\u001a\u00020&2\u0006\u0010k\u001a\u00020\u00052\u0006\u0010l\u001a\u00020\u00052\u0006\u0010m\u001a\u00020\u00052\u0006\u0010n\u001a\u00020\u0005H\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\r8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00118BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u00118BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u0013R\u0014\u0010\u0016\u001a\u00020\u000b8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\u00020\u001c8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\u00020\r8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b \u0010\u000fR\u0014\u0010!\u001a\u00020\"8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b#\u0010$R\u001c\u0010%\u001a\n \'*\u0004\u0018\u00010&0&8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b(\u0010)R\u0014\u0010*\u001a\u00020+8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b,\u0010-R\u001e\u0010.\u001a\u0012\u0012\u0004\u0012\u00020\u00050/j\b\u0012\u0004\u0012\u00020\u0005`0X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u00101\u001a\u0012\u0012\u0004\u0012\u0002020/j\b\u0012\u0004\u0012\u000202`0X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u00103\u001a\u00020\"8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b4\u0010$R\u0014\u00105\u001a\u00020\"8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b6\u0010$R\u000e\u00107\u001a\u000208X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u00109\u001a\u00020\u00118BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b:\u0010\u0013R(\u0010;\u001a\u0010\u0012\u0004\u0012\u00020=\u0012\u0004\u0012\u00020>\u0018\u00010<X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR\u0014\u0010C\u001a\u00020\u00118BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\bD\u0010\u0013R\u0014\u0010E\u001a\u00020F8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\bG\u0010HR\u0014\u0010I\u001a\u00020F8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\bJ\u0010HR\u0014\u0010K\u001a\u00020F8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\bL\u0010H\u00a8\u0006p"}, d2 = {"Lcom/example/appthitracnghiem/ui/exercise/exercise/exam/FragmentExam;", "Lcom/example/appthitracnghiem/ui/base/BaseFragment;", "Lcom/example/appthitracnghiem/ui/exercise/exercise/exam/ExamViewModel;", "()V", "MINUTES", "", "POSITIVE_QUESTION", "SECONDS", "SIZE_LIST_QUESTION", "TIME_TOTAL", "_binding", "Lcom/example/appthitracnghiem/databinding/FragmentExamBinding;", "backExercise", "Landroid/widget/ImageView;", "getBackExercise", "()Landroid/widget/ImageView;", "backQuestion", "Landroidx/cardview/widget/CardView;", "getBackQuestion", "()Landroidx/cardview/widget/CardView;", "backSubmit", "getBackSubmit", "binding", "getBinding", "()Lcom/example/appthitracnghiem/databinding/FragmentExamBinding;", "countDownTimer", "Landroid/os/CountDownTimer;", "countTime", "Lcom/skydoves/progressview/ProgressView;", "getCountTime", "()Lcom/skydoves/progressview/ProgressView;", "finishQuiz", "getFinishQuiz", "layoutExercise", "Landroid/widget/LinearLayout;", "getLayoutExercise", "()Landroid/widget/LinearLayout;", "layoutLoading", "Landroid/view/View;", "kotlin.jvm.PlatformType", "getLayoutLoading", "()Landroid/view/View;", "layoutSubmit", "Landroid/widget/RelativeLayout;", "getLayoutSubmit", "()Landroid/widget/RelativeLayout;", "listAnswer", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "listExamQuestion", "Lcom/example/appthitracnghiem/model/ExamQuestion;", "llContainerAnswerOptions", "getLlContainerAnswerOptions", "menuQuestion", "getMenuQuestion", "menuQuestionAdapter", "Lcom/example/appthitracnghiem/ui/exercise/exercise/adapter/MenuQuestionAdapter;", "nextQuestion", "getNextQuestion", "onClickChangeQuestion", "Lkotlin/Function1;", "", "", "getOnClickChangeQuestion", "()Lkotlin/jvm/functions/Function1;", "setOnClickChangeQuestion", "(Lkotlin/jvm/functions/Function1;)V", "submit", "getSubmit", "titleExam", "Landroid/widget/TextView;", "getTitleExam", "()Landroid/widget/TextView;", "txtPositionQuiz", "getTxtPositionQuiz", "txtTime", "getTxtTime", "bindData", "createTextAnswer", "arrayTxt", "txt", "position", "i", "getListAnswer", "key", "", "initUi", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onFragmentBack", "onViewCreated", "view", "saveListAnswer", "list", "setStatusBar", "setTextView", "psQuestion", "setTime", "time", "showLayoutSubmit", "showMenuQuestion", "popupViewId", "x", "y", "gravity", "Companion", "app_developmentDebug"})
public final class FragmentExam extends com.example.appthitracnghiem.ui.base.BaseFragment<com.example.appthitracnghiem.ui.exercise.exercise.exam.ExamViewModel> {
    private com.example.appthitracnghiem.databinding.FragmentExamBinding _binding;
    private com.example.appthitracnghiem.ui.exercise.exercise.adapter.MenuQuestionAdapter menuQuestionAdapter;
    private android.os.CountDownTimer countDownTimer;
    private int TIME_TOTAL = 0;
    private int MINUTES = 0;
    private int SECONDS = 0;
    private int POSITIVE_QUESTION = 0;
    private int SIZE_LIST_QUESTION = 0;
    private java.util.ArrayList<com.example.appthitracnghiem.model.ExamQuestion> listExamQuestion;
    private java.util.ArrayList<java.lang.Integer> listAnswer;
    @org.jetbrains.annotations.Nullable
    private kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> onClickChangeQuestion;
    @org.jetbrains.annotations.NotNull
    public static final com.example.appthitracnghiem.ui.exercise.exercise.exam.FragmentExam.Companion Companion = null;
    @org.jetbrains.annotations.NotNull
    private static java.util.ArrayList<android.widget.TextView> arrayTxtQuestion;
    @org.jetbrains.annotations.NotNull
    private static java.util.List<com.example.appthitracnghiem.model.PositiveQuestion> listQuestion;
    @org.jetbrains.annotations.NotNull
    private static java.util.ArrayList<java.lang.Integer> listResult;
    
    public FragmentExam() {
        super();
    }
    
    private final com.example.appthitracnghiem.databinding.FragmentExamBinding getBinding() {
        return null;
    }
    
    private final android.widget.LinearLayout getLayoutExercise() {
        return null;
    }
    
    private final android.widget.ImageView getBackExercise() {
        return null;
    }
    
    private final android.widget.TextView getTxtTime() {
        return null;
    }
    
    private final com.skydoves.progressview.ProgressView getCountTime() {
        return null;
    }
    
    private final android.widget.ImageView getFinishQuiz() {
        return null;
    }
    
    private final android.widget.LinearLayout getMenuQuestion() {
        return null;
    }
    
    private final android.widget.TextView getTxtPositionQuiz() {
        return null;
    }
    
    private final android.widget.TextView getTitleExam() {
        return null;
    }
    
    private final android.widget.LinearLayout getLlContainerAnswerOptions() {
        return null;
    }
    
    private final androidx.cardview.widget.CardView getBackQuestion() {
        return null;
    }
    
    private final androidx.cardview.widget.CardView getNextQuestion() {
        return null;
    }
    
    private final android.widget.RelativeLayout getLayoutSubmit() {
        return null;
    }
    
    private final androidx.cardview.widget.CardView getBackSubmit() {
        return null;
    }
    
    private final androidx.cardview.widget.CardView getSubmit() {
        return null;
    }
    
    private final android.view.View getLayoutLoading() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final kotlin.jvm.functions.Function1<java.lang.Boolean, kotlin.Unit> getOnClickChangeQuestion() {
        return null;
    }
    
    public final void setOnClickChangeQuestion(@org.jetbrains.annotations.Nullable
    kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> p0) {
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n", "ResourceAsColor"})
    @java.lang.Override
    public void onViewCreated(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    @java.lang.Override
    public void bindData() {
    }
    
    private final void setTime(int time) {
    }
    
    private final void setStatusBar() {
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n", "NotifyDataSetChanged"})
    private final void initUi() {
    }
    
    private final void showLayoutSubmit() {
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    private final void showMenuQuestion(android.view.View view, int popupViewId, int x, int y, int gravity) {
    }
    
    @android.annotation.SuppressLint(value = {"ResourceAsColor"})
    public final void setTextView(int psQuestion) {
    }
    
    /**
     * Create Text Answer
     */
    private final void createTextAnswer(java.util.ArrayList<android.widget.TextView> arrayTxt, android.widget.TextView txt, int position, int i) {
    }
    
    private final void saveListAnswer(java.util.ArrayList<java.lang.Integer> list, java.lang.String key) {
    }
    
    private final java.util.ArrayList<java.lang.Integer> getListAnswer(java.lang.String key) {
        return null;
    }
    
    @java.lang.Override
    public boolean onFragmentBack() {
        return false;
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
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR \u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R*\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u00120\u0004j\b\u0012\u0004\u0012\u00020\u0012`\u0013X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0007\"\u0004\b\u0015\u0010\t\u00a8\u0006\u0016"}, d2 = {"Lcom/example/appthitracnghiem/ui/exercise/exercise/exam/FragmentExam$Companion;", "", "()V", "arrayTxtQuestion", "Ljava/util/ArrayList;", "Landroid/widget/TextView;", "getArrayTxtQuestion", "()Ljava/util/ArrayList;", "setArrayTxtQuestion", "(Ljava/util/ArrayList;)V", "listQuestion", "", "Lcom/example/appthitracnghiem/model/PositiveQuestion;", "getListQuestion", "()Ljava/util/List;", "setListQuestion", "(Ljava/util/List;)V", "listResult", "", "Lkotlin/collections/ArrayList;", "getListResult", "setListResult", "app_developmentDebug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.util.ArrayList<android.widget.TextView> getArrayTxtQuestion() {
            return null;
        }
        
        public final void setArrayTxtQuestion(@org.jetbrains.annotations.NotNull
        java.util.ArrayList<android.widget.TextView> p0) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.util.List<com.example.appthitracnghiem.model.PositiveQuestion> getListQuestion() {
            return null;
        }
        
        public final void setListQuestion(@org.jetbrains.annotations.NotNull
        java.util.List<com.example.appthitracnghiem.model.PositiveQuestion> p0) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.util.ArrayList<java.lang.Integer> getListResult() {
            return null;
        }
        
        public final void setListResult(@org.jetbrains.annotations.NotNull
        java.util.ArrayList<java.lang.Integer> p0) {
        }
    }
}