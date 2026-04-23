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
import com.example.appthitracnghiem.databinding.ActivityCreateTestBinding
import com.example.appthitracnghiem.model.PositiveQuestion
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseActivity
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.createtest.question.adapter.PositiveQuestionAdapter
import com.example.appthitracnghiem.utils.PreferenceKey

@Suppress("DEPRECATION")
class CreateTestActivity : BaseActivity<EmptyViewModel>() {
    private lateinit var binding: ActivityCreateTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(FragmentCreateExam())

        /** Check keyboard show **/
        binding.changeIdCreateExam.viewTreeObserver
            .addOnGlobalLayoutListener {
                val r = Rect()
                binding.changeIdCreateExam.getWindowVisibleDisplayFrame(r)
                val screenHeight: Int = binding.changeIdCreateExam.rootView.height

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