package com.example.appthitracnghiem.ui.home.createtest

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
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.fragment_create_test.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.layout_bottom_share.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentCreateTest.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("DEPRECATION")
class FragmentCreateTest : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val GALLERY_RED_CODE: Int = 1000

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setText()

        setBottomShare()

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
            val strNumberQuiz : String = edtNumberQuiz.text.toString()
            if(strNumberQuiz.isEmpty()){
                Toast.makeText(requireActivity(),"Bạn chưa nhập câu hỏi số",Toast.LENGTH_SHORT).show()
            }else{
                val intent: Intent = Intent(requireActivity(), CreateTestActivity::class.java)
                intent.putExtra("numberQuiz",strNumberQuiz)
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
    private fun setBottomShare() {
        layoutBottomShare.setOnTouchListener { v, event -> true }
        var bottomShareBehavior = BottomSheetBehavior.from(layoutBottomShare)
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentCreateTest.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentCreateTest().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}