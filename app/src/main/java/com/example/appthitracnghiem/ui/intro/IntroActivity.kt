package com.example.appthitracnghiem.ui.intro

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.connectivity.CheckConnect
import com.example.appthitracnghiem.databinding.ActivityIntroBinding
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseActivity
import com.example.appthitracnghiem.ui.intro.adapter.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class IntroActivity : BaseActivity<EmptyViewModel>() {
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (CheckConnect.haveNetworkConnected(this@IntroActivity)) {
            viewPagerAdapter = ViewPagerAdapter(
                supportFragmentManager,
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
            )

            binding.viewPagerIntro.adapter = viewPagerAdapter
            binding.indicatorIntro.setViewPager(binding.viewPagerIntro)

            hideButton()

            initUi()

        } else {
            CheckConnect.showToastShort(this@IntroActivity, getString(R.string.toast_offline))
        }
    }

    private fun setStatusBar() {
        val window: Window = this@IntroActivity.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = ContextCompat.getColor(this@IntroActivity, R.color.backgroundIntro)
    }

    /** Next ViewPager*/
    private fun initUi() {

        setStatusBar()

        binding.skip.setOnClickListener {
            val intent = Intent(this, IntroLoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.nextIntro.setOnClickListener {
            when (binding.viewPagerIntro.currentItem) {
                2 -> {
                    val intent = Intent(this, IntroLoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else -> {
                    binding.viewPagerIntro.currentItem += 1
                }
            }
        }
    }

    private fun hideButton() {
        binding.viewPagerIntro.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int,
            ) {

            }

            override fun onPageSelected(position: Int) {
//                if(viewPagerIntro.currentItem == 2){
//                    nextIntro.visibility = View.INVISIBLE
//                    indicatorIntro.visibility = View.INVISIBLE
//                    skip.visibility = View.INVISIBLE
//                }else{
//                    nextIntro.visibility = View.VISIBLE
//                    indicatorIntro.visibility = View.VISIBLE
//                    skip.visibility = View.VISIBLE
//                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })
    }
}