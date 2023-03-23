package com.example.appthitracnghiem.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiClient {
    const val STATUS_CODE_SUCCESS = 200

    //Todo: Change BASE_URL
    const val BASE_URL = "https://google.com/"
    private var RETROFIT: Retrofit? = null
    private var API_SERVICE: ApiService? = null

    private fun getClient(): Retrofit? {
        return RETROFIT ?: synchronized(this) {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            RETROFIT = retrofit
            RETROFIT
        }
    }

    fun shared(): ApiService {
        return API_SERVICE ?: synchronized(this) {
            val apiService = getClient()?.create(ApiService::class.java)
            API_SERVICE = apiService
            API_SERVICE!!
        }
    }
}