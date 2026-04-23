package com.example.appthitracnghiem.presentation.main

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.example.appthitracnghiem.R

public class SplashFragmentDirections private constructor() {
  public companion object {
    public fun actionSplashToIntro(): NavDirections =
        ActionOnlyNavDirections(R.id.action_splash_to_intro)

    public fun actionSplashToLogin(): NavDirections =
        ActionOnlyNavDirections(R.id.action_splash_to_login)

    public fun actionSplashToHome(): NavDirections =
        ActionOnlyNavDirections(R.id.action_splash_to_home)
  }
}
