@file:Suppress("DEPRECATION")

package com.example.appthitracnghiem.ui.home.createtest.review

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.data.remote.dto.request.RequestCreateExam
import com.example.appthitracnghiem.databinding.FragmentReviewCreateExamBinding
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.createtest.manager.FragmentManageExam
import com.example.appthitracnghiem.ui.home.createtest.question.CreateExamDraftViewModel
import com.example.appthitracnghiem.ui.home.createtest.question.CreateTestIntentExtras
import com.example.appthitracnghiem.utils.Const
import com.example.appthitracnghiem.utils.PreferenceKey
import com.example.appthitracnghiem.utils.UriConvertFile
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import dagger.hilt.android.AndroidEntryPoint
import androidx.core.net.toUri

@AndroidEntryPoint
class FragmentReviewCreateExam : BaseFragment<CreateExamViewModel>() {
    private var _binding: FragmentReviewCreateExamBinding? = null
    private val binding get() = _binding!!

    private val draftViewModel: CreateExamDraftViewModel by activityViewModels()

    lateinit var positionReviewAdapter: PositionReviewAdapter
    var questionIndex: Int = 0
    var numberQuiz: Int = 0
    var listTextViewAnswer: ArrayList<TextView> = arrayListOf()
    var time: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /** Insert textview answer in array **/
        listTextViewAnswer.add(binding.answerReview1)
        listTextViewAnswer.add(binding.answerReview2)
        listTextViewAnswer.add(binding.answerReview3)
        listTextViewAnswer.add(binding.answerReview4)

        numberQuiz = requireArguments().getInt("numberQuiz")
        positionReviewAdapter = PositionReviewAdapter(numberQuiz, requireActivity())
        val linear = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.recycleListNumberReview.layoutManager = linear
        binding.recycleListNumberReview.adapter = positionReviewAdapter

        initUi()
    }

    override fun bindData() {
        super.bindData()

        val loading = ProgressDialog(requireActivity())
        loading.setTitle(getString(R.string.dialog_title_notice))
        loading.setMessage(getString(R.string.loading_please_wait))
        viewModel.isLoadingLiveData.observe(viewLifecycleOwner) {
            if (it) {
                loading.show()
            } else {
                loading.dismiss()
            }
        }

        viewModel.isSuccessfulLiveData.observe(viewLifecycleOwner) {
            if (it) {
                val fragmentManageExam = FragmentManageExam()
                val fm: FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
                fm?.add(R.id.changeIdCreateExam, fragmentManageExam)
                    ?.addToBackStack(null)?.commit()
            }
        }

        viewModel.uploadSuccessfulLiveData.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(requireActivity(), getString(R.string.toast_upload_cover_success), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun setTextExam(index: Int) {
        val listQuestion = draftViewModel.questions
        val q = listQuestion.getOrNull(index) ?: return
        binding.txtQuestionReview.text = q.question_title
        binding.answerReview1.text = q.answer_list.getOrNull(0)?.content
        binding.answerReview2.text = q.answer_list.getOrNull(1)?.content
        binding.answerReview3.text = q.answer_list.getOrNull(2)?.content
        binding.answerReview4.text = q.answer_list.getOrNull(3)?.content
        for (i in q.answer_list.indices) {
            if (q.answer_list[i]?.type == 1) {
                listTextViewAnswer[i].setBackgroundResource(R.drawable.boder_answer_create)
            } else {
                listTextViewAnswer[i].setBackgroundResource(R.drawable.boder_setting_new_password)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initUi() {
        time = requireActivity().intent.getIntExtra(CreateTestIntentExtras.TIME_MINUTES, 0)
        binding.txtTimeReview.text = getString(R.string.format_minutes_suffix, time)

        setTextExam(questionIndex)

        positionReviewAdapter.onClickItem = {
            questionIndex = it
            setTextExam(questionIndex)
        }

        binding.nextQuestionReview.setOnClickListener {
            if (questionIndex < numberQuiz - 1) {
                questionIndex++
                positionReviewAdapter.setSelectedIndex(questionIndex)
            }
            binding.recycleListNumberReview.scrollToPosition(questionIndex)
            setTextExam(questionIndex)
        }

        binding.backQuestionReview.setOnClickListener {
            if (questionIndex > 0) {
                questionIndex--
                positionReviewAdapter.setSelectedIndex(questionIndex)
            }
            binding.recycleListNumberReview.scrollToPosition(questionIndex)
            setTextExam(questionIndex)
        }

        binding.doneExamReview.setOnClickListener {
            val userId = viewModel.mPreferenceUtil.defaultPref()
                .getInt(PreferenceKey.USER_ID, 0)
            val intent = requireActivity().intent
            val title = intent.getStringExtra(CreateTestIntentExtras.TITLE).orEmpty()
            val number = intent.getIntExtra(CreateTestIntentExtras.NUMBER_QUESTION, -1)
            val status = intent.getIntExtra(CreateTestIntentExtras.STATUS, 0)
            val subjectId = intent.getIntExtra(CreateTestIntentExtras.SUBJECT_ID, -1)
            val listQuestionCreate = draftViewModel.questions

            val requestCreateExam = RequestCreateExam(
                listQuestionCreate, userId, subjectId, title, time, number, status
            )
            viewModel.createExam(requestCreateExam)

            val strImage = intent.getStringExtra(CreateTestIntentExtras.COVER_URI).orEmpty()
//            val uriImage: Uri = strImage.toUri()
//            val strPath: String = UriConvertFile.getFileFromUri(requireActivity(), uriImage).toString()
//            val file = File(strPath)
//            val requestBodyImage: RequestBody =
//                file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
//            val multipartBodyImage: MultipartBody.Part =
//                MultipartBody.Part.createFormData(Const.file, file.name, requestBodyImage)
//            val requestBodyId: RequestBody =
//                userId.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
//            val folder = "exam"
//            val requestBodyFolder: RequestBody =
//                folder.toRequestBody("multipart/form-data".toMediaTypeOrNull())
//            val fileName = "23471341347.jpg"
//            val requestBodyFileName: RequestBody =
//                fileName.toRequestBody("multipart/form-data".toMediaTypeOrNull())
//
//            viewModel.postUploadFile(requestBodyId, multipartBodyImage, requestBodyFolder, requestBodyFileName)
        }

        binding.backReview.setOnClickListener {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentReviewCreateExamBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}