package com.example.appthitracnghiem.ui.home.category.search

import androidx.lifecycle.MutableLiveData
import com.example.appthitracnghiem.data.remote.ApiClient
import com.example.appthitracnghiem.data.remote.entity.SearchResponse
import com.example.appthitracnghiem.ui.base.BaseViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : BaseViewModel() {
    val isLoadingLiveData = MutableLiveData<Boolean>()
    val listSearchLiveData = MutableLiveData<ArrayList<SearchResponse.Results>>()

    fun searchSubject(
        header: String,
        requestSearch: RequestSearch
    ){
        isLoadingLiveData.value = true
        ApiClient.shared().searchSubject(header, requestSearch)
            .enqueue(object : Callback<SearchResponse>{
                override fun onResponse(
                    call: Call<SearchResponse>,
                    response: Response<SearchResponse>
                ) {
                    isLoadingLiveData.value = false
                    if(response.isSuccessful){
                        response.body().let {
                            when(it?.statusCode){
                                ApiClient.STATUS_CODE_SUCCESS->{
                                    listSearchLiveData.value = it.result
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

                override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                    isLoadingLiveData.value = false
                    errorApiLiveData.value = t.message
                }

            })
    }
}