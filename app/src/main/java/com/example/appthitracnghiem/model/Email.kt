package com.example.appthitracnghiem.model

import android.text.TextUtils
import android.util.Patterns

data class Email(val email : String,val password : String) {
    fun isValidEmail() : Boolean{
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches() || (Patterns.PHONE.matcher(email).matches() && email.length >= 10);
    }

    fun isPassword() : Boolean{
        return !TextUtils.isEmpty(password) && password.length >= 6
    }
}