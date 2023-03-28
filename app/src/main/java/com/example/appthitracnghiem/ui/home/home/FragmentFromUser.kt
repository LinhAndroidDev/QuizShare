package com.example.appthitracnghiem.ui.home.home

import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import com.example.appthitracnghiem.R
import kotlinx.android.synthetic.main.fragment_from_user.*
import kotlinx.android.synthetic.main.fragment_system.*

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentFromUser.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentFromUser : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setText()
    }

    private fun setText() {
        val semibold: Typeface? =
            ResourcesCompat.getFont(requireActivity(), R.font.svn_gilroy_semibold)
        textQuizHomeFromUser.typeface = semibold
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_from_user, container, false)
    }
}