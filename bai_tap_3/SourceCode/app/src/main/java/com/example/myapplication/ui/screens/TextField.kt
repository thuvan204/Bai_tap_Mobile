package com.example.myapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

@Composable
fun TextField(onBackClick: () -> Unit) {
    var input by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.back),
                contentDescription = "Back icon",
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterStart)
                    .clickable { onBackClick() }
            )

            Text(
                text = "Text Field",
                fontSize = 22.sp,
                color = Color(0xFF778899),
                fontWeight = FontWeight.Bold,
            )
        }
        Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(vertical = 12.dp))
        
        Spacer(modifier = Modifier.height(100.dp))

        // TextField
        OutlinedTextField(
            value = input,
            textStyle = TextStyle(color = Color.Black),
            onValueChange = { input = it },
            placeholder = { Text("Thông tin nhập") },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()

        )

        Spacer(modifier = Modifier.height(16.dp))
        val displayText = if (input.isEmpty()) {
            "Tự động cập nhật dữ liệu theo textfield"
        } else {
            input
        }

        // Display text
        Text(
            text = displayText,
            color = Color.Gray,
            fontSize = 14.sp
        )
    }
}
@Preview(showBackground = true, widthDp = 360, heightDp = 740)
@Composable
fun PreviewTextField() {
    TextField (onBackClick = {})
}