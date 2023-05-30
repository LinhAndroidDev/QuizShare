package com.example.appthitracnghiem.ui.home.profile.setting.changeavatar

import android.Manifest
import android.app.ProgressDialog
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Point
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.Display
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.base.BaseActivity
import com.example.appthitracnghiem.ui.home.HomeActivity
import com.example.appthitracnghiem.utils.Const
import com.example.appthitracnghiem.utils.PreferenceKey
import com.example.appthitracnghiem.utils.UriConvertFile
import com.soundcloud.android.crop.Crop
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

    companion object {
        private const val REQUEST_CODE_PERMISSIONS = 101
        private const val REQUEST_CODE_PICK_IMAGE = 102
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_avatar)

        val permissions = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                permissions,
                REQUEST_CODE_PERMISSIONS
            )
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

        avatarEdit.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE)
        }

//        avatarEdit.setImageURI(uriImage)

        backChangeAvatar.setOnClickListener {
            this.onBackPressed()
        }

        setText()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                grantResults[1] == PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(this, "Permissions granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Permissions not granted", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//                && resultCode == RESULT_OK
        if (requestCode == REQUEST_CODE_PICK_IMAGE && data != null) {
            val uri = data.data
            if (uri != null) {
                Crop.of(uri, Uri.fromFile(File(cacheDir, "cropped")))
                    .asSquare()
                    .start(this)
            }
        } else
            if (requestCode == Crop.REQUEST_CROP) {
            val croppedUri = Crop.getOutput(data)
            if ( croppedUri != null) {
                val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, croppedUri)
                avatarEdit.setImageBitmap(bitmap)
//                saveImageToGallery(bitmap)
            }
        }
    }

    private fun saveImageToGallery(bitmap: Bitmap) {
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "Image_${System.currentTimeMillis()}.jpg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/MyApp")
        }
        val contentResolver = contentResolver
        val uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        if (uri != null) {
            contentResolver.openOutputStream(uri).use { outputStream ->
                if (outputStream != null) {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)
                    Toast.makeText(this, "Image saved to gallery", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Failed to save image to gallery", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setText() {
        val semibold: Typeface? = ResourcesCompat.getFont(this,R.font.svn_gilroy_semibold)
        txtChangeAvatar.typeface = semibold
    }
}