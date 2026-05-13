@file:Suppress("DEPRECATION")

package com.example.appthitracnghiem.ui.home.createtest.review

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.data.remote.dto.request.RequestCreateExam
import com.example.appthitracnghiem.databinding.FragmentReviewCreateExamBinding
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.createtest.manager.FragmentManageExam
import com.example.appthitracnghiem.utils.PreferenceKey
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentReviewCreateExam : BaseFragment<CreateExamViewModel>() {
    private var _binding: FragmentReviewCreateExamBinding? = null
    private val binding get() = _binding!!

    override fun viewModelStoreOwner(): ViewModelStoreOwner = requireActivity()

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

        numberQuiz = viewModel.requireForm().numberQuestion
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
        val listQuestion = viewModel.questions
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
        time = viewModel.requireForm().timeMinutes
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
            val form = viewModel.requireForm()
            val listQuestionCreate = viewModel.questions

            val requestCreateExam = RequestCreateExam(
                listQuestionCreate,
                userId,
                form.subjectId,
                form.title,
                form.timeMinutes,
                form.numberQuestion,
                form.status,
            )
            viewModel.createExam(requestCreateExam)
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