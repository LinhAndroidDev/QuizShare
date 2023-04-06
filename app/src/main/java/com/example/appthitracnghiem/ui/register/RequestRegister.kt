package com.example.appthitracnghiem.ui.register

data class RequestRegister(
    val email: String,
    val name: String,
    val phone_number: String,
    val birthday: String,
    val password: String
)