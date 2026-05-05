package com.example.appthitracnghiem.data.local.preferences

import android.content.SharedPreferences
import com.example.appthitracnghiem.utils.PreferenceKey
import javax.inject.Inject
import androidx.core.content.edit

class SessionLocalDataSource @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) {
    fun isFirstInstallDone(): Boolean = sharedPreferences.getBoolean(PreferenceKey.KEY_FIRST_INSTALL, false)

    fun setFirstInstallDone() {
        sharedPreferences.edit {
            putBoolean(
                PreferenceKey.KEY_FIRST_INSTALL,
                true
            )
        }
    }

    fun isUserLoggedIn(): Boolean = sharedPreferences.getBoolean(PreferenceKey.KEY_USER_LOGGED_IN, false)

    fun getToken(): String? = sharedPreferences.getString(PreferenceKey.AUTHORIZATION, null)

    fun saveUserSession(accessToken: String, userId: Int) {
        sharedPreferences.edit {
            putBoolean(PreferenceKey.KEY_USER_LOGGED_IN, true)
                .putString(PreferenceKey.AUTHORIZATION, accessToken)
                .putInt(PreferenceKey.USER_ID, userId)
        }
    }

    fun logout() {
        sharedPreferences.edit {
            putBoolean(PreferenceKey.KEY_USER_LOGGED_IN, false)
                .remove(PreferenceKey.AUTHORIZATION)
        }
    }
}
