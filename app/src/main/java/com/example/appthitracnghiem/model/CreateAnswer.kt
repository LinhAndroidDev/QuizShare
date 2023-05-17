package com.example.appthitracnghiem.model

data class CreateAnswer(
    val content: String,
    val image: String?,
    val sort: Int,
    val image_url: String?,
    val type: Int
)