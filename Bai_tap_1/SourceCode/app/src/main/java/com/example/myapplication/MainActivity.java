package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_th2);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText etxt = findViewById(R.id.editTextTextEmailAddress);
        Button check = findViewById(R.id.button);
        TextView sub = findViewById(R.id.subset);

        check.setOnClickListener(v -> {
            String text = etxt.getText().toString().trim();

            if (!text.contains("@")) {
                sub.setText("Email phải chứa ký tự '@'");
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(text).matches()) {
                sub.setText("Email không hợp lệ");
            } else {
                sub.setText("Email hợp lệ");
            }
        });
    }
}
