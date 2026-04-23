package com.example.appthitracnghiem.data.remote.dto.request

import com.example.appthitracnghiem.model.CreateQuestion
import java.io.File

data class RequestUpdateInfo(
    val user_id: Int,
    val name: String,
    val birthday: String,
)

data class RequestUnPublishUser(
    val user_id: Int,
)

data class RequestCategory(
    val user_id: Int,
    val department_id: Int,
    val keyword: String,
)

data class RequestSearch(
    val user_id: Int,
    val department_id: Int,
    val keyword: String?,
)

data class RequestListExam(
    val user_id: Int,
    val subject_id: Int,
    val type: Int,
    val sort_field: Int,
    val sort_by: String,
)

data class RequestAvatar(
    val user_id: Int,
    val file: File,
)

data class RequestUpdateEmail(
    val user_id: Int,
    val email: String,
)

data class RequestSaveExam(
    val user_id: Int,
    val exam_id: Int,
)

data class RequestEmailVerification(
    val email: String,
)

data class RequestPoint(
    val user_id: Int,
    val exam_id: Int,
    val answer_list: HashMap<String, Int?>,
    val start_time: String,
    val finish_time: String,
)

data class RequestDepartmentInfo(
    val user_id: Int,
)

data class RequestCreateExam(
    val question_exam_list: ArrayList<CreateQuestion?>,
    val user_id: Int,
    val subject_id: Int,
    val title: String,
    val time: Int,
    val number: Int,
    val status: Int,
)

data class RequestSubjectSaved(
    val user_id: Int,
    val department_id: Int,
)

data class RequestRegister(
    val email: String,
    val name: String,
    val phone_number: String,
    val birthday: String,
    val password: String,
)

data class RequestUserInfo(
    val user_id: Int,
)

data class RequestGetListDepartment(
    val user_id: Int,
    val keyword: String,
)

data class RequestExamQuestion(
    val user_id: Int,
    val exam_id: Int,
)

data class RequestExamHistory(
    val user_id: Int,
    val limit: Int,
    val offset: Int,
    val sort_field: Int,
    val sort_by: String,
)

data class RequestChangePassword(
    val password: String,
    val cf_password: String,
    val user_id: Int,
)

data class RequestLogin(
    val login_id: String,
    val password: String,
)

data class RequestSavedDepartment(
    val user_id: Int,
)

data class RequestAnswer(
    val user_id: Int,
    val exam_history_id: Int,
)

data class RequestTestSaved(
    val user_id: Int,
    val subject_id: Int,
    val type: Int,
    val sort_field: Int,
    val sort_by: String,
)
