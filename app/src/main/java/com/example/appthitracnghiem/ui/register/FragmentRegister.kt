package com.example.appthitracnghiem.ui.register

import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.PasswordTransformationMethod
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.FragmentTransaction
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment__create_password.*
import kotlinx.android.synthetic.main.fragment__register.*
import java.text.SimpleDateFormat
import java.util.*

@Suppress("DEPRECATION")
class FragmentRegister : BaseFragment<RegisterViewModel>() {

    private lateinit var progressDialog: ProgressDialog

    private var formatDate = SimpleDateFormat("dd/MM/yyyy", Locale.UK)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressDialog = ProgressDialog(requireActivity())

        initUi()
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
            val fragmentCondition = FragmentCondition()
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

    private fun initUi() {
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

        selectDate.setOnClickListener {

            val getDate = Calendar.getInstance()
            val datePicker = DatePickerDialog(requireActivity(), android.R.style.Theme_Holo_Light_Dialog_MinWidth,DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->

                val selectDate: Calendar = Calendar.getInstance()
                selectDate.set(Calendar.YEAR, year)
                selectDate.set(Calendar.MONTH, month)
                selectDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                edtEnterYearOfBirthRegister.setText(formatDate.format(selectDate.time))

            }, getDate.get(Calendar.YEAR), getDate.get((Calendar.MONTH)), getDate.get(Calendar.DAY_OF_MONTH))
            datePicker.show()
        }

        setText()

    }

    private fun setText() {
        val strText: String = getString(R.string.txtNoteRegister)
        val text = SpannableString(strText)
        val boldStart = StyleSpan(Typeface.BOLD)
        val boldEnd = StyleSpan(Typeface.BOLD)
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