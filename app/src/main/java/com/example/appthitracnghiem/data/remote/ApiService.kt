package com.example.appthitracnghiem.data.remote

import com.example.appthitracnghiem.data.remote.entity.*
import com.example.appthitracnghiem.ui.department.listdepartment.RequestDepartmentInfo
import com.example.appthitracnghiem.ui.department.listtest.RequestListExam
import com.example.appthitracnghiem.ui.exercise.exercise.answer.RequestAnswer
import com.example.appthitracnghiem.ui.exercise.exercise.exam.RequestExamQuestion
import com.example.appthitracnghiem.ui.exercise.exercise.point.RequestPoint
import com.example.appthitracnghiem.ui.exercise.topic.RequestSaveExam
import com.example.appthitracnghiem.ui.home.RequestUserInfo
import com.example.appthitracnghiem.ui.home.category.search.RequestSearch
import com.example.appthitracnghiem.ui.home.createtest.review.RequestCreateExam
import com.example.appthitracnghiem.ui.home.history.saved.department.RequestSavedDepartment
import com.example.appthitracnghiem.ui.home.history.saved.subject.RequestSubjectSaved
import com.example.appthitracnghiem.ui.home.history.saved.test.RequestTestSaved
import com.example.appthitracnghiem.ui.home.history.test.general.RequestExamHistory
import com.example.appthitracnghiem.ui.home.home.system.RequestGetListDepartment
import com.example.appthitracnghiem.ui.home.profile.setting.RequestUnPublishUser
import com.example.appthitracnghiem.ui.home.profile.setting.email.RequestUpdateEmail
import com.example.appthitracnghiem.ui.home.profile.setting.info.RequestUpdateInfo
import com.example.appthitracnghiem.ui.home.profile.setting.password.RequestChangePassword
import com.example.appthitracnghiem.ui.login.RequestLogin
import com.example.appthitracnghiem.ui.login.forgetpassword.RequestEmailVerification
import com.example.appthitracnghiem.ui.register.RequestRegister
import com.example.appthitracnghiem.utils.Const
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("register")
    fun registerUser(@Body requestRegister: RequestRegister)
            : Call<RegisterResponse>

    @POST("login")
    fun loginUser(@Body requestLogin: RequestLogin)
            : Call<LoginResponse>

    @POST("forgotPassword")
    fun emailVerification(
        @Body requestEmailVerification: RequestEmailVerification
    ): Call<EmailVerificationResponse>

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

    @POST("submitExam")
    fun submitExam(
        @Header("Authorization") header: String,
        @Body requestPoint: RequestPoint
    ): Call<PointResponse>

    @POST("getUserInfo")
    fun getUserInfo(
        @Header("Authorization") header: String,
        @Body requestUserInfo: RequestUserInfo
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
        @Body requestExamHistory: RequestExamHistory
    ): Call<ExamHistoryResponse>

    @POST("changeEmail")
    fun changeEmail(
        @Header("Authorization") header: String,
        @Body requestUpdateEmail: RequestUpdateEmail
    ): Call<UpdateEmailResponse>

    @POST("updateUserInfo")
    fun updateUserInfo(
        @Header("Authorization") header: String,
        @Body requestUpdateInfo: RequestUpdateInfo
    ): Call<UpdateInfoResponse>

    @POST("changePassword")
    fun changePassword(
        @Header("Authorization") header: String,
        @Body requestChangePassword: RequestChangePassword
    ): Call<ChangePasswordResponse>

    @POST("postSaveExam")
    fun saveExam(
        @Header("Authorization") header: String,
        @Body requestSaveExam: RequestSaveExam
    ): Call<SaveExamResponse>

    @POST("savedDepartment")
    fun savedDepartment(
        @Header("Authorization") header: String,
        @Body requestSavedDepartment: RequestSavedDepartment
    ): Call<DepartmentSavedResponse>

    @POST("savedSubject")
    fun savedSubject(
        @Header("Authorization") header: String,
        @Body requestSaveSubjectSaved: RequestSubjectSaved
    ): Call<SaveSubjectResponse>

    @POST("savedExam")
    fun saveTest(
        @Header("Authorization") header: String,
        @Body requestTestSaved: RequestTestSaved
    ): Call<TestSavedResponse>

    @POST("unpublicUser")
    fun unPublishUser(
        @Header("Authorization") header: String,
        @Body requestUnPublishUser: RequestUnPublishUser
    ): Call<UnPublishUserResponse>

    @POST("searchSubject")
    fun searchSubject(
        @Header("Authorization") header: String,
        @Body requestSearch: RequestSearch
    ): Call<SearchResponse>

    @POST("getExamResult")
    fun getExamResult(
        @Header("Authorization") header: String,
        @Body requestAnswer: RequestAnswer
    ): Call<AnswerResponse>

    @POST("createExam")
    fun createExam(
        @Header("Authorization") header: String,
        @Body requestCreateExam: RequestCreateExam
    ): Call<CreateExamResponse>
}