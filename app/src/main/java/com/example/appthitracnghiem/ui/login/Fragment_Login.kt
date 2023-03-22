package com.example.appthitracnghiem.ui.login

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Paint
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.preference.PreferenceManager
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.base.api.ApiService
import com.example.appthitracnghiem.model.Email
import com.example.appthitracnghiem.model.LoginSuccessful
import com.example.appthitracnghiem.model.ViewModelGeneral
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.home.HomeActivity
import kotlinx.android.synthetic.main.fragment__login.*
import kotlinx.android.synthetic.main.fragment__login.view.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment_Login.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("DEPRECATION")
class Fragment_Login : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var viewModelGeneral : ViewModelGeneral
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    var checkSave : Boolean = false
    lateinit var strEmail : String
    lateinit var strPassword : String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelGeneral = ViewModelProvider(requireActivity())[ViewModelGeneral::class.java]

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireActivity())
        editor = sharedPreferences.edit()

        checkSaveAccount()

        hidePasswordLogin.setOnClickListener {
            hidePassword()
        }

        forgetPassword.setOnClickListener {
            val fragmentForgetpassword : Fragment_ForgetPassword = Fragment_ForgetPassword()
            val fm : FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            fm.setCustomAnimations(R.anim.anim_translate_enter_right,R.anim.anim_translate_exit_left,R.anim.anim_translate_enter_left,R.anim.anim_translate_exit_right)
            fm.addToBackStack("Fragment_ForgetPassword")
            fm.replace(R.id.loginContainerID,fragmentForgetpassword).commit()
        }

        clickLogin()

        setText()
    }

    /** set font*/
    private fun setText() {
        forgetPassword.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        val semibold : Typeface? = ResourcesCompat.getFont(requireActivity(),R.font.svn_gilroy_semibold)
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

            if(strEmail.isEmpty() || strPassword.isEmpty()){
                setNote(R.string.txt_notification_login,R.color.color_green)
            }else{
                val email : Email = Email(strEmail,strPassword)

                if(!email.isValidEmail()){
                    setNote(R.string.txt_warning_login,R.color.color_red)
                }else{
                    if(!email.isPassword()){
                        setNote(R.string.txt_warning_password,R.color.color_red)
                    }else{
                        warningLogin.visibility = View.GONE
                        val progressDialog : ProgressDialog = ProgressDialog(requireActivity())
                        progressDialog.setMessage("Please wait ...")
                        progressDialog.show()

                        val requestBodyStrEmail : RequestBody = RequestBody.create("multipart/from-data".toMediaTypeOrNull(), strEmail)
                        val requestBodyStrPassword : RequestBody = RequestBody.create("multipart/from-data".toMediaTypeOrNull(), strPassword)

                        val countDownTimer : CountDownTimer = object : CountDownTimer(3000,3000){
                            override fun onTick(millisUntilFinished: Long) {

                            }

                            override fun onFinish() {
                                progressDialog.dismiss()

                                viewModelGeneral.postRetrofit.create(ApiService::class.java)
                                    .loginUser(requestBodyStrEmail,requestBodyStrPassword)
                                    .enqueue(object : retrofit2.Callback<LoginSuccessful> {
                                        override fun onFailure(
                                            call: retrofit2.Call<LoginSuccessful>,
                                            t: Throwable,
                                        ) {
                                            Toast.makeText(requireContext(),"Error",Toast.LENGTH_SHORT).show()
                                        }

                                        override fun onResponse(
                                            call: retrofit2.Call<LoginSuccessful>,
                                            response: retrofit2.Response<LoginSuccessful>,
                                        ) {
                                            if(response.isSuccessful){
                                                val loginSuccessful : LoginSuccessful? = response.body()
                                                if(loginSuccessful!!.status == 0){
                                                    val intent : Intent = Intent(requireActivity(),HomeActivity::class.java)
                                                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                                    startActivity(intent)

                                                    saveAccount(strEmail,strPassword);
                                                }else{
                                                    Toast.makeText(requireActivity(),"Email hoặc mật khẩu không chính xác",Toast.LENGTH_SHORT).show()
                                                }
                                            }else{
                                                Toast.makeText(requireActivity(),"Lỗi kết nối Server",Toast.LENGTH_SHORT).show()
                                            }
                                        }

                                    })
                            }
                        }.start()
                    }
                }
            }
        }

        google.setOnClickListener{
            val intent : Intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.data = Uri.parse("https://accounts.google.com/v3/signin/identifier?dsh=S-620025444%3A1673854670857931&authuser=0&continue=http%3A%2F%2Fsupport.google.com%2Fmail%2Fanswer%2F8494%3Fhl%3Dvi%26co%3DGENIE.Platform%253DDesktop&ec=GAlAdQ&hl=vi&flowName=GlifWebSignIn&flowEntry=AddSession")
            startActivity(intent)
        }

        facebook.setOnClickListener{
            val intent : Intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.data = Uri.parse("https://www.facebook.com")
            startActivity(intent)
        }
    }

    private fun saveAccount(email : String,password : String) {
        if(checkForgetPassword.isChecked){
            editor.putBoolean("checkSave",true)
            editor.commit()
            editor.putString("email",email)
            editor.commit()
            editor.putString("password",password)
            editor.commit()
        }else{
            editor.putBoolean("checkSave",false)
            editor.commit()
            editor.putString("email","")
            editor.commit()
            editor.putString("password","")
            editor.commit()
        }
    }

    fun checkSaveAccount(){
        checkSave = sharedPreferences.getBoolean("checkSave",false)
        strEmail = sharedPreferences.getString("email","").toString()
        strPassword = sharedPreferences.getString("password","").toString()

        edtEnterEmailLogin.setText(strEmail)
        passwordLogin.setText(strPassword)
        checkForgetPassword.isChecked = true
    }

    private fun hidePassword() {
        if(passwordLogin.transformationMethod == PasswordTransformationMethod.getInstance()) {
            passwordLogin.transformationMethod = null
            hidePasswordLogin.setBackgroundResource(R.drawable.icon_show_password_grey)
        }else if(passwordLogin.transformationMethod == null){
            passwordLogin.transformationMethod = PasswordTransformationMethod.getInstance()
            hidePasswordLogin.setBackgroundResource(R.drawable.icon_hint_grey)
        }
    }

    fun setNote(string : Int, color : Int){
        val circle : Animation = AnimationUtils.loadAnimation(requireActivity(),R.anim.anim_shake)
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
            Fragment_Login().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}