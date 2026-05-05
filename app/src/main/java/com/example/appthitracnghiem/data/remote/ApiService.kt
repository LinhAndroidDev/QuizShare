package com.example.appthitracnghiem.data.remote

import com.example.appthitracnghiem.data.remote.entity.*
import com.example.appthitracnghiem.utils.Const
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @POST("register")
    suspend fun registerUser(@Body requestRegister: Any): Response<RegisterResponse>

    @POST("login")
    suspend fun loginUser(@Body requestLogin: Any): Response<LoginResponse>

    @POST("forgotPassword")
    suspend fun emailVerification(
        @Body requestEmailVerification: Any
    ): Response<EmailVerificationResponse>

    @POST("getDepartmentList")
    suspend fun getDepartmentList(
        @Body requestGetListDepartment: Any
    ): Response<FromSystemResponse>

    @POST("listDepartmentInfo")
    suspend fun getListDepartmentInfo(
        @Body requestDepartmentInfo: Any
    ): Response<DepartmentResponse>

    @POST("listExam")
    suspend fun getListExam(
        @Body requestListExam: Any
    ): Response<ListExamResponse>

    @POST("examListQuestion")
    suspend fun getExamListQuestion(
        @Body requestExamQuestion: Any
    ): Response<ExamQuestionResponse>

    @POST("submitExam")
    suspend fun submitExam(
        @Body requestPoint: Any
    ): Response<PointResponse>

    @POST("getUserInfo")
    suspend fun getUserInfo(
        @Body requestUserInfo: Any
    ): Response<UserResponse>

    @Multipart
    @POST("editAvatar")
    suspend fun postImage(
        @Part(Const.user_id) user_id: RequestBody,
        @Part file: MultipartBody.Part
    ): Response<ChangeAvatarResponse>

    @POST("getExamHistoryList")
    suspend fun getExamHistory(
        @Body requestExamHistory: Any
    ): Response<ExamHistoryResponse>

    @POST("changeEmail")
    suspend fun changeEmail(
        @Body requestUpdateEmail: Any
    ): Response<UpdateEmailResponse>

    @POST("updateUserInfo")
    suspend fun updateUserInfo(
        @Body requestUpdateInfo: Any
    ): Response<UpdateInfoResponse>

    @POST("changePassword")
    suspend fun changePassword(
        @Body requestChangePassword: Any
    ): Response<ChangePasswordResponse>

    @POST("postSaveExam")
    suspend fun saveExam(
        @Body requestSaveExam: Any
    ): Response<SaveExamResponse>

    @POST("savedDepartment")
    suspend fun savedDepartment(
        @Body requestSavedDepartment: Any
    ): Response<DepartmentSavedResponse>

    @POST("savedSubject")
    suspend fun savedSubject(
        @Body requestSaveSubjectSaved: Any
    ): Response<SaveSubjectResponse>

    @POST("savedExam")
    suspend fun saveTest(
        @Body requestTestSaved: Any
    ): Response<TestSavedResponse>

    @POST("unpublicUser")
    suspend fun unPublishUser(
        @Body requestUnPublishUser: Any
    ): Response<UnPublishUserResponse>

    @POST("searchSubject")
    suspend fun searchSubject(
        @Body requestSearch: Any
    ): Response<SearchResponse>

    @POST("getExamResult")
    suspend fun getExamResult(
        @Body requestAnswer: Any
    ): Response<AnswerResponse>

    @POST("createExam")
    suspend fun createExam(
        @Body requestCreateExam: Any
    ): Response<CreateExamResponse>

    @GET("getExamHistoryDetail")
    suspend fun getExamHistoryDetail(
        @Query("user_id") user_id: Int,
        @Query("exam_history_id") exam_history_id: Int
    ): Response<HistoryTopicResponse>

    @Multipart
    @POST("postUploadFile")
    suspend fun postUploadFile(
        @Part(Const.user_id) user_id: RequestBody,
        @Part file: MultipartBody.Part,
        @Part(Const.folder_name) folder_name: RequestBody,
        @Part(Const.file_name) file_name: RequestBody
    ): Response<UploadImageResponse>
}
