package com.example.appthitracnghiem.presentation.main

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.connectivity.CheckConnect
import com.example.appthitracnghiem.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        flashScreen()
    }

    private fun flashScreen() {
        val countDownTimer: CountDownTimer = object : CountDownTimer(3000, 3000) {
            override fun onTick(millisUntilFinished: Long) = Unit

            override fun onFinish() {
                if (!isAdded) return
                if (CheckConnect.haveNetworkConnected(requireContext())) {
                    val destination = when {
                        !viewModel.isFirstInstallDone() -> {
                            viewModel.markFirstInstallDone()
                            R.id.introActivityDestination
                        }

                        viewModel.isUserLoggedIn() -> R.id.homeActivityDestination
                        else -> R.id.loginActivityDestination
                    }
                    findNavController().navigate(destination)
                } else {
                    CheckConnect.showToastShort(requireContext(), "Bạn đang ngoại tuyến")
                }
            }
        }
        countDownTimer.start()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
