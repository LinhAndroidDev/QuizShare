package com.example.appthitracnghiem.data.remote;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'J\"\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\b\b\u0001\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u000b\u001a\u00020\fH\'J\"\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00032\b\b\u0001\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u000f\u001a\u00020\u0010H\'J\"\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00032\b\b\u0001\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u0013\u001a\u00020\u0014H\'J\"\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u00032\b\b\u0001\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u0017\u001a\u00020\u0018H\'J\"\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00032\b\b\u0001\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u001b\u001a\u00020\u001cH\'J(\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0\u001e0\u00032\b\b\u0001\u0010 \u001a\u00020\n2\b\b\u0001\u0010!\u001a\u00020\nH\'J(\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020#0\u001e0\u00032\b\b\u0001\u0010 \u001a\u00020\n2\b\b\u0001\u0010!\u001a\u00020\nH\'J\"\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u00032\b\b\u0001\u0010\t\u001a\u00020\n2\b\b\u0001\u0010&\u001a\u00020\'H\'J\u0018\u0010(\u001a\b\u0012\u0004\u0012\u00020)0\u00032\b\b\u0001\u0010*\u001a\u00020+H\'J\u0018\u0010,\u001a\b\u0012\u0004\u0012\u00020-0\u00032\b\b\u0001\u0010.\u001a\u00020/H\'\u00a8\u00060"}, d2 = {"Lcom/example/appthitracnghiem/data/remote/ApiService;", "", "emailVerification", "Lretrofit2/Call;", "Lcom/example/appthitracnghiem/data/remote/entity/EmailVerificationResponse;", "requestEmailVerification", "Lcom/example/appthitracnghiem/ui/login/forgetpassword/RequestEmailVerification;", "getCategory", "Lcom/example/appthitracnghiem/data/remote/entity/CategoryResponse;", "header", "", "requestCategory", "Lcom/example/appthitracnghiem/ui/home/category/RequestCategory;", "getDepartmentList", "Lcom/example/appthitracnghiem/data/remote/entity/FromSystemResponse;", "requestGetListDepartment", "Lcom/example/appthitracnghiem/ui/home/home/system/RequestGetListDepartment;", "getExamListQuestion", "Lcom/example/appthitracnghiem/data/remote/entity/ExamQuestionResponse;", "requestExamQuestion", "Lcom/example/appthitracnghiem/ui/exercise/exercise/RequestExamQuestion;", "getListDepartmentInfo", "Lcom/example/appthitracnghiem/data/remote/entity/DepartmentResponse;", "requestDepartmentInfo", "Lcom/example/appthitracnghiem/ui/department/listdepartment/RequestDepartmentInfo;", "getListExam", "Lcom/example/appthitracnghiem/data/remote/entity/ListExamResponse;", "requestListExam", "Lcom/example/appthitracnghiem/ui/department/listtest/RequestListExam;", "getListQuiz", "", "Lcom/example/appthitracnghiem/model/Department;", "alt", "token", "getListSubject", "Lcom/example/appthitracnghiem/model/RecommandSubject;", "getUserInfo", "Lcom/example/appthitracnghiem/data/remote/entity/UserResponse;", "requestUserInfo", "Lcom/example/appthitracnghiem/ui/home/RequestUserInfo;", "loginUser", "Lcom/example/appthitracnghiem/data/remote/entity/LoginResponse;", "requestLogin", "Lcom/example/appthitracnghiem/ui/login/RequestLogin;", "registerUser", "Lcom/example/appthitracnghiem/data/remote/entity/RegisterResponse;", "requestRegister", "Lcom/example/appthitracnghiem/ui/register/RequestRegister;", "app_developmentDebug"})
public abstract interface ApiService {
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "listquiz.json")
    public abstract retrofit2.Call<java.util.List<com.example.appthitracnghiem.model.Department>> getListQuiz(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "alt")
    java.lang.String alt, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "token")
    java.lang.String token);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "listSubject.json")
    public abstract retrofit2.Call<java.util.List<com.example.appthitracnghiem.model.RecommandSubject>> getListSubject(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "alt")
    java.lang.String alt, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "token")
    java.lang.String token);
    
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
    @retrofit2.http.POST(value = "searchSubject")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.CategoryResponse> getCategory(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.example.appthitracnghiem.ui.home.category.RequestCategory requestCategory);
    
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
    com.example.appthitracnghiem.ui.exercise.exercise.RequestExamQuestion requestExamQuestion);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "getUserInfo")
    public abstract retrofit2.Call<com.example.appthitracnghiem.data.remote.entity.UserResponse> getUserInfo(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Header(value = "Authorization")
    java.lang.String header, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.example.appthitracnghiem.ui.home.RequestUserInfo requestUserInfo);
}