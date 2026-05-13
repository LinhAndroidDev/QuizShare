package com.example.appthitracnghiem.ui.home.createtest.question

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.databinding.FragmentCreateExamBinding
import com.example.appthitracnghiem.model.CreateAnswer
import com.example.appthitracnghiem.model.CreateQuestion
import com.example.appthitracnghiem.model.createexam.CreateExamSlotProgress
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.createtest.question.adapter.PositiveQuestionAdapter
import com.example.appthitracnghiem.ui.home.createtest.review.CreateExamViewModel
import com.example.appthitracnghiem.ui.home.createtest.review.FragmentReviewCreateExam
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class FragmentCreateExam : BaseFragment<CreateExamViewModel>() {
    private var _binding: FragmentCreateExamBinding? = null
    private val binding get() = _binding!!

    override fun viewModelStoreOwner(): ViewModelStoreOwner = requireActivity()

    lateinit var positiveQuestionAdapter: PositiveQuestionAdapter
    private var numberQuiz: Int = 0
    private var questionIndex = 0
    private var checkVisibleComplete: Boolean = false
    private var level: Int = 0

    private lateinit var slotProgress: CreateExamSlotProgress

    private var listCheckboxAnswer: ArrayList<CheckBox> = arrayListOf()

    private var listResults: ArrayList<Int> = arrayListOf()

    companion object {
        private const val GALLERY_RED_CODE = 1000
    }

    @SuppressLint("SetTextI18n", "ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.layoutOnClickCreate.setOnTouchListener { _, _ -> true }

        /** Insert checkbox in array **/
        listCheckboxAnswer.add(binding.isAnswer1)
        listCheckboxAnswer.add(binding.isAnswer2)
        listCheckboxAnswer.add(binding.isAnswer3)
        listCheckboxAnswer.add(binding.isAnswer4)

        val form = viewModel.requireForm()
        numberQuiz = form.numberQuestion
        val linearLayoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.recycleListNumber.layoutManager = linearLayoutManager

        /** Create array results **/
        repeat(4) {
            listResults.add(-1)
        }

        /** Danh sách câu nháp trong ViewModel (phạm vi Activity). */
        viewModel.initQuestionSlots(numberQuiz)

        slotProgress = CreateExamSlotProgress(numberQuiz)

        positiveQuestionAdapter = PositiveQuestionAdapter(numberQuiz, requireActivity()) { index ->
            slotProgress.statusAt(index)
        }
        positiveQuestionAdapter.onClickItem = {
            questionIndex = it
        }
        binding.recycleListNumber.adapter = positiveQuestionAdapter

        initUi()
    }

    @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
    private fun initUi() {
        val time = viewModel.requireForm().timeMinutes
        binding.txtTime.text = getString(R.string.format_minutes_suffix, time)

        level = 0
        binding.txtLevel.text = getString(R.string.txtLevelEasy)

        listCheckboxAnswer.forEachIndexed { index, box ->
            box.selectAnswer(index)
        }

        positiveQuestionAdapter.onClickItem = {
            val strLevel: String = binding.txtLevel.text.toString()

            if (strLevel.isEmpty()) {
                Toast.makeText(requireActivity(), getString(R.string.toast_need_question_level), Toast.LENGTH_SHORT)
                    .show()
            } else {
                clearFocusTextView()
                saveExam()
                doEmptyText()
                questionIndex = it

                setTextView()
            }
        }

        /** On Click **/
        binding.nextQuestionCreate.setOnClickListener {
            val strLevel: String = binding.txtLevel.text.toString()

            if (strLevel.isEmpty()) {
                Toast.makeText(requireActivity(), getString(R.string.toast_need_question_level), Toast.LENGTH_SHORT)
                    .show()
            } else {
                clearFocusTextView()
                saveExam()
                doEmptyText()

                visibleCompleteExam()

                if (questionIndex < numberQuiz - 1) {
                    questionIndex++
                    positiveQuestionAdapter.setSelectedIndex(questionIndex)
                }
                binding.recycleListNumber.scrollToPosition(questionIndex)
                setTextView()
            }
        }

        binding.backQuestionCreate.setOnClickListener {
            val strLevel: String = binding.txtLevel.text.toString()

            if (strLevel.isEmpty()) {
                Toast.makeText(requireActivity(), getString(R.string.toast_need_question_level), Toast.LENGTH_SHORT)
                    .show()
            } else {
                clearFocusTextView()
                saveExam()
                if (questionIndex > 0) {
                    questionIndex--
                    doEmptyText()
                    positiveQuestionAdapter.setSelectedIndex(questionIndex)
                }
                binding.recycleListNumber.scrollToPosition(questionIndex)
                setTextView()
            }
        }

        binding.completeCreateTest.setOnClickListener {
            saveExam()
            val fragmentReviewCreateExam = FragmentReviewCreateExam()
            val fm: FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
            fm?.add(R.id.changeIdCreateExam, fragmentReviewCreateExam)
                ?.addToBackStack(null)?.commit()
        }

        binding.backCreateTest.setOnClickListener {
            activity?.finish()
        }

        binding.addCoverImageCreateTest.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            startActivityForResult(intent, GALLERY_RED_CODE)
        }

        binding.menuCreateTestAct.setOnClickListener {
            binding.menuCreateTestAct.showMenuCreate(R.layout.popup_create_test_activity)
        }

        binding.createLevel.setOnClickListener {
            binding.createLevel.showMenuLevel()
        }
    }

    private fun clearFocusTextView() {
        binding.questionCreate.clearFocus()
        binding.answerCreate1.clearFocus()
        binding.answerCreate2.clearFocus()
        binding.answerCreate3.clearFocus()
        binding.answerCreate4.clearFocus()
    }

    private fun setTextView() {
        val q = viewModel.questions.getOrNull(questionIndex) ?: return
        if (q.question_title.isNotEmpty()) {
            binding.questionCreate.setText(q.question_title)
            binding.answerCreate1.setText(q.answer_list.getOrNull(0)?.content)
            binding.answerCreate2.setText(q.answer_list.getOrNull(1)?.content)
            binding.answerCreate3.setText(q.answer_list.getOrNull(2)?.content)
            binding.answerCreate4.setText(q.answer_list.getOrNull(3)?.content)
            val answerList = q.answer_list
            for (i in answerList.indices) {
                if (answerList[i]?.type == 1) {
                    listCheckboxAnswer[i].isChecked = true
                }
            }
        }
    }

    private fun visibleCompleteExam() {
        checkVisibleComplete = slotProgress.allSlotsComplete()
        if (checkVisibleComplete) {
            binding.completeCreateTest.visibility = View.VISIBLE
        } else {
            binding.completeCreateTest.visibility = View.INVISIBLE
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun saveExam() {
        binding.scrollCreateExam.post {
            binding.scrollCreateExam.fling(0)
            binding.scrollCreateExam.smoothScrollTo(0, 0)
        }
        val question = binding.questionCreate.text.toString()
        val answer1 = binding.answerCreate1.text.toString()
        val answer2 = binding.answerCreate2.text.toString()
        val answer3 = binding.answerCreate3.text.toString()
        val answer4 = binding.answerCreate4.text.toString()

        val answers = mutableListOf<CreateAnswer?>()

        if (question.isEmpty()) {
            slotProgress.markIncomplete(questionIndex)
            positiveQuestionAdapter.notifyDataSetChanged()
        } else {
            slotProgress.markComplete(questionIndex)
            positiveQuestionAdapter.notifyDataSetChanged()
        }

        answers.add(CreateAnswer(answer1, "", 1, "", listResults[0]))
        answers.add(CreateAnswer(answer2, "", 2, "", listResults[1]))
        answers.add(CreateAnswer(answer3, "", 3, "", listResults[2]))
        answers.add(CreateAnswer(answer4, "", 4, "", listResults[3]))

        viewModel.setQuestionAt(
            questionIndex,
            CreateQuestion(answers, "", "", level, questionIndex + 1, question),
        )
    }

    internal fun visibleComplete(visible: Boolean) {
        if (visible) {
            binding.layoutOnClickCreate.visibility = View.GONE
        } else {
            binding.layoutOnClickCreate.visibility = View.VISIBLE
        }
    }

    private fun doEmptyText() {
        binding.questionCreate.setText("")
        binding.answerCreate1.setText("")
        binding.answerCreate2.setText("")
        binding.answerCreate3.setText("")
        binding.answerCreate4.setText("")
        setUnSelectAnswer()
    }

    private fun CheckBox.selectAnswer(index: Int) {
        this.setOnCheckedChangeListener { _, b ->
            if (b) {
                setUnSelectAnswer()
                this.isChecked = true
                listResults[index] = 1
            }
        }
    }

    private fun setUnSelectAnswer() {
        listCheckboxAnswer.forEachIndexed { index, b ->
            b.isChecked = false
            listResults[index] = 0
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == AppCompatActivity.RESULT_OK) {
            if (requestCode == GALLERY_RED_CODE) {
                binding.imageCoverCreateTest.setImageURI(data?.data)
            }
        }
    }

    private fun View.showMenuCreate(layout: Int) {
        val popUpView: View = View.inflate(requireActivity(), layout, null)

        val width = ViewGroup.LayoutParams.WRAP_CONTENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        val focusable = true

        val popupWindow = PopupWindow(popUpView, width, height, focusable)
        popupWindow.showAsDropDown(this, 0, -30, Gravity.BOTTOM)
    }

    @SuppressLint("SetTextI18n")
    private fun View.showMenuLevel() {
        val popUpView: View = View.inflate(requireActivity(), R.layout.popup_level, null)

        val width = ViewGroup.LayoutParams.WRAP_CONTENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        val focusable = true

        val popupWindow = PopupWindow(popUpView, width, height, focusable)
        popupWindow.showAsDropDown(this, 0, -30, Gravity.BOTTOM)

        /** init view **/
        val easy: TextView = popUpView.findViewById(R.id.levelEasy)
        val medium: TextView = popUpView.findViewById(R.id.levelMedium)
        val hard: TextView = popUpView.findViewById(R.id.levelHard)

        /** On click view **/
        easy.setOnClickListener {
            level = 0
            binding.txtLevel.text = getString(R.string.txtLevelEasy)
            popupWindow.dismiss()
        }

        medium.setOnClickListener {
            level = 1
            binding.txtLevel.text = getString(R.string.txtMedium)
            popupWindow.dismiss()
        }

        hard.setOnClickListener {
            level = 2
            binding.txtLevel.text = getString(R.string.txtLevelHard)
            popupWindow.dismiss()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCreateExamBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onFragmentBack(): Boolean {
        return true
    }
}