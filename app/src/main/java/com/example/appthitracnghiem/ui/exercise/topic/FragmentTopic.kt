package com.example.appthitracnghiem.ui.exercise.topic

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.exercise.exercise.ExamActivity
import com.example.appthitracnghiem.utils.PreferenceKey
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_link_sheet.*
import kotlinx.android.synthetic.main.fragment_topic.*
import java.text.SimpleDateFormat
import java.util.*

@Suppress("DEPRECATION")
class FragmentTopic : BaseFragment<TopicViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    override fun bindData() {
        super.bindData()

        val name = viewModel.mPreferenceUtil.defaultPref()
            .getString(PreferenceKey.USER_NAME,"")
        val avt = viewModel.mPreferenceUtil.defaultPref()
            .getString(PreferenceKey.USER_AVATAR,"")

        val type = viewModel.mPreferenceUtil.defaultPref()
            .getInt(PreferenceKey.TYPE,-1)
        if(type == 0){
            infoTopic.visibility = View.GONE
            memoryTopic.visibility = View.GONE
        }else if(type == 1){
            infoTopic.visibility = View.VISIBLE
            memoryTopic.visibility = View.VISIBLE
            nameTopic.text = name
            Picasso.get().load(avt)
                .placeholder(R.drawable.loadimage)
                .error(R.drawable.logo6)
                .into(avtTopic)
        }

        val loading = ProgressDialog(requireActivity())
        loading.setTitle("Thông báo")
        loading.setMessage("Please wait...")
        viewModel.isLoadingLiveData.observe(viewLifecycleOwner){
            if(it){
                loading.show()
            }else{
                loading.dismiss()
            }
        }

        viewModel.isSuccessfulLiveData.observe(viewLifecycleOwner){ isSuccessful->
            if(isSuccessful){
                layoutMemoryTopic.visibility = View.VISIBLE
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    layoutTopic.setRenderEffect(
                        RenderEffect.createBlurEffect(
                            50f,
                            50f,
                            Shader.TileMode.MIRROR
                        )
                    )
                }
            }
        }
    }

    private fun setStatusBar() {
        val window: Window? = activity?.window
        window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window?.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window?.statusBarColor = ContextCompat.getColor(requireActivity(), R.color.backgroundIntro)

        val decorView = window?.decorView //set status background black

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            decorView?.systemUiVisibility =
                decorView?.systemUiVisibility?.and(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv())!!
        } //set status text  light
    }

    @SuppressLint("SimpleDateFormat")
    private fun initUi() {

        setStatusBar()

        backTopic.setOnClickListener {
            activity?.onBackPressed()
        }

        doTestNow.setOnClickListener {
            val sdf = SimpleDateFormat("yyyy/MM/dd hh:mm:ss")
            val currentDate = sdf.format(Date()).toString()
            viewModel.mPreferenceUtil.defaultPref()
                .edit().putString(PreferenceKey.START_DO_TEST,currentDate)
                .apply()
            val intent = Intent(requireActivity(), ExamActivity::class.java)
            startActivity(intent)
        }

        memoryTopic.setOnClickListener {
            val header = viewModel.mPreferenceUtil.defaultPref()
                .getString(PreferenceKey.AUTHORIZATION, "").toString()
            val userId = viewModel.mPreferenceUtil.defaultPref()
                .getInt(PreferenceKey.USER_ID, 0)
            val examId = viewModel.mPreferenceUtil.defaultPref()
                .getInt(PreferenceKey.ID_EXAM, 0)
            val requestSaveExam = RequestSaveExam(userId, examId)
            viewModel.saveExam(header, requestSaveExam)
        }

        backMemoryTopic.setOnClickListener {
            layoutMemoryTopic.visibility = View.GONE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                layoutTopic.setRenderEffect(null)
            }
        }
    }

    override fun onFragmentBack(): Boolean {
        return true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_topic, container, false)
    }
}