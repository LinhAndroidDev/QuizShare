// Generated by view binder compiler. Do not edit!
package com.example.appthitracnghiem.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.appthitracnghiem.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentCreateTestBinding implements ViewBinding {
  @NonNull
  private final CoordinatorLayout rootView;

  @NonNull
  public final ImageView addCoverImage;

  @NonNull
  public final CardView createTest;

  @NonNull
  public final EditText edtNumberQuiz;

  @NonNull
  public final LinearLayout googleSheet;

  @NonNull
  public final ImageView imageCover;

  @NonNull
  public final ImageView menuCreateTest;

  @NonNull
  public final ImageView selectDepartment;

  @NonNull
  public final ImageView selectMode;

  @NonNull
  public final ImageView shareTest;

  @NonNull
  public final TextView txtAddTest;

  @NonNull
  public final TextView txtDetail;

  @NonNull
  public final TextView txtDownTest;

  @NonNull
  public final TextView txtNumberQuestion;

  @NonNull
  public final TextView txtSelectDepartment;

  @NonNull
  public final TextView txtSelectMode;

  @NonNull
  public final TextView txtTiltle;

  @NonNull
  public final TextView txtTimeDoTest;

  private FragmentCreateTestBinding(@NonNull CoordinatorLayout rootView,
      @NonNull ImageView addCoverImage, @NonNull CardView createTest,
      @NonNull EditText edtNumberQuiz, @NonNull LinearLayout googleSheet,
      @NonNull ImageView imageCover, @NonNull ImageView menuCreateTest,
      @NonNull ImageView selectDepartment, @NonNull ImageView selectMode,
      @NonNull ImageView shareTest, @NonNull TextView txtAddTest, @NonNull TextView txtDetail,
      @NonNull TextView txtDownTest, @NonNull TextView txtNumberQuestion,
      @NonNull TextView txtSelectDepartment, @NonNull TextView txtSelectMode,
      @NonNull TextView txtTiltle, @NonNull TextView txtTimeDoTest) {
    this.rootView = rootView;
    this.addCoverImage = addCoverImage;
    this.createTest = createTest;
    this.edtNumberQuiz = edtNumberQuiz;
    this.googleSheet = googleSheet;
    this.imageCover = imageCover;
    this.menuCreateTest = menuCreateTest;
    this.selectDepartment = selectDepartment;
    this.selectMode = selectMode;
    this.shareTest = shareTest;
    this.txtAddTest = txtAddTest;
    this.txtDetail = txtDetail;
    this.txtDownTest = txtDownTest;
    this.txtNumberQuestion = txtNumberQuestion;
    this.txtSelectDepartment = txtSelectDepartment;
    this.txtSelectMode = txtSelectMode;
    this.txtTiltle = txtTiltle;
    this.txtTimeDoTest = txtTimeDoTest;
  }

  @Override
  @NonNull
  public CoordinatorLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentCreateTestBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentCreateTestBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_create_test, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentCreateTestBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.addCoverImage;
      ImageView addCoverImage = ViewBindings.findChildViewById(rootView, id);
      if (addCoverImage == null) {
        break missingId;
      }

      id = R.id.createTest;
      CardView createTest = ViewBindings.findChildViewById(rootView, id);
      if (createTest == null) {
        break missingId;
      }

      id = R.id.edtNumberQuiz;
      EditText edtNumberQuiz = ViewBindings.findChildViewById(rootView, id);
      if (edtNumberQuiz == null) {
        break missingId;
      }

      id = R.id.googleSheet;
      LinearLayout googleSheet = ViewBindings.findChildViewById(rootView, id);
      if (googleSheet == null) {
        break missingId;
      }

      id = R.id.imageCover;
      ImageView imageCover = ViewBindings.findChildViewById(rootView, id);
      if (imageCover == null) {
        break missingId;
      }

      id = R.id.menuCreateTest;
      ImageView menuCreateTest = ViewBindings.findChildViewById(rootView, id);
      if (menuCreateTest == null) {
        break missingId;
      }

      id = R.id.selectDepartment;
      ImageView selectDepartment = ViewBindings.findChildViewById(rootView, id);
      if (selectDepartment == null) {
        break missingId;
      }

      id = R.id.selectMode;
      ImageView selectMode = ViewBindings.findChildViewById(rootView, id);
      if (selectMode == null) {
        break missingId;
      }

      id = R.id.shareTest;
      ImageView shareTest = ViewBindings.findChildViewById(rootView, id);
      if (shareTest == null) {
        break missingId;
      }

      id = R.id.txtAddTest;
      TextView txtAddTest = ViewBindings.findChildViewById(rootView, id);
      if (txtAddTest == null) {
        break missingId;
      }

      id = R.id.txtDetail;
      TextView txtDetail = ViewBindings.findChildViewById(rootView, id);
      if (txtDetail == null) {
        break missingId;
      }

      id = R.id.txtDownTest;
      TextView txtDownTest = ViewBindings.findChildViewById(rootView, id);
      if (txtDownTest == null) {
        break missingId;
      }

      id = R.id.txtNumberQuestion;
      TextView txtNumberQuestion = ViewBindings.findChildViewById(rootView, id);
      if (txtNumberQuestion == null) {
        break missingId;
      }

      id = R.id.txtSelectDepartment;
      TextView txtSelectDepartment = ViewBindings.findChildViewById(rootView, id);
      if (txtSelectDepartment == null) {
        break missingId;
      }

      id = R.id.txtSelectMode;
      TextView txtSelectMode = ViewBindings.findChildViewById(rootView, id);
      if (txtSelectMode == null) {
        break missingId;
      }

      id = R.id.txtTiltle;
      TextView txtTiltle = ViewBindings.findChildViewById(rootView, id);
      if (txtTiltle == null) {
        break missingId;
      }

      id = R.id.txtTimeDoTest;
      TextView txtTimeDoTest = ViewBindings.findChildViewById(rootView, id);
      if (txtTimeDoTest == null) {
        break missingId;
      }

      return new FragmentCreateTestBinding((CoordinatorLayout) rootView, addCoverImage, createTest,
          edtNumberQuiz, googleSheet, imageCover, menuCreateTest, selectDepartment, selectMode,
          shareTest, txtAddTest, txtDetail, txtDownTest, txtNumberQuestion, txtSelectDepartment,
          txtSelectMode, txtTiltle, txtTimeDoTest);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
