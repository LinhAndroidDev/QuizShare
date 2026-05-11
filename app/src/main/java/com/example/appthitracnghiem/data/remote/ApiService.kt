package com.example.appthitracnghiem.data.remote

import com.example.appthitracnghiem.data.remote.dto.request.RequestAnswer
import com.example.appthitracnghiem.data.remote.dto.request.RequestChangePassword
import com.example.appthitracnghiem.data.remote.dto.request.RequestCreateExam
import com.example.appthitracnghiem.data.remote.dto.request.RequestDepartmentInfo
import com.example.appthitracnghiem.data.remote.dto.request.RequestEmailVerification
import com.example.appthitracnghiem.data.remote.dto.request.RequestExamHistory
import com.example.appthitracnghiem.data.remote.dto.request.RequestExamQuestion
import com.example.appthitracnghiem.data.remote.dto.request.RequestGetListDepartment
import com.example.appthitracnghiem.data.remote.dto.request.RequestListExam
import com.example.appthitracnghiem.data.remote.dto.request.RequestLogin
import com.example.appthitracnghiem.data.remote.dto.request.RequestPoint
import com.example.appthitracnghiem.data.remote.dto.request.RequestRegister
import com.example.appthitracnghiem.data.remote.dto.request.RequestSaveExam
import com.example.appthitracnghiem.data.remote.dto.request.RequestSavedDepartment
import com.example.appthitracnghiem.data.remote.dto.request.RequestSearch
import com.example.appthitracnghiem.data.remote.dto.request.RequestSubjectSaved
import com.example.appthitracnghiem.data.remote.dto.request.RequestTestSaved
import com.example.appthitracnghiem.data.remote.dto.request.RequestUnPublishUser
import com.example.appthitracnghiem.data.remote.dto.request.RequestUpdateEmail
import com.example.appthitracnghiem.data.remote.dto.request.RequestUpdateInfo
import com.example.appthitracnghiem.data.remote.dto.request.RequestUserInfo
import com.example.appthitracnghiem.data.remote.entity.*
import com.example.appthitracnghiem.utils.Const
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @POST("register")
    suspend fun registerUser(@Body requestRegister: RequestRegister): Response<RegisterResponse>

    @POST("login")
    suspend fun loginUser(@Body requestLogin: RequestLogin): Response<LoginResponse>

    @POST("forgotPassword")
    suspend fun emailVerification(
        @Body requestEmailVerification: RequestEmailVerification
    ): Response<EmailVerificationResponse>

    @POST("getDepartmentList")
    suspend fun getDepartmentList(
        @Body requestGetListDepartment: RequestGetListDepartment
    ): Response<FromSystemResponse>

    @POST("listDepartmentInfo")
    suspend fun getListDepartmentInfo(
        @Body requestDepartmentInfo: RequestDepartmentInfo
    ): Response<DepartmentResponse>

    @POST("listExam")
    suspend fun getListExam(
        @Body requestListExam: RequestListExam
    ): Response<ListExamResponse>

    @POST("examListQuestion")
    suspend fun getExamListQuestion(
        @Body requestExamQuestion: RequestExamQuestion
    ): Response<ExamQuestionResponse>

    @POST("submitExam")
    suspend fun submitExam(
        @Body requestPoint: RequestPoint
    ): Response<PointResponse>

    @POST("getUserInfo")
    suspend fun getUserInfo(
        @Body requestUserInfo: RequestUserInfo
    ): Response<UserResponse>

    @Multipart
    @POST("editAvatar")
    suspend fun postImage(
        @Part(Const.user_id) user_id: RequestBody,
        @Part file: MultipartBody.Part
    ): Response<ChangeAvatarResponse>

    @POST("getExamHistoryList")
    suspend fun getExamHistory(
        @Body requestExamHistory: RequestExamHistory
    ): Response<ExamHistoryResponse>

    @POST("changeEmail")
    suspend fun changeEmail(
        @Body requestUpdateEmail: RequestUpdateEmail
    ): Response<UpdateEmailResponse>

    @POST("updateUserInfo")
    suspend fun updateUserInfo(
        @Body requestUpdateInfo: RequestUpdateInfo
    ): Response<UpdateInfoResponse>

    @POST("changePassword")
    suspend fun changePassword(
        @Body requestChangePassword: RequestChangePassword
    ): Response<ChangePasswordResponse>

    @POST("postSaveExam")
    suspend fun saveExam(
        @Body requestSaveExam: RequestSaveExam
    ): Response<SaveExamResponse>

    @POST("savedDepartment")
    suspend fun savedDepartment(
        @Body requestSavedDepartment: RequestSavedDepartment
    ): Response<DepartmentSavedResponse>

    @POST("savedSubject")
    suspend fun savedSubject(
        @Body requestSaveSubjectSaved: RequestSubjectSaved
    ): Response<SaveSubjectResponse>

    @POST("savedExam")
    suspend fun saveTest(
        @Body requestTestSaved: RequestTestSaved
    ): Response<TestSavedResponse>

    @POST("unpublicUser")
    suspend fun unPublishUser(
        @Body requestUnPublishUser: RequestUnPublishUser
    ): Response<UnPublishUserResponse>

    @POST("searchSubject")
    suspend fun searchSubject(
        @Body requestSearch: RequestSearch
    ): Response<SearchResponse>

    @POST("getExamResult")
    suspend fun getExamResult(
        @Body requestAnswer: RequestAnswer
    ): Response<AnswerResponse>

    @POST("createExam")
    suspend fun createExam(
        @Body requestCreateExam: RequestCreateExam
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
