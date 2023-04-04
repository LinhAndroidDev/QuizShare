package com.example.appthitracnghiem.ui.department.listtest

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appthitracnghiem.R
import com.example.appthitracnghiem.model.Test
import com.example.appthitracnghiem.ui.department.listtest.adapter.TestAdapter
import kotlinx.android.synthetic.main.fragment_list_test.*

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentListTest.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("DEPRECATION")
class FragmentListTest : Fragment(){
    lateinit var listTest: MutableList<Test>
    lateinit var testAdapter: TestAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listTest = mutableListOf()
        listTest.add(Test(1,R.drawable.icon_test_subject,"Đề 1","Toán 12 Trắc nghiệm"))
        listTest.add(Test(2,R.drawable.icon_test_subject,"Đề 2","Toán 12 Trắc nghiệm"))
        listTest.add(Test(3,R.drawable.icon_test_subject,"Đề 3","Toán 12 Trắc nghiệm"))
        listTest.add(Test(4,R.drawable.icon_test_subject,"Đề 4","Toán 12 Trắc nghiệm"))
        listTest.add(Test(5,R.drawable.icon_test_subject,"Đề 5","Toán 12 Trắc nghiệm"))
        listTest.add(Test(6,R.drawable.icon_test_subject,"Đề 6","Toán 12 Trắc nghiệm"))
        listTest.add(Test(7,R.drawable.icon_test_subject,"Đề 7","Toán 12 Trắc nghiệm"))
        listTest.add(Test(8,R.drawable.icon_test_subject,"Đề 8","Toán 12 Trắc nghiệm"))

        testAdapter = TestAdapter(requireActivity(),listTest)

        val linear: LinearLayoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,false)
        recycleListTest.layoutManager = linear
        recycleListTest.adapter = testAdapter

        click()
    }

    private fun click() {
        backTest.setOnClickListener {
            activity?.onBackPressed()
        }

        searchTest.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                testAdapter.filter.filter(p0)
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_test, container, false)
    }
}