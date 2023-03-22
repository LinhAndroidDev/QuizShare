package com.example.appthitracnghiem.ui.base

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment(), IOnFragmentBackListener{
    override fun onFragmentBack(): Boolean {
        return false
    }

}