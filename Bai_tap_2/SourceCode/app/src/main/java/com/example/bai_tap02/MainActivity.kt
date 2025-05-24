package com.example.bai_tap02

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var edtHoTen: EditText
    private lateinit var edtTuoi: EditText
    private lateinit var btnKiemTra: Button
    private lateinit var txtKetQua: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtHoTen = findViewById(R.id.edtHoTen)
        edtTuoi = findViewById(R.id.edtTuoi)
        btnKiemTra = findViewById(R.id.btnKiemTra)
        txtKetQua = findViewById(R.id.txtKetQua)

        btnKiemTra.setOnClickListener {
            val ten = edtHoTen.text.toString().trim()
            val tuoiStr = edtTuoi.text.toString().trim()

            if (ten.isEmpty() || tuoiStr.isEmpty()) {
                txtKetQua.text = "Vui lòng nhập đầy đủ thông tin."
                return@setOnClickListener
            }

            val tuoi = tuoiStr.toInt()
            val phanBiet = when {
                tuoi > 65 -> "Người già"
                tuoi >= 6 -> "Người lớn"
                tuoi >= 2 -> "Trẻ em"
                else -> "Em bé"
            }

            txtKetQua.text = "$ten là: $phanBiet"
        }
    }
}
