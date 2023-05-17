package com.example.appthitracnghiem.ui.exercise.exercise.answer

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_answer.*

@Suppress("DEPRECATION")
class FragmentAnswer : BaseFragment<EmptyViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        answer1.setBackgroundResource(R.drawable.bg_answer_fail)
//        answer3.setBackgroundResource(R.drawable.select_text_view)

        initUi()
    }

    private fun setStatusBar() {
        val window: Window? = activity?.window
        window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window?.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window?.statusBarColor = ContextCompat.getColor(requireActivity(), R.color.backgroundIntro)

        val decorView = window?.decorView //set status background black

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            decorView?.systemUiVisibility =
                decorView?.systemUiVisibility?.and(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv())!!
        } //set status text  light
    }

    private fun initUi() {

        setStatusBar()

        backAnswer.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    override fun onFragmentBack(): Boolean {
        return false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_answer, container, false)
    }
}