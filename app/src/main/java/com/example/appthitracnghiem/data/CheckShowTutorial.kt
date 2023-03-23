package com.example.appthitracnghiem.data

import android.content.Context
import android.content.SharedPreferences

class CheckShowTutorial(val context : Context){
    var MY_SHARED_PREFERENCES : String ="MY_SHARED_PREFERENCES"

    fun putBooleanValue(key : String,value : Boolean){
        val sharedPreferences : SharedPreferences = context.getSharedPreferences("MY_SHARED_PREFERENCES",0)
        val editor : SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBooleanValue(key : String) : Boolean{
        val sharedPreferences : SharedPreferences = context.getSharedPreferences("MY_SHARED_PREFERENCES",0)
        return sharedPreferences.getBoolean(key,false)
    }
}