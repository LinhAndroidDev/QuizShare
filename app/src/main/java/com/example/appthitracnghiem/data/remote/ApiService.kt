package com.example.appthitracnghiem.data.remote

import com.example.appthitracnghiem.data.remote.entity.LoginResponse
import com.example.appthitracnghiem.model.LoginSuccessful
import com.example.appthitracnghiem.model.Quiz
import com.example.appthitracnghiem.model.Subject
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    //https://firebasestorage.googleapis.com/v0/b/realtime-64f58.appspot.com/o/listquiz.json?alt=media&token=1dd6d12d-c075-46fe-8fde-342cc7d0b49b
    @GET("listquiz.json")
    fun getListQuiz(@Query("alt")  alt : String
                    ,@Query("token") token : String) : Call<List<Quiz>>

    //https://firebasestorage.googleapis.com/v0/b/realtime-64f58.appspot.com/o/listSubject.json?alt=media&token=e6a753c7-ae4d-43f8-88f5-3909fc3e2919
    @GET("listSubject.json")
    fun getListSubject(@Query("alt")  alt : String
                       ,@Query("token") token : String) : Call<List<Subject>>

    @Multipart
    @POST("register.php")
    fun registerUser(@Part("email") email: RequestBody, @Part("password") password : RequestBody)
    : Call<LoginSuccessful>

    @Multipart
    @POST("login.php")
    fun loginUser(@Part("email") email: RequestBody, @Part("password") password: RequestBody)
            : Call<LoginResponse>

    @Multipart
    @POST("forgetpassword.php")
    fun forgetPassword(@Part("email") email: RequestBody, @Part("password") password : RequestBody)
            : Call<LoginSuccessful>
}