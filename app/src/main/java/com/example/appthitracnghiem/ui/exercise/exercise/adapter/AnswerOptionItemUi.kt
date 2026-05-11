package com.example.appthitracnghiem.ui.exercise.exercise.adapter

import android.util.TypedValue
import android.view.ViewGroup
import android.widget.TextView

/**
 * Spacing cho từng ô đáp án (màn thi / xem lại), khớp code cũ dùng px trong [android.view.ViewGroup.MarginLayoutParams.setMargins]
 * và [TextView.setPadding].
 */
object AnswerOptionItemUi {

    fun applyLegacyPxSpacing(textView: TextView) {
        val dm = textView.resources.displayMetrics
        fun px(value: Float): Int =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, value, dm).toInt()

        val margin = px(16f)
        val padH = px(32f)
        val padV = px(24f)
        textView.setPadding(padH, padV, padH, padV)
        val lineExtraPx =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2f, dm)
        textView.setLineSpacing(lineExtraPx, 1.4f)

        val lp = textView.layoutParams as ViewGroup.MarginLayoutParams
        lp.setMargins(margin, margin, margin, margin)
        textView.layoutParams = lp
    }
}
