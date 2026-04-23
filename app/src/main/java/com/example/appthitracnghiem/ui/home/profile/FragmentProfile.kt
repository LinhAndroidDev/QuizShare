package com.example.appthitracnghiem.ui.home.profile

import android.content.Intent
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.databinding.FragmentProfileBinding
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.example.appthitracnghiem.ui.home.profile.setting.SettingActivity
import com.example.appthitracnghiem.utils.PreferenceKey
import com.squareup.picasso.Picasso

@Suppress("DEPRECATION")
class FragmentProfile : BaseFragment<EmptyViewModel>() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        percentColum(30, 80, 60)

        binding.progressNumberDay.apply {
            progressMax = 100f
            setProgressWithAnimation(80f, 2000)
        }

        val avt = viewModel.mPreferenceUtil.defaultPref()
            .getString(PreferenceKey.USER_AVATAR,"")

        if(avt?.isEmpty() == true){
            binding.avatarUserProfile.setImageResource(R.drawable.logo6)
        }else{
            Picasso.get()
                .load(avt)
                .placeholder(R.drawable.loadimage)
                .error(R.drawable.logo6)
                .into(binding.avatarUserProfile)
        }

//        (activity as HomeActivity).hideTabBar(scrollProfile)

        initUi()

    }

    private fun setStatusBar() {
        val window: Window? = activity?.window
        window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window?.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window?.statusBarColor = ContextCompat.getColor(requireActivity(), R.color.white)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        };//  set status text dark
    }

    private fun initUi() {

        setStatusBar()

        binding.backProfile.setOnClickListener {
            activity?.onBackPressed()
        }

        binding.setting.setOnClickListener {
            val intent: Intent = Intent(requireActivity(), SettingActivity::class.java)
            startActivity(intent)
        }

        setText()
    }

    private fun percentColum(math: Int, science: Int, painting: Int) {
        binding.progressMath.progress = math.toFloat()
        binding.progressScience.progress = science.toFloat()
        binding.progressPainting.progress = painting.toFloat()
    }

    private fun setText() {
        binding.txtName.text = viewModel.mPreferenceUtil.defaultPref()
            .getString(PreferenceKey.USER_NAME, "")

        val semibold: Typeface? =
            ResourcesCompat.getFont(requireActivity(), R.font.svn_gilroy_semibold)
        binding.txtName.typeface = semibold
        binding.txtProfile.typeface = semibold
    }

    internal fun scrollTop(){
        binding.scrollProfile.post {
            binding.scrollProfile.fling(0)
            binding.scrollProfile.smoothScrollTo(0, 0)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onFragmentBack(): Boolean {
        return false
    }
}