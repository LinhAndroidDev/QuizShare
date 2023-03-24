package com.example.appthitracnghiem.ui.intro

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.connectivity.CheckConnect
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseActivity
import com.example.appthitracnghiem.ui.intro.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_intro.*

@Suppress("DEPRECATION")
class IntroActivity : BaseActivity<EmptyViewModel>() {
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        if (CheckConnect.haveNetworkConnected(this@IntroActivity)) {
            viewPagerAdapter = ViewPagerAdapter(
                supportFragmentManager,
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
            )

            viewPagerIntro.adapter = viewPagerAdapter
            indicatorIntro.setViewPager(viewPagerIntro)

            hideButton()

            click()

        } else {
            CheckConnect.showToastShort(this@IntroActivity, "Bạn đang ngoại tuyến")
        }
    }

    /** Next ViewPager*/
    private fun click() {
        skip.setOnClickListener {
            val intent: Intent = Intent(this, IntroLoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        nextIntro.setOnClickListener {
            when (viewPagerIntro.currentItem) {
                2 -> {
                    val intent: Intent = Intent(this, IntroLoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else -> {
                    viewPagerIntro.currentItem += 1
                }
            }
        }
    }

    private fun hideButton() {
        viewPagerIntro.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
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