package com.example.appthitracnghiem.data.remote.entity

import com.example.appthitracnghiem.data.remote.BaseResponse

class SearchResponse(
    override val result: ArrayList<Results>?
    ) : BaseResponse<ArrayList<SearchResponse.Results>?>() {
    data class Results(
        val id: Int,
        val count_exam: Int,
        val description: String,
        val image: String?,
        val title: String
    )
}