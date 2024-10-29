package com.example.simplelistnumber
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var buttonShow: Button
    private lateinit var listViewResult: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ các view từ XML
        editText = findViewById(R.id.editText)
        radioGroup = findViewById(R.id.radioGroup)
        buttonShow = findViewById(R.id.buttonshow)
        listViewResult = findViewById(R.id.listviewresult)

        buttonShow.setOnClickListener {
            // Lấy giá trị từ EditText và kiểm tra tính hợp lệ
            val inputText = editText.text.toString()
            if (inputText.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập một số nguyên dương", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val n = inputText.toIntOrNull()
            if (n == null || n < 0) {
                Toast.makeText(this, "Vui lòng nhập một số nguyên dương hợp lệ", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val results = mutableListOf<Int>()
            when (radioGroup.checkedRadioButtonId) {
                R.id.even -> {
                    // Số chẵn từ 0 đến n
                    for (i in 0..n step 2) {
                        results.add(i)
                    }
                }
                R.id.odd -> {
                    // Số lẻ từ 1 đến n
                    for (i in 1..n step 2) {
                        results.add(i)
                    }
                }
                R.id.square -> {
                    // Số chính phương từ 0 đến n
                    var i = 0
                    while (i * i <= n) {
                        results.add(i * i)
                        i++
                    }
                }
                else -> {
                    Toast.makeText(this, "Vui lòng chọn loại số", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            // Hiển thị danh sách kết quả trong ListView
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, results)
            listViewResult.adapter = adapter
        }
    }
}
