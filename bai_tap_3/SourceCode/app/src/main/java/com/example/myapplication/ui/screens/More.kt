package com.example.myapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun More(onBackClick: () -> Unit) {
    var noteText by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
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
                text = "More",
                fontSize = 22.sp,
                color = Color(0xFFCC99FF),
                fontWeight = FontWeight.Bold,
            )
        }

        Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(vertical = 12.dp))
        Spacer(modifier = Modifier.height(16.dp))

        // --- Card chứa thông tin nhập liệu ---

        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Ghi chú", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = noteText,
                    onValueChange = { noteText = it },
                    label = { Text("Nhập ghi chú của bạn") },
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        // --- Carousel (dạng LazyRow) ---
        Text(text = "Danh sách mục:", fontWeight = FontWeight.SemiBold)
        LazyRow(modifier = Modifier.padding(vertical = 8.dp)) {
            items(2) { index ->
                Card(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(120.dp, 80.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Note ${index + 1}",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
        }


        // --- Chips ---
        Row(modifier = Modifier.padding(vertical = 8.dp)) {
            AssistChip(onClick = {}, label = { Text("gợi ý 1") })
            Spacer(modifier = Modifier.width(8.dp))
            FilterChip(selected = true, onClick = {}, label = { Text("gợi ý 2") })
        }

        // --- Switch ---
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Bật thông báo")
            Spacer(modifier = Modifier.weight(1f))
            var checked = remember { mutableStateOf(true) }
            Switch(
                checked = checked.value,
                onCheckedChange = { checked.value = it }
            )
        }
    }
}



@Preview(showBackground = true, widthDp = 360, heightDp = 740)
@Composable
fun PreviewMore() {
    More (onBackClick = {})
}