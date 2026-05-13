package com.example.appthitracnghiem.ui.home.createtest.question

import android.graphics.Rect
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.databinding.ActivityCreateTestBinding
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseActivity
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.createtest.review.CreateExamViewModel
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class CreateTestActivity : BaseActivity<EmptyViewModel>() {
    private lateinit var binding: ActivityCreateTestBinding

    private val createExamViewModel: CreateExamViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindCreateExamFormFromIntent()

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
                if (fm is FragmentCreateExam) {
                    if (keypadHeight > screenHeight * 0.15) {
                        fm.visibleComplete(true)
                    } else {
                        fm.visibleComplete(false)
                    }
                }
            }
    }

    @Suppress("DEPRECATION")
    private fun bindCreateExamFormFromIntent() {
        val state = intent.getSerializableExtra(CreateTestIntentExtras.FORM_STATE) as? CreateExamFormState
            ?: return
        createExamViewModel.bindForm(state)
    }

    private fun replaceFragment(fg: Fragment) {
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
