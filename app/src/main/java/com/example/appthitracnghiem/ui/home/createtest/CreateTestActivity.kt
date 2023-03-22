package com.example.appthitracnghiem.ui.home.createtest

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.provider.MediaStore
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.home.createtest.adapter.PositiveQuestionAdapter
import com.example.appthitracnghiem.model.PositiveQuestion
import kotlinx.android.synthetic.main.activity_create_test.*
import kotlinx.android.synthetic.main.fragment_create_test.*

@Suppress("DEPRECATION")
class CreateTestActivity : AppCompatActivity() {
    lateinit var listPositiveQuestion : ArrayList<PositiveQuestion>
    lateinit var positiveQuestionAdapter : PositiveQuestionAdapter
    private val GALLERY_RED_CODE : Int = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_test)

        backCreateTest.setOnClickListener {
            finish()
        }

        setText()

        val linearLayoutManager : LinearLayoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        recycleListNumber.layoutManager = linearLayoutManager

        listPositiveQuestion = arrayListOf()
        listPositiveQuestion.add(PositiveQuestion(1))
        listPositiveQuestion.add(PositiveQuestion(2))
        listPositiveQuestion.add(PositiveQuestion(3))
        listPositiveQuestion.add(PositiveQuestion(4))
        listPositiveQuestion.add(PositiveQuestion(5))
        listPositiveQuestion.add(PositiveQuestion(6))
        listPositiveQuestion.add(PositiveQuestion(7))
        listPositiveQuestion.add(PositiveQuestion(8))
        listPositiveQuestion.add(PositiveQuestion(9))
        listPositiveQuestion.add(PositiveQuestion(10))
        listPositiveQuestion.add(PositiveQuestion(11))
        listPositiveQuestion.add(PositiveQuestion(12))
        listPositiveQuestion.add(PositiveQuestion(13))
        listPositiveQuestion.add(PositiveQuestion(14))
        listPositiveQuestion.add(PositiveQuestion(15))

        positiveQuestionAdapter = PositiveQuestionAdapter(listPositiveQuestion,this)
        recycleListNumber.adapter = positiveQuestionAdapter

        addCoverImageCreateTest.setOnClickListener {
            val intent : Intent = Intent(Intent.ACTION_PICK)
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            startActivityForResult(intent,GALLERY_RED_CODE)
        }

        menuCreateTestAct.setOnClickListener {
            showMenuCreate(menuCreateTestAct,R.layout.popup_create_test_activity,0,-30,Gravity.BOTTOM)
        }
    }

    private fun setText() {
        val semibold : Typeface? = ResourcesCompat.getFont(this,R.font.svn_gilroy_semibold)
        txtCreateTest.typeface = semibold
        txtSelectImageCt.typeface = semibold
        txtAddQuestion.typeface = semibold
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == AppCompatActivity.RESULT_OK){
            if(requestCode == GALLERY_RED_CODE){
                imageCoverCreateTest.setImageURI(data?.data)
            }
        }
    }

    private fun showMenuCreate(anchor: View, layout : Int, x : Int, y : Int, position : Int) {
        val popUpView : View = View.inflate(this@CreateTestActivity,layout,null)

        val width = ViewGroup.LayoutParams.WRAP_CONTENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        val focusable : Boolean = true

        val popupWindow : PopupWindow = PopupWindow(popUpView,width,height,focusable)
        popupWindow.showAsDropDown(anchor, x, y, position)
    }
}