package com.example.appthitracnghiem.ui.home.profile.setting

import android.graphics.Typeface
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment__create_password.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_setting_new_password.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentSettingNewPassword.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentSettingNewPassword : BaseFragment<EmptyViewModel>() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backUpdateNewPassWord.setOnClickListener {
            activity?.onBackPressed()
        }

        setText()

        showUpdatePassWord.setOnClickListener {
            if (passwordUpdate.transformationMethod == PasswordTransformationMethod.getInstance()) {
                passwordUpdate.transformationMethod = null
                showUpdatePassWord.setImageResource(R.drawable.icon_show_password_grey)
            } else if (passwordUpdate.transformationMethod == null) {
                passwordUpdate.transformationMethod = PasswordTransformationMethod.getInstance()
                showUpdatePassWord.setImageResource(R.drawable.icon_hint_grey)
            }
        }

        showRepeatUpdatePassWord.setOnClickListener {
            if (repeatPasswordUpdate.transformationMethod == PasswordTransformationMethod.getInstance()) {
                repeatPasswordUpdate.transformationMethod = null
                showRepeatUpdatePassWord.setImageResource(R.drawable.icon_show_password_grey)
            } else if (repeatPasswordUpdate.transformationMethod == null) {
                repeatPasswordUpdate.transformationMethod =
                    PasswordTransformationMethod.getInstance()
                showRepeatUpdatePassWord.setImageResource(R.drawable.icon_hint_grey)
            }
        }
    }

    /** set font*/
    private fun setText() {
        val semibold: Typeface? =
            ResourcesCompat.getFont(requireActivity(), R.font.svn_gilroy_semibold)
        txtMatKhauMoi.typeface = semibold
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting_new_password, container, false)
    }

    override fun onFragmentBack(): Boolean {
        return false
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentSettingNewPassword.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentSettingNewPassword().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}