package com.example.appthitracnghiem.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appthitracnghiem.utils.PreferenceUtil

open class BaseViewModel : ViewModel() {
    lateinit var mPreferenceUtil: PreferenceUtil
    val errorApiLiveData = MutableLiveData<String>()
}