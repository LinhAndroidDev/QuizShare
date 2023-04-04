package com.example.appthitracnghiem.ui.home.history.test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.appthitracnghiem.R

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentHistoryTopic.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentHistoryTopic : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history_topic, container, false)
    }
}