package com.example.appthitracnghiem.ui.home.createtest

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.appthitracnghiem.databinding.ActivityLinkSheetBinding
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class LinkSheetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLinkSheetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLinkSheetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUi()
    }

    private fun initUi() {
        binding.backLinkSheet.setOnClickListener {
            onBackPressed()
        }

        binding.checkSheet.setOnClickListener {
            binding.layoutCheckSheet.visibility = View.VISIBLE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                binding.layoutSheet.setRenderEffect(
                    RenderEffect.createBlurEffect(
                        50f,
                        50f,
                        Shader.TileMode.MIRROR
                    )
                )
            }
        }

        binding.backCheckSheet.setOnClickListener {
            binding.layoutCheckSheet.visibility = View.GONE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                binding.layoutSheet.setRenderEffect(null)
            }
        }
    }

}