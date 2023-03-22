package com.example.appthitracnghiem.ui.home.createtest

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import com.example.appthitracnghiem.R
import kotlinx.android.synthetic.main.activity_link_sheet.*
import kotlinx.android.synthetic.main.fragment_create_test.*

class LinkSheetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_link_sheet)

        backLinkSheet.setOnClickListener {
            onBackPressed()
        }

        setText()
    }

    /** set font*/
    private fun setText() {
        val semibold: Typeface? = ResourcesCompat.getFont(this, R.font.svn_gilroy_semibold)
        txtGoogleSheet.typeface = semibold
    }
}