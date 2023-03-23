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
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.ViewModelGeneral
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.home.HomeActivity
import com.example.appthitracnghiem.ui.login.forgetpassword.FragmentForgetPassword
import com.example.appthitracnghiem.ui.login_need_refactor.RegisterActivity
import kotlinx.android.synthetic.main.fragment__login.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentLogin.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("DEPRECATION")
class FragmentLogin : BaseFragment<LoginViewModel>() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var viewModelGeneral: ViewModelGeneral
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    var checkSave: Boolean = false
    lateinit var strEmail: String
    lateinit var strPassword: String
    lateinit var progressDialog: ProgressDialog


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressDialog = ProgressDialog(requireActivity())
        viewModelGeneral = ViewModelProvider(requireActivity())[ViewModelGeneral::class.java]

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireActivity())
        editor = sharedPreferences.edit()

        checkSaveAccount()

        hidePasswordLogin.setOnClickListener {
            hidePassword()
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

        clickLogin()

        setText()
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
    private fun clickLogin() {
        registerNow.setOnClickListener {
            val intent: Intent = Intent(activity, RegisterActivity::class.java)
            startActivity(intent)
        }

        login.setOnClickListener {
            strEmail = edtEnterEmailLogin.text.toString().trim()
            strPassword = passwordLogin.text.toString().trim()
            viewModel.login(strEmail, strPassword)
        }

        google.setOnClickListener {
            val intent: Intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.data =
                Uri.parse("https://accounts.google.com/v3/signin/identifier?dsh=S-620025444%3A1673854670857931&authuser=0&continue=http%3A%2F%2Fsupport.google.com%2Fmail%2Fanswer%2F8494%3Fhl%3Dvi%26co%3DGENIE.Platform%253DDesktop&ec=GAlAdQ&hl=vi&flowName=GlifWebSignIn&flowEntry=AddSession")
            startActivity(intent)
        }

        facebook.setOnClickListener {
            val intent: Intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.data = Uri.parse("https://www.facebook.com")
            startActivity(intent)
        }
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

    fun checkSaveAccount() {
        checkSave = sharedPreferences.getBoolean("checkSave", false)
        strEmail = sharedPreferences.getString("email", "").toString()
        strPassword = sharedPreferences.getString("password", "").toString()

        edtEnterEmailLogin.setText(strEmail)
        passwordLogin.setText(strPassword)
        checkForgetPassword.isChecked = true
    }

    private fun hidePassword() {
        if (passwordLogin.transformationMethod == PasswordTransformationMethod.getInstance()) {
            passwordLogin.transformationMethod = null
            hidePasswordLogin.setBackgroundResource(R.drawable.icon_show_password_grey)
        } else if (passwordLogin.transformationMethod == null) {
            passwordLogin.transformationMethod = PasswordTransformationMethod.getInstance()
            hidePasswordLogin.setBackgroundResource(R.drawable.icon_hint_grey)
        }
    }

    fun setNote(string: Int, color: Int) {
        val circle: Animation = AnimationUtils.loadAnimation(requireActivity(), R.anim.anim_shake)
        warningLogin.text = getString(string)
        warningLogin.setTextColor(resources.getColor(color))
        warningLogin.visibility = View.VISIBLE
        warningLogin.startAnimation(circle)
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
        return inflater.inflate(R.layout.fragment__login, container, false)
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
         * @return A new instance of fragment Fragment_Login.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentLogin().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}