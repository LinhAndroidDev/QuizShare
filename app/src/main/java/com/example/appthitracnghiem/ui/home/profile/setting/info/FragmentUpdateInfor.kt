package com.example.appthitracnghiem.ui.home.profile.setting.info

import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.databinding.FragmentUpdateInforBinding
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.HomeActivity
import com.example.appthitracnghiem.ui.home.profile.setting.changeavatar.ChangeAvatarActivity
import com.example.appthitracnghiem.utils.PreferenceKey
import com.example.appthitracnghiem.utils.loadNetworkImage
import java.text.SimpleDateFormat
import java.util.*
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class FragmentUpdateInfor : BaseFragment<UpdateInfoViewModel>() {
    private var _binding: FragmentUpdateInforBinding? = null
    private val binding get() = _binding!!
    private val GALLERY_RED_CODE: Int = 1000
    private var formatDate = SimpleDateFormat("yyyy/MM/dd", Locale.UK)

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
                Toast.makeText(requireActivity(),"Bạn đã cập nhật lại thông tin",Toast.LENGTH_SHORT).show()
                val intent = Intent(requireActivity(), HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }

    private fun initUi() {

        val avt = viewModel.mPreferenceUtil.defaultPref()
            .getString(PreferenceKey.USER_AVATAR,"")

        binding.avatarUpdateInfo.loadNetworkImage(
            avt,
            emptyUrlRes = R.drawable.logo6,
            errorRes = R.drawable.logo6,
        )

        binding.backUpdateInfo.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.changeAvatar.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            startActivityForResult(intent, GALLERY_RED_CODE)
        }

        binding.selectDateInfo.setOnClickListener {

            val getDate = Calendar.getInstance()
            val datePicker = DatePickerDialog(requireActivity(), android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                { _, year, month, dayOfMonth ->

                val selectDate: Calendar = Calendar.getInstance()
                selectDate.set(Calendar.YEAR, year)
                selectDate.set(Calendar.MONTH, month)
                selectDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    binding.edtBirthInfo.text = formatDate.format(selectDate.time)

            }, getDate.get(Calendar.YEAR), getDate.get((Calendar.MONTH)), getDate.get(Calendar.DAY_OF_MONTH))
            datePicker.show()
        }

        binding.resetInfor.setOnClickListener {
            val name = binding.edtNameInfo.text.toString()
            val birth = binding.edtBirthInfo.text.toString()

            if(name.isEmpty() || birth.isEmpty()){
                Toast.makeText(requireActivity(),"Bạn chưa nhập đủ thông tin",Toast.LENGTH_SHORT).show()
            }else{
                val userId = viewModel.mPreferenceUtil.defaultPref()
                    .getInt(PreferenceKey.USER_ID, 0)
                viewModel.updateInfo(RequestUpdateInfo(userId, name, birth))
            }
        }

        setText()
    }

    /** set font*/
    private fun setText() {
        val semibold: Typeface? =
            ResourcesCompat.getFont(requireActivity(), R.font.svn_gilroy_semibold)
        binding.txtCapNhatThongTin.typeface = semibold
    }

    /** Get Image from Storage*/
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == AppCompatActivity.RESULT_OK) {
            if (requestCode == GALLERY_RED_CODE) {
                val intent = Intent(requireActivity(), ChangeAvatarActivity::class.java)
                intent.putExtra("Uri",data?.data.toString())
                startActivity(intent)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentUpdateInforBinding.inflate(inflater, container, false)
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