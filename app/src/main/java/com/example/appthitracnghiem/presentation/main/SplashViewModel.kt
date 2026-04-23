package com.example.appthitracnghiem.presentation.main

import androidx.lifecycle.ViewModel
import com.example.appthitracnghiem.data.local.preferences.SessionLocalDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val sessionLocalDataSource: SessionLocalDataSource,
) : ViewModel() {
    fun isFirstInstallDone(): Boolean = sessionLocalDataSource.isFirstInstallDone()

    fun markFirstInstallDone() {
        sessionLocalDataSource.setFirstInstallDone()
    }

    fun isUserLoggedIn(): Boolean = sessionLocalDataSource.isUserLoggedIn()
}
