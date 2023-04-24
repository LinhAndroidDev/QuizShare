package com.example.appthitracnghiem.ui.home.profile.setting

import android.graphics.Point
import android.graphics.Typeface
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

        Picasso.get().load("https://img2.thuthuatphanmem.vn/uploads/2019/01/04/hinh-anh-dep-co-gai-de-thuong_025103410.jpg")
            .placeholder(R.drawable.loadimage)
            .error(R.drawable.icon_error)
            .centerCrop()
            .fit()
            .into(avatarEdit)

        setText()

        click()
    }

    private fun setText() {
        val semibold: Typeface? = ResourcesCompat.getFont(this,R.font.svn_gilroy_semibold)
        txtChangeAvatar.typeface = semibold
    }

    private fun click() {
        backChangeAvatar.setOnClickListener {
            this.onBackPressed()
        }
    }
}