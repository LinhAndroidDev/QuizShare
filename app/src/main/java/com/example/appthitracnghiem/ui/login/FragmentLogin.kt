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
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.HomeActivity
import com.example.appthitracnghiem.ui.login.forgetpassword.FragmentForgetPassword
import com.example.appthitracnghiem.ui.register.RegisterActivity
import com.example.appthitracnghiem.utils.PreferenceKey
import kotlinx.android.synthetic.main.fragment__create_password.*
import kotlinx.android.synthetic.main.fragment__login.*

@Suppress("DEPRECATION")
class FragmentLogin : BaseFragment<LoginViewModel>() {
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

        checkSaveAccount()

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
            val t = viewModel.mPreferenceUtil.defaultPref()
                .getString(PreferenceKey.USER_NAME,"")
        }

        viewModel.validateLiveData.observe(viewLifecycleOwner) { model ->
            if (model.isValidate) {
                warningLogin.visibility = View.GONE
            } else {
                setNote(model.resMsgError, model.resColorError)
            }
        }
    }

    /** set font*/
    private fun setText() {
        forgetPassword.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        val semibold: Typeface? =
            ResourcesCompat.getFont(requireActivity(), R.font.svn_gilroy_semibold)
        textGoogle.typeface = semibold
        textFacebook.typeface = semibold
        textApple.typeface = semibold
    }

    @SuppressLint("ResourceAsColor")
    private fun initUi() {
        hidePasswordLogin.setOnClickListener {
            hidePassword(passwordLogin, hidePasswordLogin)
        }

        forgetPassword.setOnClickListener {
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

        registerNow.setOnClickListener {
            val intent = Intent(activity, RegisterActivity::class.java)
            startActivity(intent)
        }

        login.setOnClickListener {
            strEmail = edtEnterEmailLogin.text.toString().trim()
            strPassword = passwordLogin.text.toString().trim()
            viewModel.login(strEmail, strPassword)
        }

        google.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.data =
                Uri.parse("https://accounts.google.com/v3/signin/identifier?dsh=S-620025444%3A1673854670857931&authuser=0&continue=http%3A%2F%2Fsupport.google.com%2Fmail%2Fanswer%2F8494%3Fhl%3Dvi%26co%3DGENIE.Platform%253DDesktop&ec=GAlAdQ&hl=vi&flowName=GlifWebSignIn&flowEntry=AddSession")
            startActivity(intent)
        }

        facebook.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.data = Uri.parse("https://www.facebook.com")
            startActivity(intent)
        }

        setText()
    }

    private fun saveAccount(email: String, password: String) {
        if (checkForgetPassword.isChecked) {
            editor.putBoolean("checkSave", true)
            editor.commit()
            editor.putString("email", email)
            editor.commit()
            editor.putString("password", password)
            editor.commit()
        } else {
            editor.putBoolean("checkSave", false)
            editor.commit()
            editor.putString("email", "")
            editor.commit()
            editor.putString("password", "")
            editor.commit()
        }
    }

    private fun checkSaveAccount() {
        checkSave = sharedPreferences.getBoolean("checkSave", false)
        strEmail = sharedPreferences.getString("email", "").toString()
        strPassword = sharedPreferences.getString("password", "").toString()

        edtEnterEmailLogin.setText(strEmail)
        passwordLogin.setText(strPassword)
        checkForgetPassword.isChecked = true
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
        warningLogin.text = getString(string)
        warningLogin.setTextColor(resources.getColor(color))
        warningLogin.visibility = View.VISIBLE
        warningLogin.startAnimation(circle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__login, container, false)
    }

    override fun onFragmentBack(): Boolean {
        return true
    }
}