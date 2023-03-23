package com.example.appthitracnghiem.data.remote

abstract class BaseResponse<R> {
    val statusCode: Int? = null
    val message: String? = null
    abstract val result: R?
}