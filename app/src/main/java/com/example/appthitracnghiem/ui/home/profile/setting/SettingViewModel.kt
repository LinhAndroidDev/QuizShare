package com.example.appthitracnghiem.ui.home.profile.setting

import com.example.appthitracnghiem.ui.base.BaseViewModel
import com.example.appthitracnghiem.utils.PreferenceKey

class SettingViewModel : BaseViewModel() {
    fun confirmLoggedOut() {
        mPreferenceUtil.defaultPref().edit()
            .putBoolean(PreferenceKey.KEY_USER_LOGGED_IN, false).apply()
    }
}