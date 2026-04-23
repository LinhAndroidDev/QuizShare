package com.example.appthitracnghiem.ui.exercise.exercise.answer;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.*;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appthitracnghiem.R;
import com.example.appthitracnghiem.databinding.FragmentAnswerBinding;
import com.example.appthitracnghiem.model.ExamQuestion;
import com.example.appthitracnghiem.model.PositiveQuestion;
import com.example.appthitracnghiem.ui.base.BaseFragment;
import com.example.appthitracnghiem.ui.exercise.exercise.adapter.MenuQuestionAdapter;
import com.example.appthitracnghiem.ui.exercise.exercise.exam.RequestExamQuestion;
import com.example.appthitracnghiem.utils.PreferenceKey;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONObject;
import java.lang.reflect.Type;

@kotlin.Suppress(names = {"DEPRECATION", "CAST_NEVER_SUCCEEDS"})
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u00b8\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 d2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001dB\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010>\u001a\u000201H\u0017J8\u0010?\u001a\u0002012\u0016\u0010@\u001a\u0012\u0012\u0004\u0012\u0002070\u001aj\b\u0012\u0004\u0012\u000207`\u001b2\u0006\u0010A\u001a\u0002072\u0006\u0010B\u001a\u00020\u00052\u0006\u0010C\u001a\u00020\u0005H\u0002J\"\u0010\u001c\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u001aj\b\u0012\u0004\u0012\u00020\u0005`\u001b2\b\u0010D\u001a\u0004\u0018\u00010EH\u0002J\b\u0010F\u001a\u000201H\u0003J$\u0010G\u001a\u00020\u00152\u0006\u0010H\u001a\u00020I2\b\u0010J\u001a\u0004\u0018\u00010K2\b\u0010L\u001a\u0004\u0018\u00010MH\u0016J\b\u0010N\u001a\u000201H\u0016J\b\u0010O\u001a\u00020PH\u0016J\u001a\u0010Q\u001a\u0002012\u0006\u0010R\u001a\u00020\u00152\b\u0010L\u001a\u0004\u0018\u00010MH\u0016J\b\u0010S\u001a\u000201H\u0002J\u0010\u0010T\u001a\u0002012\u0006\u0010U\u001a\u00020\u0005H\u0007J0\u0010V\u001a\u0002012\u0006\u0010R\u001a\u00020\u00152\u0006\u0010W\u001a\u00020\u00052\u0006\u0010X\u001a\u00020\u00052\u0006\u0010Y\u001a\u00020\u00052\u0006\u0010Z\u001a\u00020\u0005H\u0003J\u0012\u0010[\u001a\u0004\u0018\u00010\\2\u0006\u0010]\u001a\u00020\\H\u0002J\u0014\u0010^\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\\0_*\u00020`H\u0002J\u001a\u0010a\u001a\u0010\u0012\u0004\u0012\u00020E\u0012\u0006\u0012\u0004\u0018\u00010\\0b*\u00020cH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\n8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000e8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\b8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0014\u001a\n \u0016*\u0004\u0018\u00010\u00150\u00158BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R*\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u001aj\b\u0012\u0004\u0012\u00020\u0005`\u001bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001e\u0010 \u001a\u0012\u0012\u0004\u0012\u00020!0\u001aj\b\u0012\u0004\u0012\u00020!`\u001bX\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010%\u001a\u00020&8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\'\u0010(R\u000e\u0010)\u001a\u00020*X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010+\u001a\u00020&8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b,\u0010(R\u0014\u0010-\u001a\u00020\u000e8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b.\u0010\u0010R(\u0010/\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u000201\u0018\u000100X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u0014\u00106\u001a\u0002078BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b8\u00109R\u0014\u0010:\u001a\u0002078BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b;\u00109R\u0014\u0010<\u001a\u0002078BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b=\u00109\u00a8\u0006e"}, d2 = {"Lcom/example/appthitracnghiem/ui/exercise/exercise/answer/FragmentAnswer;", "Lcom/example/appthitracnghiem/ui/base/BaseFragment;", "Lcom/example/appthitracnghiem/ui/exercise/exercise/answer/AnswerViewModel;", "()V", "POSITIVE_QUESTION", "", "SIZE_LIST_QUESTION", "_binding", "Lcom/example/appthitracnghiem/databinding/FragmentAnswerBinding;", "backAnswer", "Landroid/widget/ImageView;", "getBackAnswer", "()Landroid/widget/ImageView;", "backQuestionAnswer", "Landroidx/cardview/widget/CardView;", "getBackQuestionAnswer", "()Landroidx/cardview/widget/CardView;", "binding", "getBinding", "()Lcom/example/appthitracnghiem/databinding/FragmentAnswerBinding;", "layoutLoading", "Landroid/view/View;", "kotlin.jvm.PlatformType", "getLayoutLoading", "()Landroid/view/View;", "listAnswer", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getListAnswer", "()Ljava/util/ArrayList;", "setListAnswer", "(Ljava/util/ArrayList;)V", "listExamQuestion", "Lcom/example/appthitracnghiem/model/ExamQuestion;", "listQuestion", "", "Lcom/example/appthitracnghiem/model/PositiveQuestion;", "llContainerOptions", "Landroid/widget/LinearLayout;", "getLlContainerOptions", "()Landroid/widget/LinearLayout;", "menuQuestionAdapter", "Lcom/example/appthitracnghiem/ui/exercise/exercise/adapter/MenuQuestionAdapter;", "menuQuestionAnswer", "getMenuQuestionAnswer", "nextQuestionAnswer", "getNextQuestionAnswer", "onClickNextQuestion", "Lkotlin/Function1;", "", "getOnClickNextQuestion", "()Lkotlin/jvm/functions/Function1;", "setOnClickNextQuestion", "(Lkotlin/jvm/functions/Function1;)V", "titleAnswer", "Landroid/widget/TextView;", "getTitleAnswer", "()Landroid/widget/TextView;", "txtPositionQuizAnswer", "getTxtPositionQuizAnswer", "txtTiltleAnswer", "getTxtTiltleAnswer", "bindData", "createTextAnswer", "arrayTxt", "txt", "position", "i", "key", "", "initUi", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onFragmentBack", "", "onViewCreated", "view", "setStatusBar", "setTextView", "psQuestion", "showMenuQuestion", "popupViewId", "x", "y", "gravity", "toValue", "", "element", "toList", "", "Lorg/json/JSONArray;", "toMap", "", "Lorg/json/JSONObject;", "Companion", "app_developmentDebug"})
public final class FragmentAnswer extends com.example.appthitracnghiem.ui.base.BaseFragment<com.example.appthitracnghiem.ui.exercise.exercise.answer.AnswerViewModel> {
    private com.example.appthitracnghiem.databinding.FragmentAnswerBinding _binding;
    private java.util.List<com.example.appthitracnghiem.model.PositiveQuestion> listQuestion;
    private com.example.appthitracnghiem.ui.exercise.exercise.adapter.MenuQuestionAdapter menuQuestionAdapter;
    private int POSITIVE_QUESTION = 0;
    private int SIZE_LIST_QUESTION = 0;
    private java.util.ArrayList<com.example.appthitracnghiem.model.ExamQuestion> listExamQuestion;
    @org.jetbrains.annotations.NotNull
    private java.util.ArrayList<java.lang.Integer> listAnswer;
    @org.jetbrains.annotations.Nullable
    private kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onClickNextQuestion;
    @org.jetbrains.annotations.NotNull
    public static final com.example.appthitracnghiem.ui.exercise.exercise.answer.FragmentAnswer.Companion Companion = null;
    @org.jetbrains.annotations.NotNull
    private static java.util.ArrayList<android.widget.TextView> arrayTxtQuestion;
    @org.jetbrains.annotations.NotNull
    private static java.util.ArrayList<java.lang.Integer> listResult;
    
    public FragmentAnswer() {
        super();
    }
    
    private final com.example.appthitracnghiem.databinding.FragmentAnswerBinding getBinding() {
        return null;
    }
    
    private final android.widget.TextView getTxtTiltleAnswer() {
        return null;
    }
    
    private final android.widget.ImageView getBackAnswer() {
        return null;
    }
    
    private final android.widget.LinearLayout getMenuQuestionAnswer() {
        return null;
    }
    
    private final android.widget.TextView getTxtPositionQuizAnswer() {
        return null;
    }
    
    private final android.widget.TextView getTitleAnswer() {
        return null;
    }
    
    private final android.widget.LinearLayout getLlContainerOptions() {
        return null;
    }
    
    private final androidx.cardview.widget.CardView getBackQuestionAnswer() {
        return null;
    }
    
    private final androidx.cardview.widget.CardView getNextQuestionAnswer() {
        return null;
    }
    
    private final android.view.View getLayoutLoading() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.ArrayList<java.lang.Integer> getListAnswer() {
        return null;
    }
    
    public final void setListAnswer(@org.jetbrains.annotations.NotNull
    java.util.ArrayList<java.lang.Integer> p0) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.Unit> getOnClickNextQuestion() {
        return null;
    }
    
    public final void setOnClickNextQuestion(@org.jetbrains.annotations.Nullable
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> p0) {
    }
    
    @java.lang.Override
    public void onViewCreated(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    @java.lang.Override
    public void bindData() {
    }
    
    private final java.util.Map<java.lang.String, java.lang.Object> toMap(org.json.JSONObject $this$toMap) {
        return null;
    }
    
    private final java.util.List<java.lang.Object> toList(org.json.JSONArray $this$toList) {
        return null;
    }
    
    private final java.lang.Object toValue(java.lang.Object element) {
        return null;
    }
    
    private final void setStatusBar() {
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    private final void initUi() {
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
    
    @kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR*\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\u0004j\b\u0012\u0004\u0012\u00020\u000b`\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0007\"\u0004\b\u000e\u0010\t\u00a8\u0006\u000f"}, d2 = {"Lcom/example/appthitracnghiem/ui/exercise/exercise/answer/FragmentAnswer$Companion;", "", "()V", "arrayTxtQuestion", "Ljava/util/ArrayList;", "Landroid/widget/TextView;", "getArrayTxtQuestion", "()Ljava/util/ArrayList;", "setArrayTxtQuestion", "(Ljava/util/ArrayList;)V", "listResult", "", "Lkotlin/collections/ArrayList;", "getListResult", "setListResult", "app_developmentDebug"})
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
        public final java.util.ArrayList<java.lang.Integer> getListResult() {
            return null;
        }
        
        public final void setListResult(@org.jetbrains.annotations.NotNull
        java.util.ArrayList<java.lang.Integer> p0) {
        }
    }
}