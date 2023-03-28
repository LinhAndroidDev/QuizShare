package com.example.appthitracnghiem.ui.register

data class RequestRegister(
    val email: String,
    val name: String,
    val phone_number: String,
    val year_of_birth: String,
    val password: String
)