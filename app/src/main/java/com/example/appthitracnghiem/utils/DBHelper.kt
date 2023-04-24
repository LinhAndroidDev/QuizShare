package com.example.appthitracnghiem.utils

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context): SQLiteOpenHelper(context,"AnswerQuiz",null,1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table AnswerQuiz (name TEXT primary key, question )")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("drop table if exists AnswerQuiz")
    }

    fun saveUserData(name: String,contact: String): Boolean {
        val p0 = this.writableDatabase
        val cv = ContentValues()
        cv.put("name", name)
        cv.put("contact", contact)
        val result = p0.insert("AnswerQuiz", null, cv)
        return result != (-1).toLong()
    }

    fun getText(): Cursor? {
        val p0 = this.writableDatabase
        return p0.rawQuery("select * from AnswerQuiz", null)
    }
}