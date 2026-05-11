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
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.databinding.FragmentTopicBinding
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.exercise.exercise.ExamActivity
import com.example.appthitracnghiem.ui.exercise.ExamSessionExtras
import com.example.appthitracnghiem.utils.PreferenceKey
import com.example.appthitracnghiem.utils.loadNetworkImage
import java.text.SimpleDateFormat
import java.util.*
import dagger.hilt.android.AndroidEntryPoint
import androidx.core.content.edit

@Suppress("DEPRECATION")
@AndroidEntryPoint
class FragmentTopic : BaseFragment<TopicViewModel>() {
    private var _binding: FragmentTopicBinding? = null
    private val binding get() = _binding!!

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
            binding.infoTopic.visibility = View.GONE
            binding.memoryTopic.visibility = View.GONE
        }else if(type == 1){
            binding.infoTopic.visibility = View.VISIBLE
            binding.memoryTopic.visibility = View.VISIBLE
            binding.nameTopic.text = name
            binding.avtTopic.loadNetworkImage(
                avt,
                emptyUrlRes = R.drawable.logo6,
                errorRes = R.drawable.logo6,
            )
        }

        val loading = ProgressDialog(requireActivity())
        loading.setTitle(getString(R.string.dialog_title_notice))
        loading.setMessage(getString(R.string.loading_please_wait))
        viewModel.isLoadingLiveData.observe(viewLifecycleOwner){
            if(it){
                loading.show()
            }else{
                loading.dismiss()
            }
        }

        viewModel.isSuccessfulLiveData.observe(viewLifecycleOwner){ isSuccessful->
            if(isSuccessful){
                binding.layoutMemoryTopic.visibility = View.VISIBLE
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    binding.layoutTopic.setRenderEffect(
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

        binding.backTopic.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.doTestNow.setOnClickListener {
            val sdf = SimpleDateFormat("yyyy/MM/dd hh:mm:ss")
            val currentDate = sdf.format(Date()).toString()
            viewModel.mPreferenceUtil.defaultPref()
                .edit {
                    putString(PreferenceKey.START_DO_TEST, currentDate)
                }
            val examId = requireArguments().getInt(ExamSessionExtras.ARG_EXAM_ID, 0)
            val timeMinutes = requireArguments().getInt(ExamSessionExtras.ARG_TIME_MINUTES, 0)
            val intent = Intent(requireActivity(), ExamActivity::class.java).apply {
                putExtra(ExamSessionExtras.INTENT_EXAM_ID, examId)
                putExtra(ExamSessionExtras.INTENT_TIME_MINUTES, timeMinutes)
            }
            startActivity(intent)
        }

        binding.memoryTopic.setOnClickListener {
            val userId = viewModel.mPreferenceUtil.defaultPref()
                .getInt(PreferenceKey.USER_ID, 0)
            val examId = requireArguments().getInt(ExamSessionExtras.ARG_EXAM_ID, 0)
            viewModel.saveExam(RequestSaveExam(userId, examId))
        }

        binding.backMemoryTopic.setOnClickListener {
            binding.layoutMemoryTopic.visibility = View.GONE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                binding.layoutTopic.setRenderEffect(null)
            }
        }
    }

    override fun onFragmentBack(): Boolean {
        return true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTopicBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}