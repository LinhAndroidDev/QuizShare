package com.example.appthitracnghiem.data.remote.entity

import com.example.appthitracnghiem.data.remote.BaseResponse

class UserResponse(
    override val result: Result?
) :
    BaseResponse<UserResponse.Result>() {
    data class Result(
        val access_token: String?,
        val avatar: String?,
        val email: String?,
        val id: Int?,
        val name: String?,
        val phone_number: String?,
        val birthday: Int?,
        val role : Int?,
        val status : Int?
    )
}