package com.example.appthitracnghiem.ui.home.profile.setting

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.ui.EmptyViewModel
import com.example.appthitracnghiem.ui.base.BaseFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_setting.*
import kotlinx.android.synthetic.main.fragment_setting_new_password.*
import kotlinx.android.synthetic.main.fragment_update_infor.*

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentUpdateInfor.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("DEPRECATION")
class FragmentUpdateInfor : BaseFragment<EmptyViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Picasso.get().load("https://img2.thuthuatphanmem.vn/uploads/2019/01/04/hinh-anh-dep-co-gai-de-thuong_025103410.jpg")
            .placeholder(R.drawable.loadimage)
            .error(R.drawable.icon_error)
            .into(avatarUpdateInfo)

        click()

        setText()
    }

    private fun click() {
        backUpdateInfo.setOnClickListener {
            activity?.onBackPressed()
        }

        changeAvatar.setOnClickListener {
            val intent: Intent = Intent(requireActivity(), ChangeAvatarActivity::class.java)
            startActivity(intent)
        }
    }

    /** set font*/
    private fun setText() {
        val semibold: Typeface? =
            ResourcesCompat.getFont(requireActivity(), R.font.svn_gilroy_semibold)
        txtCapNhatThongTin.typeface = semibold
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_infor, container, false)
    }

    override fun onFragmentBack(): Boolean {
        return false
    }
}