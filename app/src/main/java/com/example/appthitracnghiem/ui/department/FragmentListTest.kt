package com.example.appthitracnghiem.ui.department

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.appthitracnghiem.R

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentListTest.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentListTest : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_test, container, false)
    }
}