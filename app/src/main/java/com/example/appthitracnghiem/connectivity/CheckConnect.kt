@file:Suppress("DEPRECATION")

package com.example.appthitracnghiem.connectivity

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast

object CheckConnect {
    @SuppressLint("SuspiciousIndentation")
    fun haveNetworkConnected(context: Context): Boolean {
        var haveConnectedWifi = false
        var haveConnectedMobile = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfos = connectivityManager.allNetworkInfo
        for (ni in networkInfos) {
            if (ni.typeName.equals("WIFI", ignoreCase = true) && ni.isConnected)
                haveConnectedWifi = true
            if (ni.typeName.equals("MOBILE", ignoreCase = true) && ni.isConnected)
                haveConnectedMobile = true
        }
        return haveConnectedWifi || haveConnectedMobile
    }

    fun showToastShort(context: Context?, thongbao: String?) {
        Toast.makeText(context, thongbao, Toast.LENGTH_LONG).show()
    }
}