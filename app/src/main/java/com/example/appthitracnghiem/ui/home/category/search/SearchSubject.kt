package com.example.appthitracnghiem.ui.home.category.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appthitracnghiem.R
import kotlinx.android.synthetic.main.activity_search_subject.*

class SearchSubject : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_subject)

        backSearchSubject.setOnClickListener {
            onBackPressed()
        }
    }
}