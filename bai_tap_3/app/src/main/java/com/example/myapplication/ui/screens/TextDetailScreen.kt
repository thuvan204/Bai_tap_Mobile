package com.example.myapplication.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

@Composable
fun TextDetailScreen(onBackClick: () -> Unit) {
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
                text = "Text Detail",
                fontSize = 22.sp,
                color = Color(0xFF999966),
                fontWeight = FontWeight.Bold,
            )
        }

        Divider(color = Color.LightGray, thickness = 1.dp, modifier = Modifier.padding(vertical = 12.dp))

        // Phần nội dung chính
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                buildAnnotatedString {
                    append("The ")
                    withStyle(style = SpanStyle(textDecoration = TextDecoration.LineThrough)) {
                        append("quick ")
                    }
                    withStyle(style = SpanStyle(color = Color(0xFF8D6E63), fontWeight = FontWeight.Bold)) {
                        append("Brown")
                    }
                    append("\nfox j u m p s ")
                    withStyle(style = SpanStyle(color = Color(0xFFCC0000),fontWeight = FontWeight.Bold)) {
                        append("over")
                    }
                    append("\n")
                    withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                        append("the ")
                    }
                    withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                        append("lazy")
                    }
                    append(" dog.")
                },
                fontSize = 32.sp,
                lineHeight = 40.sp,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 740)
@Composable
fun PreviewTextDetailScreen() {
    TextDetailScreen(onBackClick = {})
}
