package com.example.appthitracnghiem.ui.main

import com.example.appthitracnghiem.ui.base.BaseViewModel
import com.example.appthitracnghiem.utils.PreferenceKey

class MainViewModel : BaseViewModel() {
    fun isFirstInstallApp(): Boolean {
        return mPreferenceUtil.defaultPref()
            .getBoolean(PreferenceKey.KEY_FIRST_INSTALL, true)
    }

    fun clearFirstInstallApp() {
        mPreferenceUtil.defaultPref().edit()
            .putBoolean(PreferenceKey.KEY_FIRST_INSTALL, false).apply()
    }

    fun isUserLoggedIn(): Boolean {
        return mPreferenceUtil.defaultPref()
            .getBoolean(PreferenceKey.KEY_USER_LOGGED_IN, false)
    }
}