package com.example.appthitracnghiem.data.remote

import com.example.appthitracnghiem.data.remote.entity.FromSystemResponse
import com.example.appthitracnghiem.data.remote.entity.LoginResponse
import com.example.appthitracnghiem.data.remote.entity.RegisterResponse
import com.example.appthitracnghiem.model.LoginSuccessful
import com.example.appthitracnghiem.model.Department
import com.example.appthitracnghiem.model.RecommandSubject
import com.example.appthitracnghiem.ui.login.RequestLogin
import com.example.appthitracnghiem.ui.register.RequestRegister
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    //https://firebasestorage.googleapis.com/v0/b/realtime-64f58.appspot.com/o/listquiz.json?alt=media&token=1dd6d12d-c075-46fe-8fde-342cc7d0b49b
    @GET("listquiz.json")
    fun getListQuiz(@Query("alt")  alt : String
                    ,@Query("token") token : String) : Call<List<Department>>

    //https://firebasestorage.googleapis.com/v0/b/realtime-64f58.appspot.com/o/listSubject.json?alt=media&token=e6a753c7-ae4d-43f8-88f5-3909fc3e2919
    @GET("listSubject.json")
    fun getListSubject(@Query("alt")  alt : String
                       ,@Query("token") token : String) : Call<List<RecommandSubject>>

    @POST("register")
    fun registerUser(@Body requestRegister: RequestRegister)
    : Call<RegisterResponse>

    @POST("login")
    fun loginUser(@Body requestLogin: RequestLogin)
            : Call<LoginResponse>

    @POST("getDepartmentList")
    fun getDepartmentList() : Call<FromSystemResponse>

    @Multipart
    @POST("forgetpassword.php")
    fun forgetPassword(@Part("email") email: RequestBody, @Part("password") password : RequestBody)
            : Call<LoginSuccessful>
}