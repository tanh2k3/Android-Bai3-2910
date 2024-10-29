package com.example.bai3

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private lateinit var studentRecyclerView: RecyclerView
    private lateinit var studentAdapter: StudentAdapter
    private lateinit var searchEditText: EditText

    // Tạo danh sách sinh viên mẫu
    private val students = listOf(
        Student("Nguyen Van A", "12345"),
        Student("Tran Thi B", "67890"),
        Student("Le Van C", "11223"),
        Student("Pham Thi D", "44556")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        studentRecyclerView = findViewById(R.id.studentRecyclerView)
        searchEditText = findViewById(R.id.searchEditText)

        // Khởi tạo adapter và RecyclerView
        studentAdapter = StudentAdapter(students)
        studentRecyclerView.layoutManager = LinearLayoutManager(this)
        studentRecyclerView.adapter = studentAdapter

        // Thêm TextWatcher cho EditText để lắng nghe thay đổi từ khóa tìm kiếm
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val keyword = s.toString()
                filterList(keyword)
            }
        })
    }

    // Hàm lọc danh sách sinh viên dựa trên từ khóa
    private fun filterList(keyword: String) {
        val filteredStudents = if (keyword.length > 2) {
            students.filter {
                it.name.contains(keyword, ignoreCase = true) || it.mssv.contains(keyword)
            }
        } else {
            students // Hiện toàn bộ danh sách nếu từ khóa ít hơn 3 ký tự
        }

        studentAdapter.updateList(filteredStudents)
    }
}
