// Generated by view binder compiler. Do not edit!
package com.example.appthitracnghiem.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.appthitracnghiem.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityExamBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final LinearLayout changeIdExam;

  @NonNull
  public final LayoutLoadingBinding loadingExam;

  private ActivityExamBinding(@NonNull RelativeLayout rootView, @NonNull LinearLayout changeIdExam,
      @NonNull LayoutLoadingBinding loadingExam) {
    this.rootView = rootView;
    this.changeIdExam = changeIdExam;
    this.loadingExam = loadingExam;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityExamBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityExamBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_exam, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityExamBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.changeIdExam;
      LinearLayout changeIdExam = ViewBindings.findChildViewById(rootView, id);
      if (changeIdExam == null) {
        break missingId;
      }

      id = R.id.loadingExam;
      View loadingExam = ViewBindings.findChildViewById(rootView, id);
      if (loadingExam == null) {
        break missingId;
      }
      LayoutLoadingBinding binding_loadingExam = LayoutLoadingBinding.bind(loadingExam);

      return new ActivityExamBinding((RelativeLayout) rootView, changeIdExam, binding_loadingExam);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
