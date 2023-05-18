package com.example.appthitracnghiem.ui.home.createtest.question

import android.content.Intent
import android.graphics.Rect
import android.graphics.Typeface
import android.os.Bundle
import android.provider.MediaStore
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.PositiveQuestion
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseActivity
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.createtest.question.adapter.PositiveQuestionAdapter
import com.example.appthitracnghiem.utils.PreferenceKey
import kotlinx.android.synthetic.main.activity_create_test.*
import kotlinx.android.synthetic.main.fragment_create_exam.*

@Suppress("DEPRECATION")
class CreateTestActivity : BaseActivity<EmptyViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_test)

        replaceFragment(FragmentCreateExam())
    }

    private fun replaceFragment(fg: Fragment){
        val fm: FragmentTransaction = supportFragmentManager.beginTransaction()
        fm.replace(R.id.changeIdCreateExam, fg).addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.changeIdCreateExam)
        if (fragment != null && fragment is BaseFragment<*>) {
            if (fragment.onFragmentBack()) {
                finish()
            } else {
                super.onBackPressed()
            }
        } else {
            super.onBackPressed()
        }
    }
}