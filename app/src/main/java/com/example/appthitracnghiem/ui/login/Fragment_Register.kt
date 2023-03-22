package com.example.appthitracnghiem.ui.login

import android.app.ProgressDialog
import android.graphics.Typeface
import android.os.Bundle
import android.os.CountDownTimer
import android.text.SpannableString
import android.text.Spanned
import android.text.method.PasswordTransformationMethod
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.base.api.ApiService
import com.example.appthitracnghiem.model.Email
import com.example.appthitracnghiem.model.LoginSuccessful
import com.example.appthitracnghiem.model.ViewModelGeneral
import com.example.appthitracnghiem.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment__register.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment_Register.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("DEPRECATION")
class Fragment_Register : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var viewModelGeneral: ViewModelGeneral

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelGeneral = ViewModelProvider(requireActivity())[ViewModelGeneral::class.java]

        registerAccount.setOnClickListener {
            register()
        }

        setText()

        loginNow.setOnClickListener {
            activity?.finish()
        }

        hidePassword()
    }

    private fun hidePassword() {
        hidePasswordRegister.setOnClickListener {
            if (passwordRegister.transformationMethod == PasswordTransformationMethod.getInstance()) {
                passwordRegister.transformationMethod = null
                hidePasswordRegister.setBackgroundResource(R.drawable.icon_show_password_grey)
            } else if (passwordRegister.transformationMethod == null) {
                passwordRegister.transformationMethod = PasswordTransformationMethod.getInstance()
                hidePasswordRegister.setBackgroundResource(R.drawable.icon_hint_grey)
            }
        }

        hidePasswordRegisterRepeat.setOnClickListener {
            if (passwordRegisterRepeat.transformationMethod == PasswordTransformationMethod.getInstance()) {
                passwordRegisterRepeat.transformationMethod = null
                hidePasswordRegisterRepeat.setBackgroundResource(R.drawable.icon_show_password_grey)
            } else if (passwordRegisterRepeat.transformationMethod == null) {
                passwordRegisterRepeat.transformationMethod =
                    PasswordTransformationMethod.getInstance()
                hidePasswordRegisterRepeat.setBackgroundResource(R.drawable.icon_hint_grey)
            }
        }
    }

    private fun register() {
        val progressDialog: ProgressDialog = ProgressDialog(requireActivity())
        progressDialog.setMessage("Đang cập nhật tài khoản")
        val strName = edtEnterNameRegister.text.toString().trim()
        val strEmail = edtEnterEmailRegister.text.toString().trim()
        val strPassword = passwordRegister.text.toString().trim()
        val strPasswordRepeat = passwordRegisterRepeat.text.toString().trim()

        if (strName.isEmpty() || strEmail.isEmpty() || strPassword.isEmpty() || strPasswordRepeat.isEmpty()) {
            setNote(R.string.txt_notification_register, R.color.color_green)
            return
        }
        val email: Email = Email(strEmail, strPassword)
        if (!email.isValidEmail()) {
            setNote(R.string.txt_warning_login, R.color.color_red)
            return
        }
        if (!email.isPassword()) {
            setNote(R.string.txt_warning_password, R.color.color_red)
            return
        }
        if (strPassword != strPasswordRepeat) {
            setNote(R.string.txtEnterRepeatPassword, R.color.color_red)
            return
        }
        progressDialog.show()
        val requestBodyStrEmail: RequestBody =
            RequestBody.create("multipart/from-data".toMediaTypeOrNull(), strEmail)
        val requestBodyStrPassword: RequestBody = RequestBody.create(
            "multipart/from-data".toMediaTypeOrNull(),
            strPassword
        )

        viewModelGeneral.postRetrofit.create(ApiService::class.java)
            .registerUser(requestBodyStrEmail, requestBodyStrPassword)
            .enqueue(object : retrofit2.Callback<LoginSuccessful> {
                override fun onResponse(
                    call: Call<LoginSuccessful>,
                    response: Response<LoginSuccessful>,
                ) {
                    progressDialog.dismiss()
                    if (response.isSuccessful) {
                        when (response.body()?.status) {
                            0 -> {
                                val fragmentCondition: Fragment_Condition =
                                    Fragment_Condition()
                                val fm: FragmentTransaction =
                                    requireActivity().supportFragmentManager.beginTransaction()
                                fm.addToBackStack("Fragment_Register")
                                fm.replace(
                                    R.id.registerContainerID,
                                    fragmentCondition
                                ).commit()
                            }
                            2 -> {
                                Toast.makeText(
                                    requireActivity(),
                                    "Tài khoản đã tồn tại",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            else -> {
                                Toast.makeText(
                                    requireActivity(),
                                    "Lỗi",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }

                override fun onFailure(
                    call: Call<LoginSuccessful>,
                    t: Throwable
                ) {
                    progressDialog.dismiss()
                    Toast.makeText(
                        requireActivity(),
                        "Lỗi kết nối Server",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
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
        return inflater.inflate(R.layout.fragment__register, container, false)
    }

    override fun onFragmentBack(): Boolean {

        return true
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragment_Register.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment_Register().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}