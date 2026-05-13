package com.example.appthitracnghiem.ui.exercise

/**
 * Intent extras + fragment arguments for passing exam session without relying on prefs.
 */
object ExamSessionExtras {
    const val INTENT_EXAM_ID = "exam_session_intent_exam_id"
    const val INTENT_TIME_MINUTES = "exam_session_intent_time_minutes"

    /** Start time string for [com.example.appthitracnghiem.data.remote.dto.request.RequestPoint] (was [START_DO_TEST] pref). */
    const val INTENT_EXAM_START_TIMESTAMP = "exam_session_intent_start_timestamp"

    /** History row id for answer review API (was [EXAM_ID_HISTORY] pref). */
    const val INTENT_EXAM_HISTORY_ID = "exam_session_intent_exam_history_id"

    /**
     * Topic screen UI: 0 = hide user/memory header (system), 1 = show (user), -1 = leave default layout.
     */
    const val INTENT_TOPIC_UI_MODE = "exam_session_intent_topic_ui_mode"

    const val INTENT_TOPIC_USER_NAME = "exam_session_intent_topic_user_name"
    const val INTENT_TOPIC_USER_AVATAR = "exam_session_intent_topic_user_avatar"

    const val ARG_EXAM_ID = "exam_session_arg_exam_id"
    const val ARG_TIME_MINUTES = "exam_session_arg_time_minutes"

    const val ARG_EXAM_START_TIMESTAMP = "exam_session_arg_exam_start_timestamp"
    const val ARG_EXAM_HISTORY_ID = "exam_session_arg_exam_history_id"

    const val ARG_TOPIC_UI_MODE = "exam_session_arg_topic_ui_mode"
    const val ARG_TOPIC_USER_NAME = "exam_session_arg_topic_user_name"
    const val ARG_TOPIC_USER_AVATAR = "exam_session_arg_topic_user_avatar"
}
