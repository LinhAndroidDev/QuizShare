package com.example.appthitracnghiem.ui.intro

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.home.HomeActivity
import com.example.appthitracnghiem.ui.login.LoginActivity
import com.example.appthitracnghiem.ui.register.RegisterActivity
import kotlinx.android.synthetic.main.fragment_intro_login.*

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentIntroLogin.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentIntroLogin : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        click()
    }

    private fun click() {
        loginIntro.setOnClickListener {
            val intent: Intent = Intent(requireActivity(), LoginActivity::class.java)
            startActivity(intent)
        }

        registerIntro.setOnClickListener {
            val intent: Intent = Intent(requireActivity(), RegisterActivity::class.java)
            startActivity(intent)
        }

        toDoLate.setOnClickListener {
            val intent: Intent = Intent(requireActivity(), HomeActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_intro_login, container, false)
    }
}