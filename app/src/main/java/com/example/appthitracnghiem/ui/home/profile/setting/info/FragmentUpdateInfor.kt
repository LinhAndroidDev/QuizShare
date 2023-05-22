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
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.HomeActivity
import com.example.appthitracnghiem.ui.home.profile.setting.changeavatar.ChangeAvatarActivity
import com.example.appthitracnghiem.utils.PreferenceKey
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment__register.*
import kotlinx.android.synthetic.main.fragment_create_test.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_setting.*
import kotlinx.android.synthetic.main.fragment_setting_new_password.*
import kotlinx.android.synthetic.main.fragment_update_infor.*
import java.text.SimpleDateFormat
import java.util.*

@Suppress("DEPRECATION")
class FragmentUpdateInfor : BaseFragment<UpdateInfoViewModel>() {
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

        if(avt?.isEmpty() == true){
            avatarUpdateInfo.setImageResource(R.drawable.logo6)
        }else{
            Picasso.get()
                .load(avt)
                .placeholder(R.drawable.loadimage)
                .error(R.drawable.logo6)
                .into(avatarUpdateInfo)
        }

        backUpdateInfo.setOnClickListener {
            activity?.onBackPressed()
        }

        changeAvatar.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            startActivityForResult(intent, GALLERY_RED_CODE)
        }

        selectDateInfo.setOnClickListener {

            val getDate = Calendar.getInstance()
            val datePicker = DatePickerDialog(requireActivity(), android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                { _, year, month, dayOfMonth ->

                val selectDate: Calendar = Calendar.getInstance()
                selectDate.set(Calendar.YEAR, year)
                selectDate.set(Calendar.MONTH, month)
                selectDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    edtBirthInfo.text = formatDate.format(selectDate.time)

            }, getDate.get(Calendar.YEAR), getDate.get((Calendar.MONTH)), getDate.get(Calendar.DAY_OF_MONTH))
            datePicker.show()
        }

        resetInfor.setOnClickListener {
            val name = edtNameInfo.text.toString()
            val birth = edtBirthInfo.text.toString()

            if(name.isEmpty() || birth.isEmpty()){
                Toast.makeText(requireActivity(),"Bạn chưa nhập đủ thông tin",Toast.LENGTH_SHORT).show()
            }else{
                val header = viewModel.mPreferenceUtil.defaultPref()
                    .getString(PreferenceKey.AUTHORIZATION, "").toString()
                val userId = viewModel.mPreferenceUtil.defaultPref()
                    .getInt(PreferenceKey.USER_ID, 0)
                val requestUpdateInfo = RequestUpdateInfo(userId, name, birth)
                viewModel.updateInfo(header, requestUpdateInfo)
            }
        }

        setText()
    }

    /** set font*/
    private fun setText() {
        val semibold: Typeface? =
            ResourcesCompat.getFont(requireActivity(), R.font.svn_gilroy_semibold)
        txtCapNhatThongTin.typeface = semibold
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
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_infor, container, false)
    }

    override fun onFragmentBack(): Boolean {
        return false
    }
}