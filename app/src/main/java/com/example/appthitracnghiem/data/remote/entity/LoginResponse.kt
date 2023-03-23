package com.example.appthitracnghiem.data.remote.entity

import com.example.appthitracnghiem.data.remote.BaseResponse

data class LoginResponse(
    override val result: Result?
) :
    BaseResponse<LoginResponse.Result>() {
    data class Result(val access_token: String?)
}