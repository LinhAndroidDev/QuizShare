// Generated by view binder compiler. Do not edit!
package com.example.appthitracnghiem.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.appthitracnghiem.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentManageExamBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final ImageView backManageExam;

  @NonNull
  public final RecyclerView listTestFromSystem;

  @NonNull
  public final EditText searchTest;

  @NonNull
  public final TextView txtTitleManage;

  @NonNull
  public final TextView txtTitleTopic;

  private FragmentManageExamBinding(@NonNull FrameLayout rootView,
      @NonNull ImageView backManageExam, @NonNull RecyclerView listTestFromSystem,
      @NonNull EditText searchTest, @NonNull TextView txtTitleManage,
      @NonNull TextView txtTitleTopic) {
    this.rootView = rootView;
    this.backManageExam = backManageExam;
    this.listTestFromSystem = listTestFromSystem;
    this.searchTest = searchTest;
    this.txtTitleManage = txtTitleManage;
    this.txtTitleTopic = txtTitleTopic;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentManageExamBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentManageExamBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_manage_exam, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentManageExamBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.backManageExam;
      ImageView backManageExam = ViewBindings.findChildViewById(rootView, id);
      if (backManageExam == null) {
        break missingId;
      }

      id = R.id.listTestFromSystem;
      RecyclerView listTestFromSystem = ViewBindings.findChildViewById(rootView, id);
      if (listTestFromSystem == null) {
        break missingId;
      }

      id = R.id.searchTest;
      EditText searchTest = ViewBindings.findChildViewById(rootView, id);
      if (searchTest == null) {
        break missingId;
      }

      id = R.id.txtTitleManage;
      TextView txtTitleManage = ViewBindings.findChildViewById(rootView, id);
      if (txtTitleManage == null) {
        break missingId;
      }

      id = R.id.txtTitleTopic;
      TextView txtTitleTopic = ViewBindings.findChildViewById(rootView, id);
      if (txtTitleTopic == null) {
        break missingId;
      }

      return new FragmentManageExamBinding((FrameLayout) rootView, backManageExam,
          listTestFromSystem, searchTest, txtTitleManage, txtTitleTopic);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
