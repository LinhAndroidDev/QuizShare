package com.example.appthitracnghiem.ui.home.history.test.topic

import androidx.lifecycle.MutableLiveData
import com.example.appthitracnghiem.data.remote.ApiClient
import com.example.appthitracnghiem.data.remote.entity.HistoryTopicResponse
import com.example.appthitracnghiem.ui.base.BaseViewModel
import com.example.appthitracnghiem.utils.PreferenceKey
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryTopicViewModel : BaseViewModel() {
    val isLoadingLiveData = MutableLiveData<Boolean>()
    val isSuccessfulLiveData = MutableLiveData<Boolean>()

    fun getIdExam(
        header: String,
        userId: Int,
        examHistoryId: Int
    ){
        isLoadingLiveData.value = true
        ApiClient.shared().getExamHistoryDetail(header, userId, examHistoryId)
            .enqueue(object : Callback<HistoryTopicResponse>{
                override fun onResponse(
                    call: Call<HistoryTopicResponse>,
                    response: Response<HistoryTopicResponse>,
                ) {
                    isLoadingLiveData.value = false
                    if(response.isSuccessful){
                        response.body().let {
                            when(it?.statusCode){
                                ApiClient.STATUS_CODE_SUCCESS->{
                                    it.result.id?.let { it1 ->
                                        mPreferenceUtil.defaultPref().edit()
                                            .putInt(PreferenceKey.ID_EXAM, it1)
                                            .apply()
                                    }
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

                override fun onFailure(call: Call<HistoryTopicResponse>, t: Throwable) {
                    isLoadingLiveData.value = false
                    errorApiLiveData.value = t.message
                }

            })
    }
}