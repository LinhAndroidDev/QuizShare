// Generated by view binder compiler. Do not edit!
package com.example.appthitracnghiem.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.appthitracnghiem.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentListDepartmentBinding implements ViewBinding {
  @NonNull
  private final NestedScrollView rootView;

  @NonNull
  public final ImageView backCategory;

  @NonNull
  public final RecyclerView recycleDetailListDepartment;

  @NonNull
  public final TextView searchDepartment;

  @NonNull
  public final TextView textTitleCategory;

  private FragmentListDepartmentBinding(@NonNull NestedScrollView rootView,
      @NonNull ImageView backCategory, @NonNull RecyclerView recycleDetailListDepartment,
      @NonNull TextView searchDepartment, @NonNull TextView textTitleCategory) {
    this.rootView = rootView;
    this.backCategory = backCategory;
    this.recycleDetailListDepartment = recycleDetailListDepartment;
    this.searchDepartment = searchDepartment;
    this.textTitleCategory = textTitleCategory;
  }

  @Override
  @NonNull
  public NestedScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentListDepartmentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentListDepartmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_list_department, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentListDepartmentBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.backCategory;
      ImageView backCategory = ViewBindings.findChildViewById(rootView, id);
      if (backCategory == null) {
        break missingId;
      }

      id = R.id.recycleDetailListDepartment;
      RecyclerView recycleDetailListDepartment = ViewBindings.findChildViewById(rootView, id);
      if (recycleDetailListDepartment == null) {
        break missingId;
      }

      id = R.id.searchDepartment;
      TextView searchDepartment = ViewBindings.findChildViewById(rootView, id);
      if (searchDepartment == null) {
        break missingId;
      }

      id = R.id.textTitleCategory;
      TextView textTitleCategory = ViewBindings.findChildViewById(rootView, id);
      if (textTitleCategory == null) {
        break missingId;
      }

      return new FragmentListDepartmentBinding((NestedScrollView) rootView, backCategory,
          recycleDetailListDepartment, searchDepartment, textTitleCategory);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
