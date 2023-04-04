package com.example.appthitracnghiem.ui.register

import android.app.ProgressDialog
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.text.method.PasswordTransformationMethod
import android.text.style.StyleSpan
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.data.remote.ApiClient
import com.example.appthitracnghiem.data.remote.ApiService
import com.example.appthitracnghiem.data.remote.entity.RegisterResponse
import com.example.appthitracnghiem.model.LoginSuccessful
import com.example.appthitracnghiem.model.ViewModelGeneral
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.login.forgetpassword.FragmentCreatePassword
import com.example.appthitracnghiem.utils.Email
import com.skydoves.progressview.progressView
import kotlinx.android.synthetic.main.fragment__create_password.*
import kotlinx.android.synthetic.main.fragment__register.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentRegister.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("DEPRECATION")
class FragmentRegister : BaseFragment<RegisterViewModel>() {
    lateinit var progressDialog: ProgressDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressDialog = ProgressDialog(requireActivity())

        click()

        setText()
    }

    override fun bindData() {
        super.bindData()

        viewModel.loadingLiveData.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                progressDialog.setMessage("Đang cập nhật tài khoản")
                progressDialog.show()
            } else {
                progressDialog.dismiss()
            }
        }

        viewModel.successRegisterLiveData.observe(viewLifecycleOwner) { isSuccess ->
            val fragmentCondition: FragmentCondition = FragmentCondition()
            val fm: FragmentTransaction =
                requireActivity().supportFragmentManager.beginTransaction()
            fm.setCustomAnimations(
                R.anim.anim_translate_enter_right,
                R.anim.anim_translate_exit_left,
                R.anim.anim_translate_enter_left,
                R.anim.anim_translate_exit_right
            )
            fm.addToBackStack("Fragment_Condition")
            fm.replace(R.id.registerContainerID, fragmentCondition).commit()
        }

        viewModel.validateLiveData.observe(viewLifecycleOwner) { model ->
            if (model.isValidate) {
                warningRegister.visibility = View.GONE
            } else {
                setNote(model.resMsgError, model.resColorError)
            }
        }
    }

    private fun click() {
        hidePasswordRegister.setOnClickListener {
            hidePassword(passwordRegister, hidePasswordRegister)
        }

        hidePasswordRegisterRepeat.setOnClickListener {
            hidePassword(passwordRegisterRepeat, hidePasswordRegisterRepeat)
        }

        registerAccount.setOnClickListener {
            val strName = edtEnterNameRegister.text.toString().trim()
            val strYearOfBirth = edtEnterYearOfBirthRegister.text.toString().trim()
            val strEmail = edtEnterEmailRegister.text.toString().trim()
            val strPhone = edtPhoneRegister.text.toString().trim()
            val strPassword = passwordRegister.text.toString().trim()
            val strPasswordRepeat = passwordRegisterRepeat.text.toString().trim()

            viewModel.register(
                strEmail,
                strName,
                strPhone,
                strYearOfBirth,
                strPassword,
                strPasswordRepeat
            )
        }

        loginNow.setOnClickListener {
            activity?.finish()
        }
    }

    private fun setText() {
        val strText: String = getString(R.string.txtNoteRegister)
        val text: SpannableString = SpannableString(strText)
        val boldStart: StyleSpan = StyleSpan(Typeface.BOLD)
        val boldEnd: StyleSpan = StyleSpan(Typeface.BOLD)
        text.setSpan(boldStart, 43, 53, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        text.setSpan(boldEnd, 57, 74, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        textString.text = text
    }

    fun setNote(string: Int, color: Int) {
        val circle: Animation = AnimationUtils.loadAnimation(requireActivity(), R.anim.anim_shake)
        warningRegister.text = getString(string)
        warningRegister.setTextColor(resources.getColor(color))
        warningRegister.visibility = View.VISIBLE
        warningRegister.startAnimation(circle)
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
        return inflater.inflate(R.layout.fragment__register, container, false)
    }

    override fun onFragmentBack(): Boolean {

        return true
    }
}