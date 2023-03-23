package com.example.appthitracnghiem.data.remote.entity

abstract class BaseResponse<TCP_AND_UDP> {
    val statusCode: Int? = null
    val message: String? = null
    abstract val result: TCP_AND_UDP?
}