package com.example.appthitracnghiem.data.remote;

import com.example.appthitracnghiem.data.remote.entity.*;
import com.example.appthitracnghiem.utils.Const;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.*;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u00e2\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u0001H\'J\"\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\n\u001a\u00020\u0001H\'J\"\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\r\u001a\u00020\u0001H\'J\u0018\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00032\b\b\u0001\u0010\u0010\u001a\u00020\u0001H\'J\"\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0013\u001a\u00020\u0001H\'J\"\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0016\u001a\u00020\u0001H\'J,\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0019\u001a\u00020\u001a2\b\b\u0001\u0010\u001b\u001a\u00020\u001aH\'J\"\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u001e\u001a\u00020\u0001H\'J\"\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010!\u001a\u00020\u0001H\'J\"\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010$\u001a\u00020\u0001H\'J\"\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\'\u001a\u00020\u0001H\'J\"\u0010(\u001a\b\u0012\u0004\u0012\u00020)0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010*\u001a\u00020\u0001H\'J\u0018\u0010+\u001a\b\u0012\u0004\u0012\u00020,0\u00032\b\b\u0001\u0010-\u001a\u00020\u0001H\'J,\u0010.\u001a\b\u0012\u0004\u0012\u00020/0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0019\u001a\u0002002\b\b\u0001\u00101\u001a\u000202H\'J@\u00103\u001a\b\u0012\u0004\u0012\u0002040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0019\u001a\u0002002\b\b\u0001\u00101\u001a\u0002022\b\b\u0001\u00105\u001a\u0002002\b\b\u0001\u00106\u001a\u000200H\'J\u0018\u00107\u001a\b\u0012\u0004\u0012\u0002080\u00032\b\b\u0001\u00109\u001a\u00020\u0001H\'J\"\u0010:\u001a\b\u0012\u0004\u0012\u00020;0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010<\u001a\u00020\u0001H\'J\"\u0010=\u001a\b\u0012\u0004\u0012\u00020>0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010?\u001a\u00020\u0001H\'J\"\u0010@\u001a\b\u0012\u0004\u0012\u00020A0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010B\u001a\u00020\u0001H\'J\"\u0010C\u001a\b\u0012\u0004\u0012\u00020D0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010E\u001a\u00020\u0001H\'J\"\u0010F\u001a\b\u0012\u0004\u0012\u00020G0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010H\u001a\u00020\u0001H\'J\"\u0010I\u001a\b\u0012\u0004\u0012\u00020J0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010K\u001a\u00020\u0001H\'J\"\u0010L\u001a\b\u0012\u0004\u0012\u00020M0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010N\u001a\u00020\u0001H\'J\"\u0010O\u001a\b\u0012\u0004\u0012\u00020P0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010Q\u001a\u00020\u0001H\'\u00a8\u0006R"}, d2 = {"Lcom/example/appthitracnghiem/data/remote/ApiService;", "", "changeEmail", "Lretrofit2/Call;", "Lcom/example/appthitracnghiem/data/remote/entity/UpdateEmailResponse;", "header", "", "requestUpdateEmail", "changePassword", "Lcom/example/appthitracnghiem/data/remote/entity/ChangePasswordResponse;", "requestChangePassword", "createExam", "Lcom/example/appthitracnghiem/data/remote/entity/CreateExamResponse;", "requestCreateExam", "emailVerification", "Lcom/example/appthitracnghiem/data/remote/entity/EmailVerificationResponse;", "requestEmailVerification", "getDepartmentList", "Lcom/example/appthitracnghiem/data/remote/entity/FromSystemResponse;", "requestGetListDepartment", "getExamHistory", "Lcom/example/appthitracnghiem/data/remote/entity/ExamHistoryResponse;", "requestExamHistory", "getExamHistoryDetail", "Lcom/example/appthitracnghiem/data/remote/entity/HistoryTopicResponse;", "user_id", "", "exam_history_id", "getExamListQuestion", "Lcom/example/appthitracnghiem/data/remote/entity/ExamQuestionResponse;", "requestExamQuestion", "getExamResult", "Lcom/example/appthitracnghiem/data/remote/entity/AnswerResponse;", "requestAnswer", "getListDepartmentInfo", "Lcom/example/appthitracnghiem/data/remote/entity/DepartmentResponse;", "requestDepartmentInfo", "getListExam", "Lcom/example/appthitracnghiem/data/remote/entity/ListExamResponse;", "requestListExam", "getUserInfo", "Lcom/example/appthitracnghiem/data/remote/entity/UserResponse;", "requestUserInfo", "loginUser", "Lcom/example/appthitracnghiem/data/remote/entity/LoginResponse;", "requestLogin", "postImage", "Lcom/example/appthitracnghiem/data/remote/entity/ChangeAvatarResponse;", "Lokhttp3/RequestBody;", "file", "Lokhttp3/MultipartBody$Part;", "postUploadFile", "Lcom/example/appthitracnghiem/data/remote/entity/UploadImageResponse;", "folder_name", "file_name", "registerUser", "Lcom/example/appthitracnghiem/data/remote/entity/RegisterResponse;", "requestRegister", "saveExam", "Lcom/example/appthitracnghiem/data/remote/entity/SaveExamResponse;", "requestSaveExam", "saveTest", "Lcom/example/appthitracnghiem/data/remote/entity/TestSavedResponse;", "requestTestSaved", "savedDepartment", "Lcom/example/appthitracnghiem/data/remote/entity/DepartmentSavedResponse;", "requestSavedDepartment", "savedSubject", "Lcom/example/appthitracnghiem/data/remote/entity/SaveSubjectResponse;", "requestSaveSubjectSaved", "searchSubject", "Lcom/example/appthitracnghiem/data/remote/entity/SearchResponse;", "requestSearch", "submitExam", "Lcom/example/appthitracnghiem/data/remote/entity/PointResponse;", "requestPoint", "unPublishUser", "Lcom/example/appthitracnghiem/data/remote/entity/UnPublishUserResponse;", "requestUnPublishUser", "updateUserInfo", "Lcom/example/appthitracnghiem/data/remote/entity/UpdateInfoResponse;", "requestUpdateInfo", "app_developmentDebug"})
public abstract interface ApiService {
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.POST(value = "register")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.RegisterResponse> registerUser(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    java.lang.Object requestRegister);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.POST(value = "login")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.LoginResponse> loginUser(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    java.lang.Object requestLogin);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.POST(value = "forgotPassword")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.EmailVerificationResponse> emailVerification(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    java.lang.Object requestEmailVerification);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.POST(value = "getDepartmentList")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.FromSystemResponse> getDepartmentList(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    java.lang.Object requestGetListDepartment);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.POST(value = "listDepartmentInfo")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.DepartmentResponse> getListDepartmentInfo(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    java.lang.Object requestDepartmentInfo);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.POST(value = "listExam")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.ListExamResponse> getListExam(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    java.lang.Object requestListExam);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.POST(value = "examListQuestion")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.ExamQuestionResponse> getExamListQuestion(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    java.lang.Object requestExamQuestion);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.POST(value = "submitExam")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.PointResponse> submitExam(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    java.lang.Object requestPoint);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.POST(value = "getUserInfo")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.UserResponse> getUserInfo(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    java.lang.Object requestUserInfo);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.POST(value = "editAvatar")
    @retrofit2.http.Multipart
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.ChangeAvatarResponse> postImage(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Part(value = "user_id")
    okhttp3.RequestBody user_id, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Part
    okhttp3.MultipartBody.Part file);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.POST(value = "getExamHistoryList")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.ExamHistoryResponse> getExamHistory(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    java.lang.Object requestExamHistory);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.POST(value = "changeEmail")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.UpdateEmailResponse> changeEmail(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    java.lang.Object requestUpdateEmail);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.POST(value = "updateUserInfo")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.UpdateInfoResponse> updateUserInfo(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    java.lang.Object requestUpdateInfo);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.POST(value = "changePassword")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.ChangePasswordResponse> changePassword(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    java.lang.Object requestChangePassword);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.POST(value = "postSaveExam")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.SaveExamResponse> saveExam(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    java.lang.Object requestSaveExam);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.POST(value = "savedDepartment")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.DepartmentSavedResponse> savedDepartment(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    java.lang.Object requestSavedDepartment);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.POST(value = "savedSubject")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.SaveSubjectResponse> savedSubject(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    java.lang.Object requestSaveSubjectSaved);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.POST(value = "savedExam")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.TestSavedResponse> saveTest(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    java.lang.Object requestTestSaved);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.POST(value = "unpublicUser")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.UnPublishUserResponse> unPublishUser(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    java.lang.Object requestUnPublishUser);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.POST(value = "searchSubject")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.SearchResponse> searchSubject(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    java.lang.Object requestSearch);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.POST(value = "getExamResult")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.AnswerResponse> getExamResult(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    java.lang.Object requestAnswer);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.POST(value = "createExam")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.CreateExamResponse> createExam(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    java.lang.Object requestCreateExam);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.GET(value = "getExamHistoryDetail")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.HistoryTopicResponse> getExamHistoryDetail(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @retrofit2.http.Query(value = "user_id")
    int user_id, @retrofit2.http.Query(value = "exam_history_id")
    int exam_history_id);
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.POST(value = "postUploadFile")
    @retrofit2.http.Multipart
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.UploadImageResponse> postUploadFile(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Part(value = "user_id")
    okhttp3.RequestBody user_id, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Part
    okhttp3.MultipartBody.Part file, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Part(value = "folder_name")
    okhttp3.RequestBody folder_name, @org.jetbrains.annotations.NotNull
    @retrofit2.http.Part(value = "file_name")
    okhttp3.RequestBody file_name);
}