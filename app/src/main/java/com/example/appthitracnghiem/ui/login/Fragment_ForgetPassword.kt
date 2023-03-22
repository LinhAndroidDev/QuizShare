package com.example.appthitracnghiem.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment__forget_password.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment_ForgetPassword.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment_ForgetPassword : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sendTo.setOnClickListener {
            val edtEmail : String = emailForgetPassword.text.toString()
            val bundle : Bundle = Bundle()
            bundle.putString("email",edtEmail)
            val fragmentCreatepassword : Fragment_CreatePassword = Fragment_CreatePassword()
            val fm : FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            fm.setCustomAnimations(R.anim.animation_scale_enter_right,R.anim.animation_scale_exit_left,R.anim.animation_scale_enter_left,R.anim.animation_scale_exit_right)
            fm.addToBackStack("Fragment_CreatePassword")
            fm.replace(R.id.loginContainerID,fragmentCreatepassword).commit()
            fragmentCreatepassword.arguments = bundle
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ForgetPassword","onCreate")
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        Log.d("ForgetPassword","onCreateView")
        return inflater.inflate(R.layout.fragment__forget_password, container, false)
    }

    override fun onFragmentBack(): Boolean {

        return false
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragment_ForgetPassword.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment_ForgetPassword().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}