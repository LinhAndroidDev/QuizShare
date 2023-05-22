package com.example.appthitracnghiem.model

data class DetailDepartment(
    val id: Int,
    val exam_num: Int,
    val title: String,
    var subjects: List<Subject>
    )