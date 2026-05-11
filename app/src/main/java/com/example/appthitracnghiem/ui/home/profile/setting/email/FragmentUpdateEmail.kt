package com.example.appthitracnghiem.ui.home.profile.setting.email

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.databinding.FragmentUpdateEmailBinding
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.utils.PreferenceKey
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class FragmentUpdateEmail : BaseFragment<UpdateEmailViewModel>() {
    private var _binding: FragmentUpdateEmailBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    override fun bindData() {
        super.bindData()

        val loading = ProgressDialog(requireActivity())
        loading.setTitle(getString(R.string.dialog_title_notice))
        loading.setMessage(getString(R.string.loading_please_wait))
        viewModel.isLoadingLiveData.observe(viewLifecycleOwner){
            if(it){
                loading.show()
            }else{
                loading.dismiss()
            }
        }

        viewModel.isSuccessfulLiveData.observe(viewLifecycleOwner){
            if(it){
                Toast.makeText(requireActivity(), getString(R.string.toast_email_updated), Toast.LENGTH_SHORT).show()
                activity?.onBackPressed()
            }
        }
    }

    private fun initUi() {
        binding.backUpdateEmail.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.updateEmail.setOnClickListener {
            val strEmail = binding.edtUpdateEmail.text.toString()
            if(strEmail.isEmpty()){
                Toast.makeText(requireActivity(), getString(R.string.toast_email_empty), Toast.LENGTH_SHORT).show()
            }else if(!Patterns.EMAIL_ADDRESS.matcher(strEmail)
                    .matches()){
                Toast.makeText(requireActivity(), getString(R.string.toast_email_invalid), Toast.LENGTH_SHORT).show()
            } else{
                val useId = viewModel.mPreferenceUtil.defaultPref()
                    .getInt(PreferenceKey.USER_ID, 0)
                viewModel.updateEmail(RequestUpdateEmail(useId, strEmail))
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentUpdateEmailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onFragmentBack(): Boolean {
        return false
        }
}