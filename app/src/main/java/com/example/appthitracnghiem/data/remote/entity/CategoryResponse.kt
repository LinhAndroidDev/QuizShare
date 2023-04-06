package com.example.appthitracnghiem.data.remote.entity

import com.example.appthitracnghiem.data.remote.BaseResponse
import com.example.appthitracnghiem.model.RecommandSubject

data class CategoryResponse(
    override val result: RecommandSubject
    ) : BaseResponse<RecommandSubject>()