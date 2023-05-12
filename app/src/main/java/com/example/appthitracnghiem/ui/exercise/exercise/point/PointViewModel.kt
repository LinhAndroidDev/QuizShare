package com.example.appthitracnghiem.ui.exercise.exercise.point

import androidx.lifecycle.MutableLiveData
import com.example.appthitracnghiem.data.remote.ApiClient
import com.example.appthitracnghiem.data.remote.entity.PointResponse
import com.example.appthitracnghiem.ui.base.BaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PointViewModel : BaseViewModel() {
    var listExamResultLiveData = MutableLiveData<HashMap<String, Int?>>()
    var scoreLiveData = MutableLiveData<Float>()
    var numberCorrectLiveData = MutableLiveData<Int>()
    var skipNumberLiveData = MutableLiveData<Int>()
    var wrongNumberLiveData = MutableLiveData<Int>()

    fun getResult(header: String, requestPoint: RequestPoint){
        ApiClient.shared().submitExam(header, requestPoint)
            .enqueue(object : Callback<PointResponse> {
                override fun onResponse(
                    call: Call<PointResponse>,
                    response: Response<PointResponse>
                ) {
                    response.body()?.let {
                        when (it.statusCode) {
                            ApiClient.STATUS_CODE_SUCCESS -> {
                                listExamResultLiveData.value = it.result?.exam_result
                                scoreLiveData.value = it.result?.score
                                numberCorrectLiveData.value = it.result?.correct_number
                                skipNumberLiveData.value = it.result?.skip_number
                                wrongNumberLiveData.value = it.result?.wrong_number
                            }
                            ApiClient.STATUS_USER_EXIST -> {
                                errorApiLiveData.value  = it.message
                            }
                            ApiClient.STATUS_INVALID_TOKEN -> {
                                errorApiLiveData.value = it.message
                            }
                            ApiClient.STATUS_CODE_SERVER_NOT_RESPONSE -> {
                                errorApiLiveData.value = it.message
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<PointResponse>, t: Throwable) {
                    errorApiLiveData.value = t.message
                }

            })
    }
}