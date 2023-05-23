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

        /** Check keyboard show **/
        changeIdCreateExam.viewTreeObserver
            .addOnGlobalLayoutListener {
                val r = Rect()
                changeIdCreateExam.getWindowVisibleDisplayFrame(r)
                val screenHeight: Int = changeIdCreateExam.rootView.height

                // r.bottom is the position above soft keypad or device button.
                // if keypad is shown, the r.bottom is smaller than that before.
                val keypadHeight: Int = screenHeight - r.bottom

                val fm: Fragment? = supportFragmentManager.findFragmentById(R.id.changeIdCreateExam)
                if(fm is FragmentCreateExam){
                    if (keypadHeight > screenHeight * 0.15) {
                        fm.visibleComplete(true)
                    }else{
                        fm.visibleComplete(false)
                    }
                }
            }
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