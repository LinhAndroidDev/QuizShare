package com.example.appthitracnghiem.ui.home.profile.setting.changeavatar

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Point
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.view.Display
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.base.BaseActivity
import com.example.appthitracnghiem.ui.home.HomeActivity
import com.example.appthitracnghiem.utils.Const
import com.example.appthitracnghiem.utils.PreferenceKey
import com.example.appthitracnghiem.utils.UriConvertFile
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_change_avatar.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

@Suppress("DEPRECATION")
class ChangeAvatarActivity : BaseActivity<ChangeAvatarViewModel>() {
    var screenWitch: Int = 0
    var screenHeight: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_avatar)

        setSizeScreen()

        val strImage: String = intent.getStringExtra("Uri").toString()
        val uriImage: Uri = Uri.parse(strImage)
        Picasso.get().load(uriImage)
            .placeholder(R.drawable.loadimage)
            .error(R.drawable.icon_error)
            .centerCrop()
            .fit()
            .into(avatarEdit)

        val strPath: String = UriConvertFile.getFileFromUri(this,uriImage).toString()
        val file = File(strPath)
        val requestBodyAvatar: RequestBody =RequestBody.create("multipart/form-data".toMediaTypeOrNull(),file)
        val multipartBodyAvt: MultipartBody.Part = MultipartBody.Part.createFormData(Const.file,file.name,requestBodyAvatar)

        val header = viewModel.mPreferenceUtil.defaultPref()
            .getString(PreferenceKey.AUTHORIZATION,"").toString()

        val userId = viewModel.mPreferenceUtil.defaultPref()
            .getInt(PreferenceKey.USER_ID,0).toString()
        val requestBodyId: RequestBody =
            userId.toRequestBody("multipart/form-data".toMediaTypeOrNull())

        done.setOnClickListener {
            viewModel.requestAvt(header, requestBodyId, multipartBodyAvt)
        }

        initUi()
    }

    private fun setSizeScreen() {
        val display: Display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        screenWitch = size.x
        screenHeight = size.y

        avatarEdit.maxWidth = screenWitch
        avatarEdit.maxHeight = screenWitch
        strokeAvatar.maxWidth = screenWitch
        strokeAvatar.maxHeight = screenWitch
    }

    override fun bindData() {
        super.bindData()

        val progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait...")

        viewModel.isLoadingLiveData.observe(this, Observer {
            if(it){
                progressDialog.show()
            }else{
                progressDialog.dismiss()
            }
        })

        viewModel.isSuccessfulLiveData.observe(this, Observer {
            if(it){
                Toast.makeText(this, "Thay đổi ảnh đại diện thành công", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@ChangeAvatarActivity, HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
        })
    }

    private fun initUi() {
//        avatarEdit.setImageURI(uriImage)

        backChangeAvatar.setOnClickListener {
            this.onBackPressed()
        }

        setText()
    }

    private fun setText() {
        val semibold: Typeface? = ResourcesCompat.getFont(this,R.font.svn_gilroy_semibold)
        txtChangeAvatar.typeface = semibold
    }
}