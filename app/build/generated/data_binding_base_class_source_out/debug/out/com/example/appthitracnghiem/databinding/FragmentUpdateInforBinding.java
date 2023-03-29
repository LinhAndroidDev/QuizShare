// Generated by view binder compiler. Do not edit!
package com.example.appthitracnghiem.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.appthitracnghiem.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentUpdateInforBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final ImageView backUpdateInfo;

  @NonNull
  public final TextView txtCapNhatThongTin;

  private FragmentUpdateInforBinding(@NonNull LinearLayout rootView,
      @NonNull ImageView backUpdateInfo, @NonNull TextView txtCapNhatThongTin) {
    this.rootView = rootView;
    this.backUpdateInfo = backUpdateInfo;
    this.txtCapNhatThongTin = txtCapNhatThongTin;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentUpdateInforBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentUpdateInforBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_update_infor, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentUpdateInforBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.backUpdateInfo;
      ImageView backUpdateInfo = ViewBindings.findChildViewById(rootView, id);
      if (backUpdateInfo == null) {
        break missingId;
      }

      id = R.id.txtCapNhatThongTin;
      TextView txtCapNhatThongTin = ViewBindings.findChildViewById(rootView, id);
      if (txtCapNhatThongTin == null) {
        break missingId;
      }

      return new FragmentUpdateInforBinding((LinearLayout) rootView, backUpdateInfo,
          txtCapNhatThongTin);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
