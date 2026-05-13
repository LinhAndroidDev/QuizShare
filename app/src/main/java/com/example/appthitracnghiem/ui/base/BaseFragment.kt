package com.example.appthitracnghiem.ui.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.appthitracnghiem.utils.PreferenceUtil
import java.lang.reflect.ParameterizedType
abstract class BaseFragment<V : BaseViewModel> : Fragment(), IOnFragmentBackListener {
    lateinit var viewModel: V

    /**
     * Mặc định: ViewModel theo fragment. Ghi đè thành [requireActivity] khi cần dùng chung
     * giữa nhiều fragment trong cùng activity (ví dụ luồng tạo đề, luồng làm bài).
     */
    protected open fun viewModelStoreOwner(): ViewModelStoreOwner = this

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(viewModelStoreOwner())[(this::class.java.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<V>]
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