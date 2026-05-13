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
import com.example.appthitracnghiem.databinding.FragmentRegisterBinding
import com.example.appthitracnghiem.ui.base.BaseFragment
import java.text.SimpleDateFormat
import java.util.*
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class FragmentRegister : BaseFragment<RegisterViewModel>() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

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
                progressDialog.setMessage(getString(R.string.loading_updating_account))
                progressDialog.show()
            } else {
                progressDialog.dismiss()
            }
        }

        viewModel.successRegisterLiveData.observe(viewLifecycleOwner) { _ ->
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
                binding.warningRegister.visibility = View.GONE
            } else {
                setNote(model.resMsgError, model.resColorError)
            }
        }
    }

    private fun initUi() {
        binding.hidePasswordRegister.setOnClickListener {
            hidePassword(binding.passwordRegister, binding.hidePasswordRegister)
        }

        binding.hidePasswordRegisterRepeat.setOnClickListener {
            hidePassword(binding.passwordRegisterRepeat, binding.hidePasswordRegisterRepeat)
        }

        binding.registerAccount.setOnClickListener {
            val strName = binding.edtEnterNameRegister.text.toString().trim()
            val strYearOfBirth = binding.edtEnterYearOfBirthRegister.text.toString().trim()
            val strEmail = binding.edtEnterEmailRegister.text.toString().trim()
            val strPhone = binding.edtPhoneRegister.text.toString().trim()
            val strPassword = binding.passwordRegister.text.toString().trim()
            val strPasswordRepeat = binding.passwordRegisterRepeat.text.toString().trim()

            viewModel.register(
                strEmail,
                strName,
                strPhone,
                strYearOfBirth,
                strPassword,
                strPasswordRepeat
            )
        }

        binding.loginNow.setOnClickListener {
            activity?.finish()
        }

        binding.selectDate.setOnClickListener {

            val getDate = Calendar.getInstance()
            val datePicker = DatePickerDialog(
                requireActivity(), android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                { _, year, month, dayOfMonth ->

                    val selectDate: Calendar = Calendar.getInstance()
                    selectDate.set(Calendar.YEAR, year)
                    selectDate.set(Calendar.MONTH, month)
                    selectDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    binding.edtEnterYearOfBirthRegister.setText(formatDate.format(selectDate.time))

                }, getDate.get(Calendar.YEAR), getDate.get((Calendar.MONTH)), getDate.get(Calendar.DAY_OF_MONTH)
            )
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

        binding.textString.text = text
    }

    fun setNote(string: Int, color: Int) {
        val circle: Animation = AnimationUtils.loadAnimation(requireActivity(), R.anim.anim_shake)
        binding.warningRegister.text = getString(string)
        binding.warningRegister.setTextColor(resources.getColor(color))
        binding.warningRegister.visibility = View.VISIBLE
        binding.warningRegister.startAnimation(circle)
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
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onFragmentBack(): Boolean {

        return true
    }
}