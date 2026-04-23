package com.example.appthitracnghiem.data.local.preferences

import android.content.SharedPreferences
import com.example.appthitracnghiem.utils.PreferenceKey
import javax.inject.Inject

class SessionLocalDataSource @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) {
    fun isFirstInstallDone(): Boolean = sharedPreferences.getBoolean(PreferenceKey.KEY_FIRST_INSTALL, false)

    fun setFirstInstallDone() {
        sharedPreferences.edit().putBoolean(PreferenceKey.KEY_FIRST_INSTALL, true).apply()
    }

    fun isUserLoggedIn(): Boolean = sharedPreferences.getBoolean(PreferenceKey.KEY_USER_LOGGED_IN, false)

    fun saveUserSession(accessToken: String, userId: Int) {
        sharedPreferences.edit()
            .putBoolean(PreferenceKey.KEY_USER_LOGGED_IN, true)
            .putString(PreferenceKey.AUTHORIZATION, accessToken)
            .putInt(PreferenceKey.USER_ID, userId)
            .apply()
    }
}
