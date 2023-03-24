package com.example.appthitracnghiem.ui.home.profile.setting

import android.graphics.Typeface
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment__create_password.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_setting_new_password.*

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentSettingNewPassword.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentSettingNewPassword : BaseFragment<EmptyViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setText()

        click()
    }

    private fun click() {
        backUpdateNewPassWord.setOnClickListener {
            activity?.onBackPressed()
        }

        showUpdatePassWord.setOnClickListener {
            hidePassword(passwordUpdate,showUpdatePassWord)
        }

        showRepeatUpdatePassWord.setOnClickListener {
            hidePassword(repeatPasswordUpdate,showRepeatUpdatePassWord)
        }
    }

    /** set font*/
    private fun setText() {
        val semibold: Typeface? =
            ResourcesCompat.getFont(requireActivity(), R.font.svn_gilroy_semibold)
        txtMatKhauMoi.typeface = semibold
    }

    private fun hidePassword(password: EditText, hide: ImageView) {
        if (password.transformationMethod == PasswordTransformationMethod.getInstance()) {
            password.transformationMethod = null
            hide.setBackgroundResource(R.drawable.icon_show_password_grey)
        } else if (newPasswordCreate.transformationMethod == null) {
            password.transformationMethod = PasswordTransformationMethod.getInstance()
            hide.setBackgroundResource(R.drawable.icon_hint_grey)
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
}