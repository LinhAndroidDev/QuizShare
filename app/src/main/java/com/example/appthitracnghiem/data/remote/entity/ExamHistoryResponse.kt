package com.example.appthitracnghiem.data.remote.entity

import com.example.appthitracnghiem.data.remote.BaseResponse
import com.example.appthitracnghiem.model.HistoryExam

class ExamHistoryResponse(
    override val result: ArrayList<HistoryExam>?
): BaseResponse<ArrayList<HistoryExam>?>()