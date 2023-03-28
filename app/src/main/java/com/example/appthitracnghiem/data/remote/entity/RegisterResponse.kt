package com.example.appthitracnghiem.data.remote.entity

import com.example.appthitracnghiem.data.remote.BaseResponse

data class RegisterResponse(
    override val result: Result?
) : BaseResponse<RegisterResponse.Result?>() {
    data class Result(val access_token: String?)
}