package com.example.appthitracnghiem.ui.home.profile.setting.email

import android.app.ProgressDialog
import android.graphics.Typeface
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.utils.PreferenceKey
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_setting_new_password.*
import kotlinx.android.synthetic.main.fragment_update_email.*

@Suppress("DEPRECATION")
class FragmentUpdateEmail : BaseFragment<UpdateEmailViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    override fun bindData() {
        super.bindData()

        val loading = ProgressDialog(requireActivity())
        loading.setTitle("Thông báo")
        loading.setMessage("Please wait...")
        viewModel.isLoadingLiveData.observe(viewLifecycleOwner){
            if(it){
                loading.show()
            }else{
                loading.dismiss()
            }
        }

        viewModel.isSuccessfulLiveData.observe(viewLifecycleOwner){
            if(it){
                Toast.makeText(requireActivity(),"Bạn đã đổi Email thành công",Toast.LENGTH_SHORT).show()
                activity?.onBackPressed()
            }
        }
    }

    private fun initUi() {
        backUpdateEmail.setOnClickListener {
            activity?.onBackPressed()
        }

        updateEmail.setOnClickListener {
            val strEmail = edtUpdateEmail.text.toString()
            if(strEmail.isEmpty()){
                Toast.makeText(requireActivity(),"Bạn chưa nhập Email",Toast.LENGTH_SHORT).show()
            }else if(!Patterns.EMAIL_ADDRESS.matcher(strEmail)
                    .matches()){
                Toast.makeText(requireActivity(),"Vui lòng nhập lại Email",Toast.LENGTH_SHORT).show()
            } else{
                val useId = viewModel.mPreferenceUtil.defaultPref()
                    .getInt(PreferenceKey.USER_ID, 0)
                val header = viewModel.mPreferenceUtil.defaultPref()
                    .getString(PreferenceKey.AUTHORIZATION,"").toString()
                val requestUpdateEmail = RequestUpdateEmail(useId, strEmail)
                viewModel.updateEmail(header, requestUpdateEmail)
            }
        }

        setText()
    }

    /** set font*/
    private fun setText() {
        val semibold: Typeface? =
            ResourcesCompat.getFont(requireActivity(), R.font.svn_gilroy_semibold)
        txtCapNhatEmail.typeface = semibold
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_email, container, false)
    }

    override fun onFragmentBack(): Boolean {
        return false
        }
}