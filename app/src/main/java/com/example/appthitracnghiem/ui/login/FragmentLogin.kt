package com.example.appthitracnghiem.ui.login

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Paint
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.FragmentTransaction
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.databinding.FragmentLoginBinding
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.HomeActivity
import com.example.appthitracnghiem.ui.login.forgetpassword.FragmentForgetPassword
import com.example.appthitracnghiem.ui.register.RegisterActivity
import com.example.appthitracnghiem.utils.PreferenceKey
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class FragmentLogin : BaseFragment<LoginViewModel>() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    var checkSave: Boolean = false
    lateinit var strEmail: String
    lateinit var strPassword: String
    lateinit var progressDialog: ProgressDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressDialog = ProgressDialog(requireActivity())

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireActivity())
        editor = sharedPreferences.edit()

        initUi()
    }

    override fun bindData() {
        super.bindData()
        viewModel.loadingLiveData.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                progressDialog.setMessage("Please wait ...")
                progressDialog.show()
            } else {
                progressDialog.dismiss()
            }
        }

        viewModel.successLoginLiveData.observe(viewLifecycleOwner) {
            val intent = Intent(
                requireActivity(),
                HomeActivity::class.java
            )
            intent.flags =
                Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            saveAccount(strEmail, strPassword);
        }

        viewModel.validateLiveData.observe(viewLifecycleOwner) { model ->
            if (model.isValidate) {
                binding.warningLogin.visibility = View.GONE
            } else {
                setNote(model.resMsgError, model.resColorError)
            }
        }
    }

    /** set font*/
    private fun setText() {
        binding.forgetPassword.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        val semibold: Typeface? =
            ResourcesCompat.getFont(requireActivity(), R.font.svn_gilroy_semibold)
        binding.textGoogle.typeface = semibold
        binding.textFacebook.typeface = semibold
        binding.textApple.typeface = semibold
    }

    @SuppressLint("ResourceAsColor")
    private fun initUi() {
        checkSaveAccount()

        binding.hidePasswordLogin.setOnClickListener {
            hidePassword(binding.passwordLogin, binding.hidePasswordLogin)
        }

        binding.forgetPassword.setOnClickListener {
            val fragmentForgetpassword: FragmentForgetPassword = FragmentForgetPassword()
            val fm: FragmentTransaction =
                requireActivity().supportFragmentManager.beginTransaction()
            fm.setCustomAnimations(
                R.anim.anim_translate_enter_right,
                R.anim.anim_translate_exit_left,
                R.anim.anim_translate_enter_left,
                R.anim.anim_translate_exit_right
            )
            fm.addToBackStack("Fragment_ForgetPassword")
            fm.replace(R.id.loginContainerID, fragmentForgetpassword).commit()
        }

        binding.registerNow.setOnClickListener {
            val intent = Intent(activity, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.login.setOnClickListener {
            strEmail = binding.edtEnterEmailLogin.text.toString().trim()
            strPassword = binding.passwordLogin.text.toString().trim()
            viewModel.login(strEmail, strPassword)
        }

        binding.google.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.data =
                Uri.parse("https://accounts.google.com/v3/signin/identifier?dsh=S-620025444%3A1673854670857931&authuser=0&continue=http%3A%2F%2Fsupport.google.com%2Fmail%2Fanswer%2F8494%3Fhl%3Dvi%26co%3DGENIE.Platform%253DDesktop&ec=GAlAdQ&hl=vi&flowName=GlifWebSignIn&flowEntry=AddSession")
            startActivity(intent)
        }

        binding.facebook.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.data = Uri.parse("https://www.facebook.com")
            startActivity(intent)
        }

        setText()
    }

    private fun saveAccount(email: String, password: String) {
        if (binding.checkForgetPassword.isChecked) {
            viewModel.mPreferenceUtil.defaultPref()
                .edit().putBoolean(PreferenceKey.SAVE_ACCOUNT, true)
                .apply()
            viewModel.mPreferenceUtil.defaultPref()
                .edit().putString(PreferenceKey.USER_EMAIL,email)
                .apply()
            viewModel.mPreferenceUtil.defaultPref()
                .edit().putString(PreferenceKey.USER_PASSWORD,password)
                .apply()
        } else {
            viewModel.mPreferenceUtil.defaultPref()
                .edit().putBoolean(PreferenceKey.SAVE_ACCOUNT, false)
                .apply()
            viewModel.mPreferenceUtil.defaultPref()
                .edit().putString(PreferenceKey.USER_EMAIL,"")
                .apply()
            viewModel.mPreferenceUtil.defaultPref()
                .edit().putString(PreferenceKey.USER_PASSWORD,"")
                .apply()
        }
    }

    private fun checkSaveAccount() {
        checkSave = viewModel.mPreferenceUtil.defaultPref()
            .getBoolean(PreferenceKey.SAVE_ACCOUNT,false)
        strEmail = viewModel.mPreferenceUtil.defaultPref()
            .getString(PreferenceKey.USER_EMAIL,"").toString()
        strPassword = viewModel.mPreferenceUtil.defaultPref()
            .getString(PreferenceKey.USER_PASSWORD,"").toString()


        binding.edtEnterEmailLogin.setText(strEmail)
        binding.passwordLogin.setText(strPassword)
        binding.checkForgetPassword.isChecked = true
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

    private fun setNote(string: Int, color: Int) {
        val circle: Animation = AnimationUtils.loadAnimation(requireActivity(), R.anim.anim_shake)
        binding.warningLogin.text = getString(string)
        binding.warningLogin.setTextColor(resources.getColor(color))
        binding.warningLogin.visibility = View.VISIBLE
        binding.warningLogin.startAnimation(circle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
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