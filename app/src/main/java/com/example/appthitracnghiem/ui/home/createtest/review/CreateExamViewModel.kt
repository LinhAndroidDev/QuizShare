package com.example.appthitracnghiem.ui.home.createtest.review

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appthitracnghiem.core.ResultState
import com.example.appthitracnghiem.data.remote.ApiService
import com.example.appthitracnghiem.data.remote.dto.request.RequestCreateExam
import com.example.appthitracnghiem.data.remote.safeApiCall
import com.example.appthitracnghiem.model.CreateQuestion
import com.example.appthitracnghiem.ui.base.BaseViewModel
import com.example.appthitracnghiem.ui.home.createtest.question.CreateExamFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

/**
 * Phạm vi [com.example.appthitracnghiem.ui.home.createtest.question.CreateTestActivity]:
 * form tạo đề, danh sách câu nháp, và gọi API tạo đề / upload.
 */
@HiltViewModel
class CreateExamViewModel @Inject constructor(
    private val apiService: ApiService,
) : BaseViewModel() {

    private var formState: CreateExamFormState? = null

    private lateinit var _questions: ArrayList<CreateQuestion?>

    val questions: ArrayList<CreateQuestion?>
        get() = _questions

    fun bindForm(state: CreateExamFormState) {
        formState = state
    }

    fun requireForm(): CreateExamFormState =
        checkNotNull(formState) {
            "CreateExamFormState missing — open CreateTestActivity with CreateTestIntentExtras.FORM_STATE"
        }

    fun initQuestionSlots(count: Int) {
        if (!::_questions.isInitialized || _questions.size != count) {
            _questions = ArrayList(List(count) { null })
        }
    }

    fun setQuestionAt(index: Int, question: CreateQuestion?) {
        if (!::_questions.isInitialized) return
        if (index in _questions.indices) {
            _questions[index] = question
        }
    }

    var isLoadingLiveData = MutableLiveData<Boolean>()
    var isSuccessfulLiveData = MutableLiveData<Boolean>()
    var uploadSuccessfulLiveData = MutableLiveData<Boolean>()

    fun createExam(requestCreateExam: RequestCreateExam) {
        isLoadingLiveData.value = true
        viewModelScope.launch {
            val result = safeApiCall { apiService.createExam(requestCreateExam) }
            isLoadingLiveData.value = false
            when (result) {
                is ResultState.Success -> isSuccessfulLiveData.value = true
                is ResultState.Error -> errorApiLiveData.value = result.message
            }
        }
    }

    fun postUploadFile(
        user_id: RequestBody,
        file: MultipartBody.Part,
        folder_name: RequestBody,
        file_name: RequestBody,
    ) {
        viewModelScope.launch {
            val result = safeApiCall { apiService.postUploadFile(user_id, file, folder_name, file_name) }
            when (result) {
                is ResultState.Success -> uploadSuccessfulLiveData.value = true
                is ResultState.Error -> errorApiLiveData.value = result.message
            }
        }
    }
}
