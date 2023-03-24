package com.example.appthitracnghiem.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment__condition.*

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentCondition.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentCondition : BaseFragment<EmptyViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        click()
    }

    private fun click() {
        backLogin.setOnClickListener {
            activity?.finish()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__condition, container, false)
    }

    override fun onFragmentBack(): Boolean {

        return false
    }
}