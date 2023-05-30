package com.example.appthitracnghiem.data.remote;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u00b6\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\bH\'J\"\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u000b\u001a\u00020\fH\'J\"\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u000f\u001a\u00020\u0010H\'J\u0018\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00032\b\b\u0001\u0010\u0013\u001a\u00020\u0014H\'J\"\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0017\u001a\u00020\u0018H\'J\"\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u001b\u001a\u00020\u001cH\'J,\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u001f\u001a\u00020 2\b\b\u0001\u0010!\u001a\u00020 H\'J\"\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010$\u001a\u00020%H\'J\"\u0010&\u001a\b\u0012\u0004\u0012\u00020\'0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010(\u001a\u00020)H\'J\"\u0010*\u001a\b\u0012\u0004\u0012\u00020+0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010,\u001a\u00020-H\'J\"\u0010.\u001a\b\u0012\u0004\u0012\u00020/0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u00100\u001a\u000201H\'J\"\u00102\u001a\b\u0012\u0004\u0012\u0002030\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u00104\u001a\u000205H\'J\u0018\u00106\u001a\b\u0012\u0004\u0012\u0002070\u00032\b\b\u0001\u00108\u001a\u000209H\'J,\u0010:\u001a\b\u0012\u0004\u0012\u00020;0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u001f\u001a\u00020<2\b\b\u0001\u0010=\u001a\u00020>H\'J@\u0010?\u001a\b\u0012\u0004\u0012\u00020@0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u001f\u001a\u00020<2\b\b\u0001\u0010=\u001a\u00020>2\b\b\u0001\u0010A\u001a\u00020<2\b\b\u0001\u0010B\u001a\u00020<H\'J\u0018\u0010C\u001a\b\u0012\u0004\u0012\u00020D0\u00032\b\b\u0001\u0010E\u001a\u00020FH\'J\"\u0010G\u001a\b\u0012\u0004\u0012\u00020H0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010I\u001a\u00020JH\'J\"\u0010K\u001a\b\u0012\u0004\u0012\u00020L0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010M\u001a\u00020NH\'J\"\u0010O\u001a\b\u0012\u0004\u0012\u00020P0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010Q\u001a\u00020RH\'J\"\u0010S\u001a\b\u0012\u0004\u0012\u00020T0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010U\u001a\u00020VH\'J\"\u0010W\u001a\b\u0012\u0004\u0012\u00020X0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010Y\u001a\u00020ZH\'J\"\u0010[\u001a\b\u0012\u0004\u0012\u00020\\0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010]\u001a\u00020^H\'J\"\u0010_\u001a\b\u0012\u0004\u0012\u00020`0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010a\u001a\u00020bH\'J\"\u0010c\u001a\b\u0012\u0004\u0012\u00020d0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010e\u001a\u00020fH\'\u00a8\u0006g"}, d2 = {"Lcom/example/appthitracnghiem/data/remote/ApiService;", "", "changeEmail", "Lretrofit2/Call;", "Lcom/example/appthitracnghiem/data/remote/entity/UpdateEmailResponse;", "header", "", "requestUpdateEmail", "Lcom/example/appthitracnghiem/ui/home/profile/setting/email/RequestUpdateEmail;", "changePassword", "Lcom/example/appthitracnghiem/data/remote/entity/ChangePasswordResponse;", "requestChangePassword", "Lcom/example/appthitracnghiem/ui/home/profile/setting/password/RequestChangePassword;", "createExam", "Lcom/example/appthitracnghiem/data/remote/entity/CreateExamResponse;", "requestCreateExam", "Lcom/example/appthitracnghiem/ui/home/createtest/review/RequestCreateExam;", "emailVerification", "Lcom/example/appthitracnghiem/data/remote/entity/EmailVerificationResponse;", "requestEmailVerification", "Lcom/example/appthitracnghiem/ui/login/forgetpassword/RequestEmailVerification;", "getDepartmentList", "Lcom/example/appthitracnghiem/data/remote/entity/FromSystemResponse;", "requestGetListDepartment", "Lcom/example/appthitracnghiem/ui/home/home/system/RequestGetListDepartment;", "getExamHistory", "Lcom/example/appthitracnghiem/data/remote/entity/ExamHistoryResponse;", "requestExamHistory", "Lcom/example/appthitracnghiem/ui/home/history/test/general/RequestExamHistory;", "getExamHistoryDetail", "Lcom/example/appthitracnghiem/data/remote/entity/HistoryTopicResponse;", "user_id", "", "exam_history_id", "getExamListQuestion", "Lcom/example/appthitracnghiem/data/remote/entity/ExamQuestionResponse;", "requestExamQuestion", "Lcom/example/appthitracnghiem/ui/exercise/exercise/exam/RequestExamQuestion;", "getExamResult", "Lcom/example/appthitracnghiem/data/remote/entity/AnswerResponse;", "requestAnswer", "Lcom/example/appthitracnghiem/ui/exercise/exercise/answer/RequestAnswer;", "getListDepartmentInfo", "Lcom/example/appthitracnghiem/data/remote/entity/DepartmentResponse;", "requestDepartmentInfo", "Lcom/example/appthitracnghiem/ui/department/listdepartment/RequestDepartmentInfo;", "getListExam", "Lcom/example/appthitracnghiem/data/remote/entity/ListExamResponse;", "requestListExam", "Lcom/example/appthitracnghiem/ui/department/listtest/RequestListExam;", "getUserInfo", "Lcom/example/appthitracnghiem/data/remote/entity/UserResponse;", "requestUserInfo", "Lcom/example/appthitracnghiem/ui/home/RequestUserInfo;", "loginUser", "Lcom/example/appthitracnghiem/data/remote/entity/LoginResponse;", "requestLogin", "Lcom/example/appthitracnghiem/ui/login/RequestLogin;", "postImage", "Lcom/example/appthitracnghiem/data/remote/entity/ChangeAvatarResponse;", "Lokhttp3/RequestBody;", "file", "Lokhttp3/MultipartBody$Part;", "postUploadFile", "Lcom/example/appthitracnghiem/data/remote/entity/UploadImageResponse;", "folder_name", "file_name", "registerUser", "Lcom/example/appthitracnghiem/data/remote/entity/RegisterResponse;", "requestRegister", "Lcom/example/appthitracnghiem/ui/register/RequestRegister;", "saveExam", "Lcom/example/appthitracnghiem/data/remote/entity/SaveExamResponse;", "requestSaveExam", "Lcom/example/appthitracnghiem/ui/exercise/topic/RequestSaveExam;", "saveTest", "Lcom/example/appthitracnghiem/data/remote/entity/TestSavedResponse;", "requestTestSaved", "Lcom/example/appthitracnghiem/ui/home/history/saved/test/RequestTestSaved;", "savedDepartment", "Lcom/example/appthitracnghiem/data/remote/entity/DepartmentSavedResponse;", "requestSavedDepartment", "Lcom/example/appthitracnghiem/ui/home/history/saved/department/RequestSavedDepartment;", "savedSubject", "Lcom/example/appthitracnghiem/data/remote/entity/SaveSubjectResponse;", "requestSaveSubjectSaved", "Lcom/example/appthitracnghiem/ui/home/history/saved/subject/RequestSubjectSaved;", "searchSubject", "Lcom/example/appthitracnghiem/data/remote/entity/SearchResponse;", "requestSearch", "Lcom/example/appthitracnghiem/ui/home/category/search/RequestSearch;", "submitExam", "Lcom/example/appthitracnghiem/data/remote/entity/PointResponse;", "requestPoint", "Lcom/example/appthitracnghiem/ui/exercise/exercise/point/RequestPoint;", "unPublishUser", "Lcom/example/appthitracnghiem/data/remote/entity/UnPublishUserResponse;", "requestUnPublishUser", "Lcom/example/appthitracnghiem/ui/home/profile/setting/RequestUnPublishUser;", "updateUserInfo", "Lcom/example/appthitracnghiem/data/remote/entity/UpdateInfoResponse;", "requestUpdateInfo", "Lcom/example/appthitracnghiem/ui/home/profile/setting/info/RequestUpdateInfo;", "app_developmentDebug"})
public abstract interface ApiService {
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "register")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.RegisterResponse> registerUser(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.example.appthitracnghiem.ui.register.RequestRegister requestRegister);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "login")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.LoginResponse> loginUser(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.example.appthitracnghiem.ui.login.RequestLogin requestLogin);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "forgotPassword")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.EmailVerificationResponse> emailVerification(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.example.appthitracnghiem.ui.login.forgetpassword.RequestEmailVerification requestEmailVerification);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "getDepartmentList")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.FromSystemResponse> getDepartmentList(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.example.appthitracnghiem.ui.home.home.system.RequestGetListDepartment requestGetListDepartment);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "listDepartmentInfo")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.DepartmentResponse> getListDepartmentInfo(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.example.appthitracnghiem.ui.department.listdepartment.RequestDepartmentInfo requestDepartmentInfo);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "listExam")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.ListExamResponse> getListExam(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.example.appthitracnghiem.ui.department.listtest.RequestListExam requestListExam);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "examListQuestion")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.ExamQuestionResponse> getExamListQuestion(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.example.appthitracnghiem.ui.exercise.exercise.exam.RequestExamQuestion requestExamQuestion);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "submitExam")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.PointResponse> submitExam(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.example.appthitracnghiem.ui.exercise.exercise.point.RequestPoint requestPoint);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "getUserInfo")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.UserResponse> getUserInfo(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.example.appthitracnghiem.ui.home.RequestUserInfo requestUserInfo);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "editAvatar")
    @retrofit2.http.Multipart()
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.ChangeAvatarResponse> postImage(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Part(value = "user_id")
    okhttp3.RequestBody user_id, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Part()
    okhttp3.MultipartBody.Part file);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "getExamHistoryList")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.ExamHistoryResponse> getExamHistory(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.example.appthitracnghiem.ui.home.history.test.general.RequestExamHistory requestExamHistory);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "changeEmail")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.UpdateEmailResponse> changeEmail(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.example.appthitracnghiem.ui.home.profile.setting.email.RequestUpdateEmail requestUpdateEmail);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "updateUserInfo")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.UpdateInfoResponse> updateUserInfo(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.example.appthitracnghiem.ui.home.profile.setting.info.RequestUpdateInfo requestUpdateInfo);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "changePassword")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.ChangePasswordResponse> changePassword(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.example.appthitracnghiem.ui.home.profile.setting.password.RequestChangePassword requestChangePassword);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "postSaveExam")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.SaveExamResponse> saveExam(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.example.appthitracnghiem.ui.exercise.topic.RequestSaveExam requestSaveExam);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "savedDepartment")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.DepartmentSavedResponse> savedDepartment(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.example.appthitracnghiem.ui.home.history.saved.department.RequestSavedDepartment requestSavedDepartment);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "savedSubject")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.SaveSubjectResponse> savedSubject(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.example.appthitracnghiem.ui.home.history.saved.subject.RequestSubjectSaved requestSaveSubjectSaved);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "savedExam")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.TestSavedResponse> saveTest(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.example.appthitracnghiem.ui.home.history.saved.test.RequestTestSaved requestTestSaved);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "unpublicUser")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.UnPublishUserResponse> unPublishUser(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.example.appthitracnghiem.ui.home.profile.setting.RequestUnPublishUser requestUnPublishUser);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "searchSubject")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.SearchResponse> searchSubject(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.example.appthitracnghiem.ui.home.category.search.RequestSearch requestSearch);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "getExamResult")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.AnswerResponse> getExamResult(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.example.appthitracnghiem.ui.exercise.exercise.answer.RequestAnswer requestAnswer);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "createExam")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.CreateExamResponse> createExam(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.example.appthitracnghiem.ui.home.createtest.review.RequestCreateExam requestCreateExam);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "getExamHistoryDetail")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.HistoryTopicResponse> getExamHistoryDetail(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @retrofit2.http.Query(value = "user_id")
    int user_id, @retrofit2.http.Query(value = "exam_history_id")
    int exam_history_id);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "postUploadFile")
    @retrofit2.http.Multipart()
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.UploadImageResponse> postUploadFile(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Part(value = "user_id")
    okhttp3.RequestBody user_id, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Part()
    okhttp3.MultipartBody.Part file, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Part(value = "folder_name")
    okhttp3.RequestBody folder_name, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Part(value = "file_name")
    okhttp3.RequestBody file_name);
}