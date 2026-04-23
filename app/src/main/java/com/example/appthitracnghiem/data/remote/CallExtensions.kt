package com.example.appthitracnghiem.data.remote

import com.example.appthitracnghiem.core.ResultState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun <T : BaseResponse<*>> Call<T>.executeResultState(): ResultState<T> {
    return suspendCoroutine { continuation ->
        enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val body = response.body()
                if (!response.isSuccessful || body == null) {
                    continuation.resume(ResultState.Error(response.message()))
                    return
                }
                if (body.statusCode == ApiClient.STATUS_CODE_SUCCESS) {
                    continuation.resume(ResultState.Success(body))
                } else {
                    continuation.resume(ResultState.Error(body.message ?: "Unknown error"))
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                continuation.resume(ResultState.Error(t.message ?: "Network error"))
            }
        })
    }
}
