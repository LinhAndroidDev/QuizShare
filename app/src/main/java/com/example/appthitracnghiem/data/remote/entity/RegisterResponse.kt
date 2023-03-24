package com.example.appthitracnghiem.data.remote.entity

import com.example.appthitracnghiem.data.remote.BaseResponse

data class RegisterResponse(
    override val result: String
) : BaseResponse<String>() {

}