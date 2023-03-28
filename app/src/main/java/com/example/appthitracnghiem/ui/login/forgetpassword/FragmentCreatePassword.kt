package com.example.appthitracnghiem.ui.login.forgetpassword

import android.app.ProgressDialog
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.FragmentTransaction
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.login.FragmentLogin
import kotlinx.android.synthetic.main.fragment__create_password.*

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentCreatePassword.newInstance] factory method to
 * create an instance of this fragment.
 */

@Suppress("DEPRECATION")
class FragmentCreatePassword : BaseFragment<EmptyViewModel>() {
    lateinit var email: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle: Bundle = requireArguments()
        email = bundle.getString("email").toString()

        click()
    }

    private fun click() {
        resetPassword.setOnClickListener {
            val password: String = newPasswordCreate.text.toString()
            val progressDialog: ProgressDialog = ProgressDialog(requireActivity())
            progressDialog.setMessage("Đang cập nhật mật khẩu")

            val fragmentLogin: FragmentLogin = FragmentLogin()
            val fm: FragmentTransaction =
                requireActivity().supportFragmentManager.beginTransaction()
            fm.setCustomAnimations(
                R.anim.anim_translate_enter_right,
                R.anim.anim_translate_exit_left,
                R.anim.anim_translate_enter_left,
                R.anim.anim_translate_exit_right
            )
            fm.replace(R.id.loginContainerID, fragmentLogin).commit()
        }

        hidePasswordCreate.setOnClickListener {
            hidePassword(newPasswordCreate, hidePasswordCreate)
        }

        hidePasswordRepeat.setOnClickListener {
            hidePassword(newPasswordRepeat, hidePasswordRepeat)
        }
    }

    private fun hidePassword(password: EditText, hide: ImageView) {
        if (password.transformationMethod == PasswordTransformationMethod.getInstance()) {
            password.transformationMethod = null
            hide.setBackgroundResource(R.drawable.icon_show_password_grey)
        } else if (password.transformationMethod == null) {
            password.transformationMethod = PasswordTransformationMethod.getInstance()
            hide.setBackgroundResource(R.drawable.icon_hint_grey)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__create_password, container, false)
    }

    override fun onFragmentBack(): Boolean {

        return false
    }
}