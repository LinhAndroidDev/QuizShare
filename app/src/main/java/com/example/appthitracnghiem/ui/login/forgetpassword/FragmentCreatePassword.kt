package com.example.appthitracnghiem.ui.login.forgetpassword

import android.app.ProgressDialog
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.databinding.FragmentCreatePasswordBinding
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.login.FragmentLogin
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class FragmentCreatePassword : BaseFragment<EmptyViewModel>() {
    private var _binding: FragmentCreatePasswordBinding? = null
    private val binding get() = _binding!!
    lateinit var email: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle: Bundle = requireArguments()
        email = bundle.getString("email").toString()

        val uri = activity?.intent?.data
        if (uri != null) {
            val path = uri.toString()
            Toast.makeText(requireActivity(), getString(R.string.toast_debug_data_path, path), Toast.LENGTH_SHORT)
                .show()
        }

        initUi()
    }

    private fun initUi() {
        binding.resetPassword.setOnClickListener {
            val progressDialog = ProgressDialog(requireActivity())
            progressDialog.setMessage(getString(R.string.loading_updating_password))

            val fragmentLogin = FragmentLogin()
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

        binding.hidePasswordCreate.setOnClickListener {
            hidePassword(binding.newPasswordCreate, binding.hidePasswordCreate)
        }

        binding.hidePasswordRepeat.setOnClickListener {
            hidePassword(binding.newPasswordRepeat, binding.hidePasswordRepeat)
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
    ): View {
        _binding = FragmentCreatePasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onFragmentBack(): Boolean {

        return false
    }
}