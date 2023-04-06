package com.example.appthitracnghiem.ui.login.forgetpassword

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment__forget_password.*

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentForgetPassword.newInstance] factory method to
 * create an instance of this fragment.
 */

class FragmentForgetPassword : BaseFragment<ForgetPasswordViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        click()
    }

    override fun bindData() {
        super.bindData()

    }

    private fun click() {
        sendTo.setOnClickListener {
            val edtEmail: String = emailForgetPassword.text.toString()
            val bundle: Bundle = Bundle()
            bundle.putString("email", edtEmail)
            val fragmentCreatepassword: FragmentCreatePassword = FragmentCreatePassword()
            val fm: FragmentTransaction =
                requireActivity().supportFragmentManager.beginTransaction()
            fm.setCustomAnimations(
                R.anim.anim_translate_enter_right,
                R.anim.anim_translate_exit_left,
                R.anim.anim_translate_enter_left,
                R.anim.anim_translate_exit_right
            )
            fm.addToBackStack("Fragment_CreatePassword")
            fm.replace(R.id.loginContainerID, fragmentCreatepassword).commit()
            fragmentCreatepassword.arguments = bundle
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        Log.d("ForgetPassword", "onCreateView")
        return inflater.inflate(R.layout.fragment__forget_password, container, false)
    }

    override fun onFragmentBack(): Boolean {

        return false
    }
}