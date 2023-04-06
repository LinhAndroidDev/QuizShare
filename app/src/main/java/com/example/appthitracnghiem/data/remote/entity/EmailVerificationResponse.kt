package com.example.appthitracnghiem.data.remote.entity

import com.example.appthitracnghiem.data.remote.BaseResponse

data class EmailVerificationResponse(
    override val result: Boolean?
    ) : BaseResponse<Boolean?>()