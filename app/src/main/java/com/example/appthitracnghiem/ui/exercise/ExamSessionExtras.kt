package com.example.appthitracnghiem.ui.exercise

/**
 * Intent extras + fragment arguments for passing exam session without relying on [ID_EXAM] prefs.
 */
object ExamSessionExtras {
    const val INTENT_EXAM_ID = "exam_session_intent_exam_id"
    const val INTENT_TIME_MINUTES = "exam_session_intent_time_minutes"

    const val ARG_EXAM_ID = "exam_session_arg_exam_id"
    const val ARG_TIME_MINUTES = "exam_session_arg_time_minutes"
}
