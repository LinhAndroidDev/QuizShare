package com.example.appthitracnghiem.ui.department

/**
 * Fragment arguments for department → subject → exam list navigation (replaces prefs [TYPE] + [ID_DEPARTMENT]).
 */
object DepartmentNavExtras {
    /** Single department to highlight/filter; use -1 to show all departments. */
    const val ARG_DEPARTMENT_ID = "department_nav_department_id"

    /**
     * List source type for exam list API (`RequestListExam.type`) and topic UI mode wiring.
     * Also passed in the subject → [com.example.appthitracnghiem.ui.department.listtest.FragmentListTest] bundle.
     */
    const val ARG_LIST_SOURCE_TYPE = "department_nav_list_source_type"
}
