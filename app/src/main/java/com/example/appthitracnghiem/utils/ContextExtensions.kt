package com.example.appthitracnghiem.utils

import android.content.Context
import android.content.ContextWrapper
import androidx.appcompat.app.AppCompatActivity

/**
 * Resolves [AppCompatActivity] from a [Context] that may be wrapped (e.g. Hilt
 * [dagger.hilt.android.internal.managers.ViewComponentManager.FragmentContextWrapper]).
 */
tailrec fun Context.findAppCompatActivity(): AppCompatActivity? {
    return when (this) {
        is AppCompatActivity -> this
        is ContextWrapper -> baseContext.findAppCompatActivity()
        else -> null
    }
}
