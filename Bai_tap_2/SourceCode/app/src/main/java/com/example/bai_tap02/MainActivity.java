package com.example.bai_tap02;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    EditText edtHoTen, edtTuoi;
    Button btnKiemTra;
    TextView txtKetQua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtHoTen = findViewById(R.id.edtHoTen);
        edtTuoi = findViewById(R.id.edtTuoi);
        btnKiemTra = findViewById(R.id.btnKiemTra);
        txtKetQua = findViewById(R.id.txtKetQua);

        btnKiemTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = edtHoTen.getText().toString().trim();
                String tuoiStr = edtTuoi.getText().toString().trim();

                if (ten.isEmpty() || tuoiStr.isEmpty()) {
                    txtKetQua.setText("Vui lòng nhập đầy đủ thông tin.");
                    return;
                }

                int tuoi = Integer.parseInt(tuoiStr);
                String PhanBiet;

                if (tuoi > 65) {
                    PhanBiet = "Người già";
                } else if (tuoi >= 6) {
                    PhanBiet = "Người lớn";
                } else if (tuoi >= 2) {
                    PhanBiet = "Trẻ em";
                } else {
                    PhanBiet = "Em bé";
                }

                txtKetQua.setText(ten + " là: " + PhanBiet );
            }
        });
    }
}
