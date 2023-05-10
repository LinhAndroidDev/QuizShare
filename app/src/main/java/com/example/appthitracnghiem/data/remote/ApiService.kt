package com.example.appthitracnghiem.data.remote

import com.example.appthitracnghiem.data.remote.entity.*
import com.example.appthitracnghiem.model.Department
import com.example.appthitracnghiem.model.RecommandSubject
import com.example.appthitracnghiem.ui.department.listdepartment.RequestDepartmentInfo
import com.example.appthitracnghiem.ui.department.listtest.RequestListExam
import com.example.appthitracnghiem.ui.exercise.exercise.RequestExamQuestion
import com.example.appthitracnghiem.ui.home.RequestUserInfo
import com.example.appthitracnghiem.ui.home.category.RequestCategory
import com.example.appthitracnghiem.ui.home.home.system.RequestGetListDepartment
import com.example.appthitracnghiem.ui.login.RequestLogin
import com.example.appthitracnghiem.ui.login.forgetpassword.RequestEmailVerification
import com.example.appthitracnghiem.ui.register.RequestRegister
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    //https://firebasestorage.googleapis.com/v0/b/realtime-64f58.appspot.com/o/listquiz.json?alt=media&token=1dd6d12d-c075-46fe-8fde-342cc7d0b49b
    @GET("listquiz.json")
    fun getListQuiz(
        @Query("alt") alt: String, @Query("token") token: String
    ): Call<List<Department>>

    //https://firebasestorage.googleapis.com/v0/b/realtime-64f58.appspot.com/o/listSubject.json?alt=media&token=e6a753c7-ae4d-43f8-88f5-3909fc3e2919
    @GET("listSubject.json")
    fun getListSubject(
        @Query("alt") alt: String, @Query("token") token: String
    ): Call<List<RecommandSubject>>

    @POST("register")
    fun registerUser(@Body requestRegister: RequestRegister)
            : Call<RegisterResponse>

    @POST("login")
    fun loginUser(@Body requestLogin: RequestLogin)
            : Call<LoginResponse>

    @POST("forgotPassword")
    fun emailVerification(@Body requestEmailVerification: RequestEmailVerification)
            : Call<EmailVerificationResponse>

    @POST("searchSubject")
    fun getCategory(
        @Header("Authorization") header: String,
        @Body requestCategory: RequestCategory
    ): Call<CategoryResponse>

    @POST("getDepartmentList")
    fun getDepartmentList(
        @Header("Authorization") header: String,
        @Body requestGetListDepartment: RequestGetListDepartment
    ): Call<FromSystemResponse>

    @POST("listDepartmentInfo")
    fun getListDepartmentInfo(
        @Header("Authorization") header: String,
        @Body requestDepartmentInfo: RequestDepartmentInfo
    ): Call<DepartmentResponse>

    @POST("listExam")
    fun getListExam(
        @Header("Authorization") header: String,
        @Body requestListExam: RequestListExam
    ): Call<ListExamResponse>

    @POST("examListQuestion")
    fun getExamListQuestion(
        @Header("Authorization") header: String,
        @Body requestExamQuestion: RequestExamQuestion
    ): Call<ExamQuestionResponse>

    @POST("getUserInfo")
    fun getUserInfo(
        @Header("Authorization") header: String,
        @Body requestUserInfo: RequestUserInfo
    ): Call<UserResponse>

//    @POST("postUploadFile")
//    fun postImage(
//        @Header("Authorization") header: String,
//        @Body requestAvatar: RequestAvatar
//    ): Call<>
}