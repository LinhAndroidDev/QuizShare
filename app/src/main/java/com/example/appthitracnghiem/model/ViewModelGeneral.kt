package com.example.appthitracnghiem.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appthitracnghiem.ui.base.api.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ViewModelGeneral : ViewModel() {
    lateinit var retrofit: Retrofit
    lateinit var postRetrofit: Retrofit
    val listQuizLive = MutableLiveData<MutableList<Quiz>>()
    val loadingQuizLive = MutableLiveData(false)
    val listSubjectLive = MutableLiveData<MutableList<Subject>>()
    val loadingSubjectLive = MutableLiveData(false)

    init {
        initRetrofit()
        initPostRetrofit()
    }

    private fun initRetrofit() {
        val gson: Gson = GsonBuilder().setDateFormat("dd-MM-yy").create()

        retrofit = Retrofit.Builder()
            .baseUrl("https://firebasestorage.googleapis.com/v0/b/realtime-64f58.appspot.com/o/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private fun initPostRetrofit() {
        val gson: Gson = GsonBuilder().setDateFormat("dd-MM-yy").create()

        postRetrofit = Retrofit.Builder()
            .baseUrl("http://192.168.10.33/tracnghiem/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun getDataQuiz() {
        loadingQuizLive.postValue(true)
        retrofit.create(ApiService::class.java)
            .getListQuiz("media", "1dd6d12d-c075-46fe-8fde-342cc7d0b49b")
            .enqueue(object : Callback<List<Quiz>> {
                override fun onResponse(call: Call<List<Quiz>>, response: Response<List<Quiz>>) {
                    listQuizLive.postValue(response.body() as MutableList<Quiz>?)
                    loadingQuizLive.postValue(false)
                }

                override fun onFailure(call: Call<List<Quiz>>, t: Throwable) {
                    loadingQuizLive.postValue(false)
                }

            })
    }

    fun getListSubject() {
        loadingSubjectLive.postValue(true)
        retrofit.create(ApiService::class.java)
            .getListSubject("media", "e6a753c7-ae4d-43f8-88f5-3909fc3e2919")
            .enqueue(object : Callback<List<Subject>> {
                override fun onResponse(
                    call: Call<List<Subject>>,
                    response: Response<List<Subject>>
                ) {
                    listSubjectLive.postValue(response.body() as MutableList<Subject>?)
                    loadingSubjectLive.postValue(false)
                }

                override fun onFailure(call: Call<List<Subject>>, t: Throwable) {
                    loadingSubjectLive.postValue(false)
                }

            })
    }
}