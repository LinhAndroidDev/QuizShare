// Generated by view binder compiler. Do not edit!
package com.example.appthitracnghiem.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.appthitracnghiem.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class PopupListTestBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final LinearLayout flowNumberCreate;

  @NonNull
  public final LinearLayout flowSubject;

  @NonNull
  public final LinearLayout flowTime;

  private PopupListTestBinding(@NonNull LinearLayout rootView,
      @NonNull LinearLayout flowNumberCreate, @NonNull LinearLayout flowSubject,
      @NonNull LinearLayout flowTime) {
    this.rootView = rootView;
    this.flowNumberCreate = flowNumberCreate;
    this.flowSubject = flowSubject;
    this.flowTime = flowTime;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static PopupListTestBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static PopupListTestBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.popup_list_test, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static PopupListTestBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.flowNumberCreate;
      LinearLayout flowNumberCreate = ViewBindings.findChildViewById(rootView, id);
      if (flowNumberCreate == null) {
        break missingId;
      }

      id = R.id.flowSubject;
      LinearLayout flowSubject = ViewBindings.findChildViewById(rootView, id);
      if (flowSubject == null) {
        break missingId;
      }

      id = R.id.flowTime;
      LinearLayout flowTime = ViewBindings.findChildViewById(rootView, id);
      if (flowTime == null) {
        break missingId;
      }

      return new PopupListTestBinding((LinearLayout) rootView, flowNumberCreate, flowSubject,
          flowTime);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
