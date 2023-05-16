package com.example.appthitracnghiem.data.remote.entity

import android.icu.text.CaseMap.Title
import com.example.appthitracnghiem.data.remote.BaseResponse

class SaveSubjectResponse(
    override val result: ArrayList<Result>?
    ) : BaseResponse<ArrayList<SaveSubjectResponse.Result>?>() {
    data class Result(
        val department_title: String,
        val exem_number: Int,
        val id: Int,
        val image: String?,
        val title: String
    )
}