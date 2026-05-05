package com.example.appthitracnghiem.data.remote

import com.example.appthitracnghiem.core.ResultState
import retrofit2.Response

suspend fun <T : BaseResponse<*>> safeApiCall(call: suspend () -> Response<T>): ResultState<T> {
    return try {
        val response = call()
        val body = response.body()
        when {
            !response.isSuccessful || body == null ->
                ResultState.Error("HTTP ${response.code()}: ${response.message()}")
            body.statusCode == ApiClient.STATUS_CODE_SUCCESS ->
                ResultState.Success(body)
            else ->
                ResultState.Error(body.message ?: "Unknown error")
        }
    } catch (e: Exception) {
        ResultState.Error(e.message ?: "Network error")
    }
}
