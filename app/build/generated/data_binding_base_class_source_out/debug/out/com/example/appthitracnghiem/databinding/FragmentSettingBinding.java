// Generated by view binder compiler. Do not edit!
package com.example.appthitracnghiem.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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

public final class FragmentSettingBinding implements ViewBinding {
  @NonNull
  private final CoordinatorLayout rootView;

  @NonNull
  public final ImageView backSetting;

  @NonNull
  public final CardView changeEmail;

  @NonNull
  public final CardView changePassWord;

  @NonNull
  public final CardView deleteAccount;

  @NonNull
  public final CoordinatorLayout fragmentSetting;

  @NonNull
  public final CardView frequentQuestion;

  @NonNull
  public final RelativeLayout layoutLogoutCover;

  @NonNull
  public final TextView logout;

  @NonNull
  public final CardView priorityMode;

  @NonNull
  public final TextView txtSetting;

  @NonNull
  public final CardView updateInfo;

  private FragmentSettingBinding(@NonNull CoordinatorLayout rootView,
      @NonNull ImageView backSetting, @NonNull CardView changeEmail,
      @NonNull CardView changePassWord, @NonNull CardView deleteAccount,
      @NonNull CoordinatorLayout fragmentSetting, @NonNull CardView frequentQuestion,
      @NonNull RelativeLayout layoutLogoutCover, @NonNull TextView logout,
      @NonNull CardView priorityMode, @NonNull TextView txtSetting, @NonNull CardView updateInfo) {
    this.rootView = rootView;
    this.backSetting = backSetting;
    this.changeEmail = changeEmail;
    this.changePassWord = changePassWord;
    this.deleteAccount = deleteAccount;
    this.fragmentSetting = fragmentSetting;
    this.frequentQuestion = frequentQuestion;
    this.layoutLogoutCover = layoutLogoutCover;
    this.logout = logout;
    this.priorityMode = priorityMode;
    this.txtSetting = txtSetting;
    this.updateInfo = updateInfo;
  }

  @Override
  @NonNull
  public CoordinatorLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentSettingBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentSettingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_setting, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentSettingBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.backSetting;
      ImageView backSetting = ViewBindings.findChildViewById(rootView, id);
      if (backSetting == null) {
        break missingId;
      }

      id = R.id.changeEmail;
      CardView changeEmail = ViewBindings.findChildViewById(rootView, id);
      if (changeEmail == null) {
        break missingId;
      }

      id = R.id.changePassWord;
      CardView changePassWord = ViewBindings.findChildViewById(rootView, id);
      if (changePassWord == null) {
        break missingId;
      }

      id = R.id.deleteAccount;
      CardView deleteAccount = ViewBindings.findChildViewById(rootView, id);
      if (deleteAccount == null) {
        break missingId;
      }

      CoordinatorLayout fragmentSetting = (CoordinatorLayout) rootView;

      id = R.id.frequentQuestion;
      CardView frequentQuestion = ViewBindings.findChildViewById(rootView, id);
      if (frequentQuestion == null) {
        break missingId;
      }

      id = R.id.layoutLogoutCover;
      RelativeLayout layoutLogoutCover = ViewBindings.findChildViewById(rootView, id);
      if (layoutLogoutCover == null) {
        break missingId;
      }

      id = R.id.logout;
      TextView logout = ViewBindings.findChildViewById(rootView, id);
      if (logout == null) {
        break missingId;
      }

      id = R.id.priorityMode;
      CardView priorityMode = ViewBindings.findChildViewById(rootView, id);
      if (priorityMode == null) {
        break missingId;
      }

      id = R.id.txtSetting;
      TextView txtSetting = ViewBindings.findChildViewById(rootView, id);
      if (txtSetting == null) {
        break missingId;
      }

      id = R.id.updateInfo;
      CardView updateInfo = ViewBindings.findChildViewById(rootView, id);
      if (updateInfo == null) {
        break missingId;
      }

      return new FragmentSettingBinding((CoordinatorLayout) rootView, backSetting, changeEmail,
          changePassWord, deleteAccount, fragmentSetting, frequentQuestion, layoutLogoutCover,
          logout, priorityMode, txtSetting, updateInfo);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
