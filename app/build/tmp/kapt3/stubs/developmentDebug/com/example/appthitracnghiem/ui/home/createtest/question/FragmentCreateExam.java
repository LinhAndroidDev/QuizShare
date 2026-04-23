package com.example.appthitracnghiem.ui.home.createtest.question;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.appthitracnghiem.R;
import com.example.appthitracnghiem.databinding.FragmentCreateExamBinding;
import com.example.appthitracnghiem.model.CreateAnswer;
import com.example.appthitracnghiem.model.CreateQuestion;
import com.example.appthitracnghiem.ui.EmptyViewModel;
import com.example.appthitracnghiem.ui.base.BaseFragment;
import com.example.appthitracnghiem.ui.home.createtest.question.adapter.PositiveQuestionAdapter;
import com.example.appthitracnghiem.ui.home.createtest.review.FragmentReviewCreateExam;
import com.example.appthitracnghiem.utils.PreferenceKey;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

@kotlin.Suppress(names = {"DEPRECATION"})
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u00b8\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010g\u001a\u00020hH\u0002J\b\u0010i\u001a\u00020hH\u0002J\"\u0010j\u001a\u0012\u0012\u0004\u0012\u00020\u000505j\b\u0012\u0004\u0012\u00020\u0005`62\b\u0010k\u001a\u0004\u0018\u00010lH\u0002J&\u0010m\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010<05j\n\u0012\u0006\u0012\u0004\u0018\u00010<`62\b\u0010k\u001a\u0004\u0018\u00010lH\u0002J\b\u0010n\u001a\u00020hH\u0003J\"\u0010o\u001a\u00020h2\u0006\u0010p\u001a\u00020\u00052\u0006\u0010q\u001a\u00020\u00052\b\u0010r\u001a\u0004\u0018\u00010sH\u0016J$\u0010t\u001a\u00020u2\u0006\u0010v\u001a\u00020w2\b\u0010x\u001a\u0004\u0018\u00010y2\b\u0010z\u001a\u0004\u0018\u00010{H\u0016J\b\u0010|\u001a\u00020hH\u0016J\b\u0010}\u001a\u00020 H\u0016J\u001a\u0010~\u001a\u00020h2\u0006\u0010\u007f\u001a\u00020u2\b\u0010z\u001a\u0004\u0018\u00010{H\u0017J\t\u0010\u0080\u0001\u001a\u00020hH\u0002J,\u0010\u0081\u0001\u001a\u00020h2\u0017\u0010\u0082\u0001\u001a\u0012\u0012\u0004\u0012\u00020\u000505j\b\u0012\u0004\u0012\u00020\u0005`62\b\u0010k\u001a\u0004\u0018\u00010lH\u0002J0\u0010\u0083\u0001\u001a\u00020h2\u001b\u0010\u0082\u0001\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010<05j\n\u0012\u0006\u0012\u0004\u0018\u00010<`62\b\u0010k\u001a\u0004\u0018\u00010lH\u0002J\t\u0010\u0084\u0001\u001a\u00020hH\u0002J\t\u0010\u0085\u0001\u001a\u00020hH\u0002J\t\u0010\u0086\u0001\u001a\u00020hH\u0002J\t\u0010\u0087\u0001\u001a\u00020hH\u0002J6\u0010\u0088\u0001\u001a\u00020h2\u0007\u0010\u0089\u0001\u001a\u00020u2\u0007\u0010\u008a\u0001\u001a\u00020\u00052\u0007\u0010\u008b\u0001\u001a\u00020\u00052\u0007\u0010\u008c\u0001\u001a\u00020\u00052\u0007\u0010\u008d\u0001\u001a\u00020\u0005H\u0002J-\u0010\u008e\u0001\u001a\u00020h2\u0007\u0010\u0089\u0001\u001a\u00020u2\u0007\u0010\u008b\u0001\u001a\u00020\u00052\u0007\u0010\u008c\u0001\u001a\u00020\u00052\u0007\u0010\u008d\u0001\u001a\u00020\u0005H\u0002J\u0018\u0010\u008f\u0001\u001a\u00020h2\u0007\u0010\u0090\u0001\u001a\u00020 H\u0000\u00a2\u0006\u0003\b\u0091\u0001J\t\u0010\u0092\u0001\u001a\u00020hH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\t8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\r8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0011\u0010\u000fR\u0014\u0010\u0012\u001a\u00020\r8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u000fR\u0014\u0010\u0014\u001a\u00020\r8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u000fR\u0014\u0010\u0016\u001a\u00020\t8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\u000bR\u0014\u0010\u0018\u001a\u00020\u00198BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u00078BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u000e\u0010\u001f\u001a\u00020 X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010!\u001a\u00020\u00198BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\"\u0010\u001bR\u0014\u0010#\u001a\u00020$8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b%\u0010&R\u0014\u0010\'\u001a\u00020\t8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b(\u0010\u000bR\u0014\u0010)\u001a\u00020*8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b)\u0010+R\u0014\u0010,\u001a\u00020*8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b,\u0010+R\u0014\u0010-\u001a\u00020*8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b-\u0010+R\u0014\u0010.\u001a\u00020*8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b.\u0010+R\u0014\u0010/\u001a\u0002008BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b1\u00102R\u000e\u00103\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R*\u00104\u001a\u0012\u0012\u0004\u0012\u00020\u000505j\b\u0012\u0004\u0012\u00020\u0005`6X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R.\u0010;\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010<05j\n\u0012\u0006\u0012\u0004\u0018\u00010<`6X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b=\u00108\"\u0004\b>\u0010:R*\u0010?\u001a\u0012\u0012\u0004\u0012\u00020\u000505j\b\u0012\u0004\u0012\u00020\u0005`6X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b@\u00108\"\u0004\bA\u0010:R*\u0010B\u001a\u0012\u0012\u0004\u0012\u00020*05j\b\u0012\u0004\u0012\u00020*`6X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bC\u00108\"\u0004\bD\u0010:R\u0014\u0010E\u001a\u00020\t8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\bF\u0010\u000bR\u0014\u0010G\u001a\u00020\u00198BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\bH\u0010\u001bR\u000e\u0010I\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010J\u001a\u00020KX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\bL\u0010M\"\u0004\bN\u0010OR\u0014\u0010P\u001a\u00020\r8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\bQ\u0010\u000fR\u000e\u0010R\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010S\u001a\u00020T8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\bU\u0010VR\u0014\u0010W\u001a\u00020X8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\bY\u0010ZR\u0014\u0010[\u001a\u00020\\8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b]\u0010^R\u0014\u0010_\u001a\u00020\\8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b`\u0010^R\u0014\u0010a\u001a\u00020\\8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\bb\u0010^R\u0014\u0010c\u001a\u00020\\8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\bd\u0010^R\u0014\u0010e\u001a\u00020\\8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\bf\u0010^\u00a8\u0006\u0093\u0001"}, d2 = {"Lcom/example/appthitracnghiem/ui/home/createtest/question/FragmentCreateExam;", "Lcom/example/appthitracnghiem/ui/base/BaseFragment;", "Lcom/example/appthitracnghiem/ui/EmptyViewModel;", "()V", "GALLERY_RED_CODE", "", "_binding", "Lcom/example/appthitracnghiem/databinding/FragmentCreateExamBinding;", "addCoverImageCreateTest", "Landroid/widget/ImageView;", "getAddCoverImageCreateTest", "()Landroid/widget/ImageView;", "answerCreate1", "Landroid/widget/EditText;", "getAnswerCreate1", "()Landroid/widget/EditText;", "answerCreate2", "getAnswerCreate2", "answerCreate3", "getAnswerCreate3", "answerCreate4", "getAnswerCreate4", "backCreateTest", "getBackCreateTest", "backQuestionCreate", "Landroidx/cardview/widget/CardView;", "getBackQuestionCreate", "()Landroidx/cardview/widget/CardView;", "binding", "getBinding", "()Lcom/example/appthitracnghiem/databinding/FragmentCreateExamBinding;", "checkVisibleComplete", "", "completeCreateTest", "getCompleteCreateTest", "createLevel", "Landroid/widget/LinearLayout;", "getCreateLevel", "()Landroid/widget/LinearLayout;", "imageCoverCreateTest", "getImageCoverCreateTest", "isAnswer1", "Landroid/widget/CheckBox;", "()Landroid/widget/CheckBox;", "isAnswer2", "isAnswer3", "isAnswer4", "layoutOnClickCreate", "Landroid/widget/RelativeLayout;", "getLayoutOnClickCreate", "()Landroid/widget/RelativeLayout;", "level", "listNumberQuestion", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getListNumberQuestion", "()Ljava/util/ArrayList;", "setListNumberQuestion", "(Ljava/util/ArrayList;)V", "listQuestionCreate", "Lcom/example/appthitracnghiem/model/CreateQuestion;", "getListQuestionCreate", "setListQuestionCreate", "listResults", "getListResults", "setListResults", "listTextViewAnswer", "getListTextViewAnswer", "setListTextViewAnswer", "menuCreateTestAct", "getMenuCreateTestAct", "nextQuestionCreate", "getNextQuestionCreate", "numberQuiz", "positiveQuestionAdapter", "Lcom/example/appthitracnghiem/ui/home/createtest/question/adapter/PositiveQuestionAdapter;", "getPositiveQuestionAdapter", "()Lcom/example/appthitracnghiem/ui/home/createtest/question/adapter/PositiveQuestionAdapter;", "setPositiveQuestionAdapter", "(Lcom/example/appthitracnghiem/ui/home/createtest/question/adapter/PositiveQuestionAdapter;)V", "questionCreate", "getQuestionCreate", "questionIndex", "recycleListNumber", "Landroidx/recyclerview/widget/RecyclerView;", "getRecycleListNumber", "()Landroidx/recyclerview/widget/RecyclerView;", "scrollCreateExam", "Landroidx/core/widget/NestedScrollView;", "getScrollCreateExam", "()Landroidx/core/widget/NestedScrollView;", "txtAddQuestion", "Landroid/widget/TextView;", "getTxtAddQuestion", "()Landroid/widget/TextView;", "txtCreateTest", "getTxtCreateTest", "txtLevel", "getTxtLevel", "txtSelectImageCt", "getTxtSelectImageCt", "txtTime", "getTxtTime", "clearFocusTextView", "", "doEmptyText", "getListPositive", "key", "", "getListQuestion", "initUi", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onFragmentBack", "onViewCreated", "view", "saveExam", "saveListPositive", "list", "saveListQuestion", "selectAnswer", "setText", "setTextView", "setUnSelectAnswer", "showMenuCreate", "anchor", "layout", "x", "y", "position", "showMenuLevel", "visibleComplete", "visible", "visibleComplete$app_developmentDebug", "visibleCompleteExam", "app_developmentDebug"})
public final class FragmentCreateExam extends com.example.appthitracnghiem.ui.base.BaseFragment<com.example.appthitracnghiem.ui.EmptyViewModel> {
    private com.example.appthitracnghiem.databinding.FragmentCreateExamBinding _binding;
    public com.example.appthitracnghiem.ui.home.createtest.question.adapter.PositiveQuestionAdapter positiveQuestionAdapter;
    private final int GALLERY_RED_CODE = 1000;
    private int numberQuiz = 0;
    private int questionIndex = 0;
    private boolean checkVisibleComplete = false;
    private int level = -1;
    @org.jetbrains.annotations.NotNull
    private java.util.ArrayList<com.example.appthitracnghiem.model.CreateQuestion> listQuestionCreate;
    @org.jetbrains.annotations.NotNull
    private java.util.ArrayList<java.lang.Integer> listNumberQuestion;
    @org.jetbrains.annotations.NotNull
    private java.util.ArrayList<android.widget.CheckBox> listTextViewAnswer;
    @org.jetbrains.annotations.NotNull
    private java.util.ArrayList<java.lang.Integer> listResults;
    
    public FragmentCreateExam() {
        super();
    }
    
    private final com.example.appthitracnghiem.databinding.FragmentCreateExamBinding getBinding() {
        return null;
    }
    
    private final android.widget.RelativeLayout getLayoutOnClickCreate() {
        return null;
    }
    
    private final android.widget.CheckBox isAnswer1() {
        return null;
    }
    
    private final android.widget.CheckBox isAnswer2() {
        return null;
    }
    
    private final android.widget.CheckBox isAnswer3() {
        return null;
    }
    
    private final android.widget.CheckBox isAnswer4() {
        return null;
    }
    
    private final androidx.recyclerview.widget.RecyclerView getRecycleListNumber() {
        return null;
    }
    
    private final android.widget.TextView getTxtTime() {
        return null;
    }
    
    private final android.widget.TextView getTxtLevel() {
        return null;
    }
    
    private final androidx.cardview.widget.CardView getNextQuestionCreate() {
        return null;
    }
    
    private final androidx.cardview.widget.CardView getBackQuestionCreate() {
        return null;
    }
    
    private final androidx.cardview.widget.CardView getCompleteCreateTest() {
        return null;
    }
    
    private final android.widget.ImageView getBackCreateTest() {
        return null;
    }
    
    private final android.widget.ImageView getAddCoverImageCreateTest() {
        return null;
    }
    
    private final android.widget.ImageView getMenuCreateTestAct() {
        return null;
    }
    
    private final android.widget.LinearLayout getCreateLevel() {
        return null;
    }
    
    private final android.widget.EditText getQuestionCreate() {
        return null;
    }
    
    private final android.widget.EditText getAnswerCreate1() {
        return null;
    }
    
    private final android.widget.EditText getAnswerCreate2() {
        return null;
    }
    
    private final android.widget.EditText getAnswerCreate3() {
        return null;
    }
    
    private final android.widget.EditText getAnswerCreate4() {
        return null;
    }
    
    private final androidx.core.widget.NestedScrollView getScrollCreateExam() {
        return null;
    }
    
    private final android.widget.TextView getTxtCreateTest() {
        return null;
    }
    
    private final android.widget.TextView getTxtSelectImageCt() {
        return null;
    }
    
    private final android.widget.TextView getTxtAddQuestion() {
        return null;
    }
    
    private final android.widget.ImageView getImageCoverCreateTest() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.appthitracnghiem.ui.home.createtest.question.adapter.PositiveQuestionAdapter getPositiveQuestionAdapter() {
        return null;
    }
    
    public final void setPositiveQuestionAdapter(@org.jetbrains.annotations.NotNull
    com.example.appthitracnghiem.ui.home.createtest.question.adapter.PositiveQuestionAdapter p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.ArrayList<com.example.appthitracnghiem.model.CreateQuestion> getListQuestionCreate() {
        return null;
    }
    
    public final void setListQuestionCreate(@org.jetbrains.annotations.NotNull
    java.util.ArrayList<com.example.appthitracnghiem.model.CreateQuestion> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.ArrayList<java.lang.Integer> getListNumberQuestion() {
        return null;
    }
    
    public final void setListNumberQuestion(@org.jetbrains.annotations.NotNull
    java.util.ArrayList<java.lang.Integer> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.ArrayList<android.widget.CheckBox> getListTextViewAnswer() {
        return null;
    }
    
    public final void setListTextViewAnswer(@org.jetbrains.annotations.NotNull
    java.util.ArrayList<android.widget.CheckBox> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.ArrayList<java.lang.Integer> getListResults() {
        return null;
    }
    
    public final void setListResults(@org.jetbrains.annotations.NotNull
    java.util.ArrayList<java.lang.Integer> p0) {
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n", "ClickableViewAccessibility"})
    @java.lang.Override
    public void onViewCreated(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @android.annotation.SuppressLint(value = {"NotifyDataSetChanged", "SetTextI18n"})
    private final void initUi() {
    }
    
    private final void clearFocusTextView() {
    }
    
    private final void setTextView() {
    }
    
    private final void visibleCompleteExam() {
    }
    
    private final void saveExam() {
    }
    
    private final void saveListPositive(java.util.ArrayList<java.lang.Integer> list, java.lang.String key) {
    }
    
    private final java.util.ArrayList<java.lang.Integer> getListPositive(java.lang.String key) {
        return null;
    }
    
    private final void saveListQuestion(java.util.ArrayList<com.example.appthitracnghiem.model.CreateQuestion> list, java.lang.String key) {
    }
    
    private final java.util.ArrayList<com.example.appthitracnghiem.model.CreateQuestion> getListQuestion(java.lang.String key) {
        return null;
    }
    
    public final void visibleComplete$app_developmentDebug(boolean visible) {
    }
    
    private final void doEmptyText() {
    }
    
    private final void selectAnswer() {
    }
    
    private final void setUnSelectAnswer() {
    }
    
    private final void setText() {
    }
    
    @java.lang.Override
    public void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable
    android.content.Intent data) {
    }
    
    private final void showMenuCreate(android.view.View anchor, int layout, int x, int y, int position) {
    }
    
    private final void showMenuLevel(android.view.View anchor, int x, int y, int position) {
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
}