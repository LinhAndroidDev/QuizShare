package com.example.appthitracnghiem.ui.home.history.saved.subject

import androidx.lifecycle.MutableLiveData
import com.example.appthitracnghiem.data.remote.ApiClient
import com.example.appthitracnghiem.data.remote.entity.SaveSubjectResponse
import com.example.appthitracnghiem.ui.base.BaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistorySubjectSavedViewModel : BaseViewModel() {
    var isLoadingLiveData = MutableLiveData<Boolean>()
    var listSubjectSaved = MutableLiveData<ArrayList<SaveSubjectResponse.Result>?>()

    fun saveSubject(
        header: String,
        requestSubjectSaved: RequestSubjectSaved
    )
    {
        isLoadingLiveData.value = true
        ApiClient.shared().savedSubject(header, requestSubjectSaved)
            .enqueue(object : Callback<SaveSubjectResponse>{
                override fun onResponse(
                    call: Call<SaveSubjectResponse>,
                    response: Response<SaveSubjectResponse>
                ) {
                    isLoadingLiveData.value = false
                    if(response.isSuccessful){
                        response.body().let {
                            when(it?.statusCode){
                                ApiClient.STATUS_CODE_SUCCESS->{
                                    listSubjectSaved.value = it.result
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

                override fun onFailure(call: Call<SaveSubjectResponse>, t: Throwable) {
                    isLoadingLiveData.value = false
                    errorApiLiveData.value = t.message
                }

            })
    }
}