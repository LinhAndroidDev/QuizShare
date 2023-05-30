package com.example.appthitracnghiem.ui.home.createtest.review

import androidx.lifecycle.MutableLiveData
import com.example.appthitracnghiem.data.remote.ApiClient
import com.example.appthitracnghiem.data.remote.entity.CreateExamResponse
import com.example.appthitracnghiem.data.remote.entity.UploadImageResponse
import com.example.appthitracnghiem.ui.base.BaseViewModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateExamViewModel : BaseViewModel() {
    var isLoadingLiveData = MutableLiveData<Boolean>()
    var isSuccessfulLiveData = MutableLiveData<Boolean>()
    var uploadSuccessfulLiveData = MutableLiveData<Boolean>()

    fun createExam(
        header: String,
        requestCreateExam: RequestCreateExam
    ){
        isLoadingLiveData.value = true
        ApiClient.shared().createExam(header, requestCreateExam)
            .enqueue(object : Callback<CreateExamResponse>{
                override fun onResponse(
                    call: Call<CreateExamResponse>,
                    response: Response<CreateExamResponse>
                ) {
                    isLoadingLiveData.value = false
                    if(response.isSuccessful){
                        response.body().let {
                            when(it?.statusCode){
                                ApiClient.STATUS_CODE_SUCCESS->{
                                    isSuccessfulLiveData.value = true
                                }
                                ApiClient.STATUS_USER_EXIST->{
                                    errorApiLiveData.value = it.message
                                }
                                ApiClient.STATUS_INVALID_TOKEN->{
                                    errorApiLiveData.value = it.message
                                }
                                ApiClient.STATUS_CODE_SERVER_NOT_RESPONSE->{
                                    errorApiLiveData.value = it.message
                                }
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<CreateExamResponse>, t: Throwable) {
                    isLoadingLiveData.value = false
                    errorApiLiveData.value = t.message
                }

            })
    }

    fun postUploadFile(
        header: String,
        user_id: RequestBody,
        file: MultipartBody.Part,
        folder_name: RequestBody,
        file_name: RequestBody
    ){
        ApiClient.shared().postUploadFile(header, user_id, file, folder_name, file_name)
            .enqueue(object : Callback<UploadImageResponse>{
                override fun onResponse(
                    call: Call<UploadImageResponse>,
                    response: Response<UploadImageResponse>,
                ) {
                    if(response.isSuccessful){
                        response.body().let {
                            when(it?.statusCode){
                                ApiClient.STATUS_CODE_SUCCESS->{
                                    isSuccessfulLiveData.value = true
                                }
                                ApiClient.STATUS_USER_EXIST->{
                                    errorApiLiveData.value = it.message
                                }
                                ApiClient.STATUS_INVALID_TOKEN->{
                                    errorApiLiveData.value = it.message
                                }
                                ApiClient.STATUS_CODE_SERVER_NOT_RESPONSE->{
                                    errorApiLiveData.value = it.message
                                }
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<UploadImageResponse>, t: Throwable) {
                    errorApiLiveData.value = t.message
                }

            })
    }
}