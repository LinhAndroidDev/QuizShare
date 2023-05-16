package com.example.appthitracnghiem.ui.home.profile.setting.password

data class RequestChangePassword(
    val password: String,
    val cf_password: String,
    val user_id: Int
)