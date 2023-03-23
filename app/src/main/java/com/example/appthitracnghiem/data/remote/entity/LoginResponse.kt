package com.example.appthitracnghiem.data.remote.entity

data class LoginResponse(override val result: Result?) :
    BaseResponse<LoginResponse.Result>() {
    data class Result(val access_token: String?)
}