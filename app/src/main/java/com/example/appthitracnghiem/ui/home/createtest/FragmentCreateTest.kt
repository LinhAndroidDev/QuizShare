package com.example.appthitracnghiem.ui.home.createtest

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.department.listdepartment.ListDepartmentViewModel
import com.example.appthitracnghiem.ui.department.listdepartment.RequestDepartmentInfo
import com.example.appthitracnghiem.ui.home.createtest.adapter.CreateDepartmentAdapter
import com.example.appthitracnghiem.ui.home.createtest.question.CreateTestActivity
import com.example.appthitracnghiem.utils.PreferenceKey
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_create_test.*
import kotlinx.android.synthetic.main.fragment_create_exam.*
import kotlinx.android.synthetic.main.fragment_create_test.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_list_department.*
import kotlinx.android.synthetic.main.layout_bottom_share.*

@Suppress("DEPRECATION")
class FragmentCreateTest : BaseFragment<ListDepartmentViewModel>() {
    private val GALLERY_RED_CODE: Int = 1000
    private var DEPARTMENT_ID: Int = -1
    private var SUBJECT_ID: Int = -1
    private var uriImage = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        (activity as HomeActivity).hideTabBar(scrollCreateTest)

        setBottomShare()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            initUi()
        }

        setText()
    }

    override fun bindData() {
        super.bindData()

        val header = viewModel.mPreferenceUtil.defaultPref()
            .getString(PreferenceKey.AUTHORIZATION, "").toString()
        val userId = viewModel.mPreferenceUtil.defaultPref()
            .getInt(PreferenceKey.USER_ID, 0)
        val requestDepartmentInfo = RequestDepartmentInfo(userId)
        viewModel.getDataDepartmentDetail(header, requestDepartmentInfo)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setStatusBar() {
        val window: Window? = activity?.window
        window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window?.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window?.statusBarColor = ContextCompat.getColor(requireActivity(), R.color.white)

        window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;//  set status text dark
    }

    private fun View.hideKeyboard() {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(Build.VERSION_CODES.M)
    private fun initUi() {

        setStatusBar()

        layoutCreateTestCover.setOnTouchListener { view, _ ->
            view.hideKeyboard()
            clearFocusTextView()
            false
        }

        menuCreateTest.setOnClickListener {
            val popUpView: View = View.inflate(requireActivity(), R.layout.popup_create_test, null)
            showMenuCreate(popUpView, menuCreateTest, 0, -30, Gravity.BOTTOM)
        }

        selectDepartment.setOnClickListener {
            val popUpView: View = View.inflate(requireActivity(), R.layout.popup_select_partment, null)
            val width = ViewGroup.LayoutParams.WRAP_CONTENT
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            val focusable = true
            val popupWindow = PopupWindow(popUpView, width, height, focusable)
            popupWindow.showAsDropDown(selectDepartment, 0, -30, Gravity.BOTTOM)

            val listDepartment: ArrayList<String> = arrayListOf()
            listDepartment.add("Khoa tự nhiên")
            listDepartment.add("Khoa nhạc")
            listDepartment.add("Khoa thể chất")
            listDepartment.add("Khoa truyền thông")

            val createDepartmentAdapter = CreateDepartmentAdapter(listDepartment, requireActivity())
            createDepartmentAdapter.onClickItem = {
                DEPARTMENT_ID = it
                popupWindow.dismiss()
                txtSelectSubject.visibility = View.VISIBLE
                layoutSelectSubject.visibility = View.VISIBLE
            }
            createDepartmentAdapter.getStringItem = {
                edtSelectDepartment.text = it
            }
            val linear = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
            val recyclerView: RecyclerView = popUpView.findViewById(R.id.recycleListTopic)
            recyclerView.layoutManager = linear
            recyclerView.adapter = createDepartmentAdapter
        }

        selectSubject.setOnClickListener {
            val popUpView: View = View.inflate(requireActivity(), R.layout.popup_select_partment, null)
            val width = ViewGroup.LayoutParams.WRAP_CONTENT
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            val focusable = true

            viewModel.listDepartmentLiveData.observe(viewLifecycleOwner){
                if(DEPARTMENT_ID >= it.size){
                    Toast.makeText(requireActivity(),"Khoa chưa có môn",Toast.LENGTH_SHORT).show()
                }else{
                    val popupWindow = PopupWindow(popUpView, width, height, focusable)
                    popupWindow.showAsDropDown(selectSubject, 0, -30, Gravity.BOTTOM)

                    val listDepartment: ArrayList<String> = arrayListOf()
                    for(i in 0 until it[DEPARTMENT_ID].subjects.size){
                        listDepartment.add(it[DEPARTMENT_ID].subjects[i].title)
                    }
                    val createDepartmentAdapter = CreateDepartmentAdapter(listDepartment, requireActivity())
                    createDepartmentAdapter.onClickItem = {
                        SUBJECT_ID = it
                        popupWindow.dismiss()
                        viewModel.mPreferenceUtil.defaultPref().edit()
                            .putInt(PreferenceKey.CREATE_SUBJECT_ID, it+1)
                            .apply()
                    }
                    createDepartmentAdapter.getStringItem = {
                        edtSelectSubject.text = it
                    }
                    val linear = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
                    val recyclerView: RecyclerView = popUpView.findViewById(R.id.recycleListTopic)
                    recyclerView.layoutManager = linear
                    recyclerView.adapter = createDepartmentAdapter
                }
                }

        }

        selectMode.setOnClickListener {
            val popUpView: View = View.inflate(requireActivity(), R.layout.popup_select_mode, null)
            val width = ViewGroup.LayoutParams.WRAP_CONTENT
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            val focusable = true
            val popupWindow = PopupWindow(popUpView, width, height, focusable)
            popupWindow.showAsDropDown(selectMode, 0, -30, Gravity.BOTTOM)

            val public: LinearLayout = popUpView.findViewById(R.id.selectPublic)
            val private: LinearLayout = popUpView.findViewById(R.id.selectPrivate)

            public.setOnClickListener {
                edtSelectLevel.text = "Công khai"
                viewModel.mPreferenceUtil.defaultPref()
                    .edit().putInt(PreferenceKey.CREATE_STATUS, 1)
                    .apply()
                popupWindow.dismiss()
            }

            private.setOnClickListener {
                edtSelectLevel.text = "Riêng tư"
                viewModel.mPreferenceUtil.defaultPref()
                    .edit().putInt(PreferenceKey.CREATE_STATUS, 2)
                    .apply()
                popupWindow.dismiss()
            }
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
                val title: String = edtSelectTitle.text.toString()
                val department: String = edtSelectSubject.text.toString()
                val time: String = edtSelectTime.text.toString()
                val numberQuiz: String = edtSelectNumberQuiz.text.toString()
                val describe: String = edtDescribeQuiz.text.toString()

                if(title.isEmpty() || department.isEmpty() || time.isEmpty() || numberQuiz.isEmpty() || describe.isEmpty()){
                    Toast.makeText(requireActivity(), "Bạn chưa nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
                }else{
                    viewModel.mPreferenceUtil.defaultPref()
                        .edit().putString(PreferenceKey.CREATE_TITLE,title)
                        .apply()
                    viewModel.mPreferenceUtil.defaultPref()
                        .edit().putString(PreferenceKey.CREATE_DEPARTMENT,department)
                        .apply()
                    viewModel.mPreferenceUtil.defaultPref()
                        .edit().putInt(PreferenceKey.TIME_EXAM,time.toInt())
                        .apply()
                    viewModel.mPreferenceUtil.defaultPref()
                        .edit().putString(PreferenceKey.CREATE_DESCRIBE_QUIZ,describe)
                        .apply()
                    if(uriImage.isEmpty()){
                        viewModel.mPreferenceUtil.defaultPref()
                            .edit().putString(PreferenceKey.CREATE_URI_IMAGE_SUBJECT,"")
                            .apply()
                    }else{
                        viewModel.mPreferenceUtil.defaultPref()
                            .edit().putString(PreferenceKey.CREATE_URI_IMAGE_SUBJECT,uriImage)
                            .apply()
                    }
                    val intent = Intent(requireActivity(), CreateTestActivity::class.java)
                    intent.putExtra("number_question",numberQuiz.toInt())
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
                uriImage = data?.data.toString()
            }
        }
    }

    /** show menu add test */
    private fun showMenuCreate(popView: View,anchor: View, x: Int, y: Int, position: Int) {
        val width = ViewGroup.LayoutParams.WRAP_CONTENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        val focusable = true

        val popupWindow = PopupWindow(popView, width, height, focusable)
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
        txtSelectSubject.typeface = semibold
    }

    internal fun scrollTop(){
        scrollCreateTest.post {
            scrollCreateTest.fling(0)
            scrollCreateTest.smoothScrollTo(0, 0)
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

    private fun clearFocusTextView() {
        edtSelectTitle.clearFocus()
        edtSelectTime.clearFocus()
        edtSelectNumberQuiz.clearFocus()
        edtDescribeQuiz.clearFocus()
    }
}