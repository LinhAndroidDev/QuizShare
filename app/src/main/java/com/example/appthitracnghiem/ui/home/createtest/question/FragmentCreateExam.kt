package com.example.appthitracnghiem.ui.home.createtest.question

import android.content.Intent
import android.graphics.Rect
import android.graphics.Typeface
import android.os.Bundle
import android.provider.MediaStore
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.PositiveQuestion
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.createtest.manager.FragmentManageExam
import com.example.appthitracnghiem.ui.home.createtest.question.adapter.PositiveQuestionAdapter
import com.example.appthitracnghiem.utils.PreferenceKey
import kotlinx.android.synthetic.main.activity_create_test.*
import kotlinx.android.synthetic.main.fragment_create_exam.*

class FragmentCreateExam : BaseFragment<EmptyViewModel>() {
    lateinit var listPositiveQuestion: ArrayList<PositiveQuestion>
    lateinit var positiveQuestionAdapter: PositiveQuestionAdapter
    private val GALLERY_RED_CODE: Int = 1000
    private var isComplete: Boolean = false


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val numberQuiz = viewModel.mPreferenceUtil.defaultPref()
            .getInt(PreferenceKey.CREATE_NUMBER_QUIZ, 0)

        val linearLayoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        recycleListNumber.layoutManager = linearLayoutManager

        listPositiveQuestion = arrayListOf()
        for (i in 0 until numberQuiz) {
            if(i == 0){
                listPositiveQuestion.add(PositiveQuestion(i + 1,true))
            }else{
                listPositiveQuestion.add(PositiveQuestion(i + 1,null))
            }
        }
        positiveQuestionAdapter = PositiveQuestionAdapter(listPositiveQuestion, requireActivity())
        positiveQuestionAdapter.hasClickItem = {
            listPositiveQuestion[0].isSelect = false
        }
        positiveQuestionAdapter.getPositiveQuestion = {
            if(it == (listPositiveQuestion.size-1)){
                txtContinue.text = "Hoàn thành"
                isComplete = true
            }else{
                txtContinue.text = "Tiếp theo"
                isComplete = false
            }
        }
        recycleListNumber.adapter = positiveQuestionAdapter

        initUi()
    }

    private fun initUi() {
        continueCreateTest.setOnClickListener {
            if(isComplete){
                val fm : FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
                val fragmentManageExam = FragmentManageExam()
                fm?.replace(R.id.changeIdCreateExam, fragmentManageExam)?.addToBackStack(null)
                    ?.commit()
            }
        }

        backCreateTest.setOnClickListener {
            activity?.finish()
        }

        addCoverImageCreateTest.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            startActivityForResult(intent, GALLERY_RED_CODE)
        }

        menuCreateTestAct.setOnClickListener {
            showMenuCreate(
                menuCreateTestAct,
                R.layout.popup_create_test_activity,
                0,
                -30,
                Gravity.BOTTOM
            )
        }

        layoutCreateExam.viewTreeObserver
            .addOnGlobalLayoutListener {
                val r = Rect()
                layoutCreateExam.getWindowVisibleDisplayFrame(r)
                val screenHeight: Int = layoutCreateExam.rootView.height

                // r.bottom is the position above soft keypad or device button.
                // if keypad is shown, the r.bottom is smaller than that before.
                val keypadHeight: Int = screenHeight - r.bottom

                if (keypadHeight > screenHeight * 0.15) {
                    continueCreateTest.visibility = View.GONE
                }else{
                    continueCreateTest.visibility = View.VISIBLE
                }
            }

        setText()
    }

    private fun setText() {
        val semibold: Typeface? = ResourcesCompat.getFont(requireActivity(), R.font.svn_gilroy_semibold)
        txtCreateTest.typeface = semibold
        txtSelectImageCt.typeface = semibold
        txtAddQuestion.typeface = semibold
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == AppCompatActivity.RESULT_OK) {
            if (requestCode == GALLERY_RED_CODE) {
                imageCoverCreateTest.setImageURI(data?.data)
            }
        }
    }

    private fun showMenuCreate(anchor: View, layout: Int, x: Int, y: Int, position: Int) {
        val popUpView: View = View.inflate(requireActivity(), layout, null)

        val width = ViewGroup.LayoutParams.WRAP_CONTENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        val focusable = true

        val popupWindow = PopupWindow(popUpView, width, height, focusable)
        popupWindow.showAsDropDown(anchor, x, y, position)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_exam, container, false)
    }

    override fun onFragmentBack(): Boolean {
        return true
    }
}