package com.example.appthitracnghiem.ui.login.forgetpassword

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.utils.Email
import kotlinx.android.synthetic.main.fragment__forget_password.*

@Suppress("DEPRECATION")
class FragmentForgetPassword : BaseFragment<ForgetPasswordViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    override fun bindData() {
        super.bindData()

        val loading = ProgressDialog(requireActivity())
        loading.setTitle("Thông báo")
        loading.setMessage("Please wait...")
        viewModel.isLoading.observe(viewLifecycleOwner){
            if(it){
                loading.show()
            }else{
                loading.dismiss()
            }
        }

        viewModel.isSuccessful.observe(viewLifecycleOwner){
            if(it){
                val edtEmail: String = emailForgetPassword.text.toString()
                val bundle = Bundle()
                bundle.putString("email", edtEmail)
                val fragmentCreatePassword = FragmentCreatePassword()
                val fm: FragmentTransaction =
                    requireActivity().supportFragmentManager.beginTransaction()
                fm.setCustomAnimations(
                    R.anim.anim_translate_enter_right,
                    R.anim.anim_translate_exit_left,
                    R.anim.anim_translate_enter_left,
                    R.anim.anim_translate_exit_right
                )
                fm.addToBackStack("Fragment_CreatePassword")
                fm.replace(R.id.loginContainerID, fragmentCreatePassword).commit()
                fragmentCreatePassword.arguments = bundle
            }
        }
    }

    private fun initUi() {
        sendTo.setOnClickListener {
            val edtEmail: String = emailForgetPassword.text.toString()
            if(edtEmail.isEmpty()){
                Toast.makeText(requireActivity(),"Bạn chưa nhập email",Toast.LENGTH_SHORT).show()
            }else if(!Patterns.EMAIL_ADDRESS.matcher(edtEmail).matches()){
                Toast.makeText(requireActivity(),"Email không đúng định dạng",Toast.LENGTH_SHORT).show()
            }else{
                val requestEmailVerification = RequestEmailVerification(edtEmail)
                viewModel.checkEmail(requestEmailVerification)
            }
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