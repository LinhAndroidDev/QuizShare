package com.example.appthitracnghiem.data.remote.entity

import com.example.appthitracnghiem.data.remote.BaseResponse

class UserResponse(
    override val result: Result?
) :
    BaseResponse<UserResponse.Result>() {
    data class Result(
        val access_token: String?,
        val email: String?,
        val user_id: Int?,
        val name: String?,
        val phone_number: String?,
        val year_of_birth: Int?,
        val role : Int?,
        val status : Int?
    )
}