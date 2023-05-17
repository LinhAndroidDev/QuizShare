package com.example.appthitracnghiem.ui.home.createtest

import android.content.Intent
import android.graphics.Rect
import android.graphics.Typeface
import android.os.Bundle
import android.provider.MediaStore
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.PositiveQuestion
import com.example.appthitracnghiem.ui.home.createtest.adapter.PositiveQuestionAdapter
import kotlinx.android.synthetic.main.activity_create_test.*

@Suppress("DEPRECATION")
class CreateTestActivity : AppCompatActivity() {
    lateinit var listPositiveQuestion: ArrayList<PositiveQuestion>
    lateinit var positiveQuestionAdapter: PositiveQuestionAdapter
    private val GALLERY_RED_CODE: Int = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_test)

        val numberQuiz = intent.getStringExtra("numberQuiz")?.toInt()

        val linearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycleListNumber.layoutManager = linearLayoutManager

        listPositiveQuestion = arrayListOf()
        for (i in 0 until numberQuiz!!) {
            listPositiveQuestion.add(PositiveQuestion(i + 1))
        }
        positiveQuestionAdapter = PositiveQuestionAdapter(listPositiveQuestion, this)
        recycleListNumber.adapter = positiveQuestionAdapter

        initUi()
    }

    private fun initUi() {
        backCreateTest.setOnClickListener {
            finish()
        }

        addCoverImageCreateTest.setOnClickListener {
            val intent: Intent = Intent(Intent.ACTION_PICK)
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
        val semibold: Typeface? = ResourcesCompat.getFont(this, R.font.svn_gilroy_semibold)
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
        val popUpView: View = View.inflate(this@CreateTestActivity, layout, null)

        val width = ViewGroup.LayoutParams.WRAP_CONTENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        val focusable: Boolean = true

        val popupWindow: PopupWindow = PopupWindow(popUpView, width, height, focusable)
        popupWindow.showAsDropDown(anchor, x, y, position)
    }
}