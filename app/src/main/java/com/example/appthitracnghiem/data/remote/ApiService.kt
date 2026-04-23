package com.example.appthitracnghiem.data.remote

import com.example.appthitracnghiem.data.remote.entity.*
import com.example.appthitracnghiem.utils.Const
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("register")
    fun registerUser(@Body requestRegister: Any)
            : Call<RegisterResponse>

    @POST("login")
    fun loginUser(@Body requestLogin: Any)
            : Call<LoginResponse>

    @POST("forgotPassword")
    fun emailVerification(
        @Body requestEmailVerification: Any
    ): Call<EmailVerificationResponse>

    @POST("getDepartmentList")
    fun getDepartmentList(
        @Header("Authorization") header: String,
        @Body requestGetListDepartment: Any
    ): Call<FromSystemResponse>

    @POST("listDepartmentInfo")
    fun getListDepartmentInfo(
        @Header("Authorization") header: String,
        @Body requestDepartmentInfo: Any
    ): Call<DepartmentResponse>

    @POST("listExam")
    fun getListExam(
        @Header("Authorization") header: String,
        @Body requestListExam: Any
    ): Call<ListExamResponse>

    @POST("examListQuestion")
    fun getExamListQuestion(
        @Header("Authorization") header: String,
        @Body requestExamQuestion: Any
    ): Call<ExamQuestionResponse>

    @POST("submitExam")
    fun submitExam(
        @Header("Authorization") header: String,
        @Body requestPoint: Any
    ): Call<PointResponse>

    @POST("getUserInfo")
    fun getUserInfo(
        @Header("Authorization") header: String,
        @Body requestUserInfo: Any
    ): Call<UserResponse>

    @Multipart
    @POST("editAvatar")
    fun postImage(
        @Header("Authorization") header: String,
        @Part(Const.user_id) user_id: RequestBody,
        @Part file: MultipartBody.Part
    ): Call<ChangeAvatarResponse>

    @POST("getExamHistoryList")
    fun getExamHistory(
        @Header("Authorization") header: String,
        @Body requestExamHistory: Any
    ): Call<ExamHistoryResponse>

    @POST("changeEmail")
    fun changeEmail(
        @Header("Authorization") header: String,
        @Body requestUpdateEmail: Any
    ): Call<UpdateEmailResponse>

    @POST("updateUserInfo")
    fun updateUserInfo(
        @Header("Authorization") header: String,
        @Body requestUpdateInfo: Any
    ): Call<UpdateInfoResponse>

    @POST("changePassword")
    fun changePassword(
        @Header("Authorization") header: String,
        @Body requestChangePassword: Any
    ): Call<ChangePasswordResponse>

    @POST("postSaveExam")
    fun saveExam(
        @Header("Authorization") header: String,
        @Body requestSaveExam: Any
    ): Call<SaveExamResponse>

    @POST("savedDepartment")
    fun savedDepartment(
        @Header("Authorization") header: String,
        @Body requestSavedDepartment: Any
    ): Call<DepartmentSavedResponse>

    @POST("savedSubject")
    fun savedSubject(
        @Header("Authorization") header: String,
        @Body requestSaveSubjectSaved: Any
    ): Call<SaveSubjectResponse>

    @POST("savedExam")
    fun saveTest(
        @Header("Authorization") header: String,
        @Body requestTestSaved: Any
    ): Call<TestSavedResponse>

    @POST("unpublicUser")
    fun unPublishUser(
        @Header("Authorization") header: String,
        @Body requestUnPublishUser: Any
    ): Call<UnPublishUserResponse>

    @POST("searchSubject")
    fun searchSubject(
        @Header("Authorization") header: String,
        @Body requestSearch: Any
    ): Call<SearchResponse>

    @POST("getExamResult")
    fun getExamResult(
        @Header("Authorization") header: String,
        @Body requestAnswer: Any
    ): Call<AnswerResponse>

    @POST("createExam")
    fun createExam(
        @Header("Authorization") header: String,
        @Body requestCreateExam: Any
    ): Call<CreateExamResponse>

    @GET("getExamHistoryDetail")
    fun getExamHistoryDetail(
        @Header("Authorization") header: String,
        @Query("user_id") user_id: Int,
        @Query("exam_history_id") exam_history_id: Int
    ): Call<HistoryTopicResponse>

    @Multipart
    @POST("postUploadFile")
    fun postUploadFile(
        @Header("Authorization") header: String,
        @Part(Const.user_id) user_id: RequestBody,
        @Part file: MultipartBody.Part,
        @Part(Const.folder_name) folder_name: RequestBody,
        @Part(Const.file_name) file_name: RequestBody
    ): Call<UploadImageResponse>
}