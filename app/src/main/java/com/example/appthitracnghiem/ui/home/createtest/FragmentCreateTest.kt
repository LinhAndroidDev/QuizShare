package com.example.appthitracnghiem.ui.home.createtest

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.fragment_create_test.*
import kotlinx.android.synthetic.main.layout_bottom_share.*

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentCreateTest.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("DEPRECATION")
class FragmentCreateTest : BaseFragment<EmptyViewModel>() {
    private val GALLERY_RED_CODE: Int = 1000

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setBottomShare()

        click()

        setText()
    }

    private fun click() {
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
            val intent: Intent = Intent(Intent.ACTION_PICK)
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            startActivityForResult(intent, GALLERY_RED_CODE)
        }

        googleSheet.setOnClickListener {
            val intent: Intent = Intent(requireActivity(), LinkSheetActivity::class.java)
            startActivity(intent)
        }

        createTest.setOnClickListener {
            val strNumberQuiz: String = edtNumberQuiz.text.toString()
            if (strNumberQuiz.isEmpty()) {
                Toast.makeText(requireActivity(), "Bạn chưa nhập câu hỏi số", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val intent: Intent = Intent(requireActivity(), CreateTestActivity::class.java)
                intent.putExtra("numberQuiz", strNumberQuiz)
                startActivity(intent)
            }
        }

        shareWithFacebook.setOnClickListener {
            val intent: Intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.data = Uri.parse("https://www.facebook.com")
            startActivity(intent)
        }

        shareWithMail.setOnClickListener {
            val intent: Intent = Intent()
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
        val focusable: Boolean = true

        val popupWindow: PopupWindow = PopupWindow(popUpView, width, height, focusable)
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