package com.example.appthitracnghiem.ui.home.createtest

import android.graphics.RenderEffect
import android.graphics.Shader
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.example.appthitracnghiem.R
import kotlinx.android.synthetic.main.activity_link_sheet.*
import kotlinx.android.synthetic.main.fragment_exam.*

@Suppress("DEPRECATION")
class LinkSheetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_link_sheet)

        click()

        setText()
    }

    private fun click() {
        backLinkSheet.setOnClickListener {
            onBackPressed()
        }

        checkSheet.setOnClickListener {
            layoutCheckSheet.visibility = View.VISIBLE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                layoutSheet.setRenderEffect(
                    RenderEffect.createBlurEffect(
                        50f,
                        50f,
                        Shader.TileMode.MIRROR
                    )
                )
            }
        }

        backCheckSheet.setOnClickListener {
            layoutCheckSheet.visibility = View.GONE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                layoutSheet.setRenderEffect(null)
            }
        }
    }

    /** set font*/
    private fun setText() {
        val semibold: Typeface? = ResourcesCompat.getFont(this, R.font.svn_gilroy_semibold)
        txtGoogleSheet.typeface = semibold
    }
}