package com.example.appthitracnghiem.ui.home.createtest

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import android.widget.PopupWindow
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.HomeActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.fragment_create_test.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.layout_bottom_share.*

@Suppress("DEPRECATION")
class FragmentCreateTest : BaseFragment<EmptyViewModel>() {
    private val GALLERY_RED_CODE: Int = 1000

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        (activity as HomeActivity).hideTabBar(scrollCreateTest)

        setBottomShare()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            initUi()
        }

        setText()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setStatusBar() {
        val window: Window? = activity?.window
        window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window?.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window?.statusBarColor = ContextCompat.getColor(requireActivity(), R.color.white)

        window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;//  set status text dark
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun initUi() {

        setStatusBar()

        menuCreateTest.setOnClickListener {
            showMenuCreate(menuCreateTest, R.layout.popup_create_test, 0, -30, Gravity.BOTTOM)
        }

        selectDepartment.setOnClickListener {
            showMenuCreate(selectDepartment, R.layout.popup_select_partment, 0, -30, Gravity.BOTTOM)
        }

        selectMode.setOnClickListener {
            showMenuCreate(selectMode, R.layout.popup_select_mode, 0, -30, Gravity.BOTTOM)
        }

        addCoverImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            startActivityForResult(intent, GALLERY_RED_CODE)
        }

        googleSheet.setOnClickListener {
            val intent = Intent(requireActivity(), LinkSheetActivity::class.java)
            startActivity(intent)
        }

        createTest.setOnClickListener {
            val strNumberQuiz: String = edtNumberQuiz.text.toString()
            if (strNumberQuiz.isEmpty()) {
                Toast.makeText(requireActivity(), "Bạn chưa nhập câu hỏi số", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val intent = Intent(requireActivity(), CreateTestActivity::class.java)
                intent.putExtra("numberQuiz", strNumberQuiz)
                startActivity(intent)
            }
        }

        shareWithFacebook.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.data = Uri.parse("https://www.facebook.com")
            startActivity(intent)
        }

        shareWithMail.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.data =
                Uri.parse("https://accounts.google.com/v3/signin/identifier?dsh=S-620025444%3A1673854670857931&authuser=0&continue=http%3A%2F%2Fsupport.google.com%2Fmail%2Fanswer%2F8494%3Fhl%3Dvi%26co%3DGENIE.Platform%253DDesktop&ec=GAlAdQ&hl=vi&flowName=GlifWebSignIn&flowEntry=AddSession")
            startActivity(intent)
        }
    }

    /** Share Test*/
    @SuppressLint("ClickableViewAccessibility")
    private fun setBottomShare() {
        layoutBottomShare.setOnTouchListener { _, _ -> true }
        val bottomShareBehavior = BottomSheetBehavior.from(layoutBottomShare)
        shareTest.setOnClickListener {
            if (bottomShareBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                bottomShareBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            } else {
                bottomShareBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }
        layoutCreateTest.setOnTouchListener { _, event ->
            when(event.actionMasked){
                MotionEvent.ACTION_UP->{
                    bottomShareBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                }
            }
            true
        }
    }


    /** Get Image from Storage*/
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == AppCompatActivity.RESULT_OK) {
            if (requestCode == GALLERY_RED_CODE) {
                imageCover.setImageURI(data?.data)
            }
        }
    }

    /** show menu add test */
    private fun showMenuCreate(anchor: View, layout: Int, x: Int, y: Int, position: Int) {
        val popUpView: View = View.inflate(requireActivity(), layout, null)

        val width = ViewGroup.LayoutParams.WRAP_CONTENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        val focusable = true

        val popupWindow = PopupWindow(popUpView, width, height, focusable)
        popupWindow.showAsDropDown(anchor, x, y, position)
    }

    /** set font **/
    private fun setText() {
        val semibold: Typeface? =
            ResourcesCompat.getFont(requireActivity(), R.font.svn_gilroy_semibold)
        txtAddTest.typeface = semibold
        txtTiltle.typeface = semibold
        txtDownTest.typeface = semibold
        txtSelectDepartment.typeface = semibold
        txtSelectMode.typeface = semibold
        txtTimeDoTest.typeface = semibold
        txtNumberQuestion.typeface = semibold
        txtDetail.typeface = semibold
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_test, container, false)
    }

    override fun onFragmentBack(): Boolean {
        return false
    }
}