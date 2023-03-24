package com.example.appthitracnghiem.ui.intro

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.appthitracnghiem.R
import kotlinx.android.synthetic.main.fragment_intro_second.*

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentIntroSecond.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentIntroSecond : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        click()
    }

    private fun click() {
        val semibold: Typeface? =
            ResourcesCompat.getFont(requireActivity(), R.font.svn_gilroy_semibold)
        textIntroSecond.typeface = semibold
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_intro_second, container, false)
    }
}