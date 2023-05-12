package com.example.appthitracnghiem.ui.home.profile.setting.changeavatar

import android.graphics.Point
import android.graphics.Typeface
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display
import androidx.core.content.res.ResourcesCompat
import com.example.appthitracnghiem.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_change_avatar.*

@Suppress("DEPRECATION")
class ChangeAvatarActivity : AppCompatActivity() {
    var screenWitch: Int = 0
    var screenHeight: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_avatar)

        val display: Display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        screenWitch = size.x
        screenHeight = size.y

        avatarEdit.maxWidth = screenWitch
        avatarEdit.maxHeight = screenWitch
        strokeAvatar.maxWidth = screenWitch
        strokeAvatar.maxHeight = screenWitch

        initUi()
    }

    private fun setText() {
        val semibold: Typeface? = ResourcesCompat.getFont(this,R.font.svn_gilroy_semibold)
        txtChangeAvatar.typeface = semibold
    }

    private fun initUi() {

        val strImage: String = intent.getStringExtra("Uri").toString()
        val uriImage: Uri = Uri.parse(strImage)
        Picasso.get().load(uriImage)
            .placeholder(R.drawable.loadimage)
            .error(R.drawable.icon_error)
            .centerCrop()
            .fit()
            .into(avatarEdit)
//        avatarEdit.setImageURI(uriImage)

        backChangeAvatar.setOnClickListener {
            this.onBackPressed()
        }

        setText()
    }
}