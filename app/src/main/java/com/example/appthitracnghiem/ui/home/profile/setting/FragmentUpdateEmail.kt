package com.example.appthitracnghiem.ui.home.profile.setting

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_setting_new_password.*
import kotlinx.android.synthetic.main.fragment_update_email.*

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentUpdateEmail.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentUpdateEmail : BaseFragment<EmptyViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        click()

        setText()
    }

    private fun click() {
        backUpdateEmail.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    /** set font*/
    private fun setText() {
        val semibold: Typeface? =
            ResourcesCompat.getFont(requireActivity(), R.font.svn_gilroy_semibold)
        txtCapNhatEmail.typeface = semibold
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_email, container, false)
    }

    override fun onFragmentBack(): Boolean {
        return false
        }
}