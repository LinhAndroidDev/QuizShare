package com.example.appthitracnghiem.ui.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.appthitracnghiem.utils.PreferenceUtil
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<V : BaseViewModel> : Fragment(), IOnFragmentBackListener {
    lateinit var viewModel: V
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProviders.of(this)[(this::class.java.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<V>]
        activity?.let { viewModel.mPreferenceUtil = PreferenceUtil(it) }
        bindData()
    }

    override fun onFragmentBack(): Boolean {
        return false
    }

    open fun bindData() {
        viewModel.errorApiLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(
                requireContext(),
                it,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}