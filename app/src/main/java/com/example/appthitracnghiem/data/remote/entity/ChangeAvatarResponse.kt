package com.example.appthitracnghiem.data.remote.entity

import com.example.appthitracnghiem.data.remote.BaseResponse

data class ChangeAvatarResponse(
    override val result: Boolean?
    ) : BaseResponse<Boolean?>()