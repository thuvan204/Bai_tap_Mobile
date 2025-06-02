package com.example.myapplication.ui.screens
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment

import androidx.compose.ui.unit.sp

@Composable
fun ComponentItem(title: String, description: String, onItemClick: () -> Unit,backgroundColor: Color = Color(0xFF66CCCC)) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(backgroundColor, shape = RoundedCornerShape(8.dp))
            .clickable { onItemClick() }
            .padding(8.dp)
    ) {
        Text(text = title, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        Text(text = description, fontSize = 12.sp)
    }
}

@Composable
fun ComponentSection(sectionTitle: String, items: List<Pair<String, String>>, onItemClick: (String) -> Unit) {
    Column(modifier = Modifier.padding(vertical = 10.dp)) {
        Text(
            text = sectionTitle,
            color = Color(0xFF00CCCC),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 4.dp)

        )
        items.forEach { (title, description) ->
            val bgColor = if (title == "Tự tìm hiểu") Color(0xFF99FFFF) else Color(0xFF66CCCC)
            ComponentItem(title = title, description = description, onItemClick = { onItemClick(title) },backgroundColor = bgColor)
        }
    }
}

@Composable
fun ComponentScreen(
    onTextClick: () -> Unit,
    onImageClick: () -> Unit,
    onTextFieldClick: () -> Unit,
    onPasswordClick: () -> Unit,
    onRowClick: () -> Unit,
    onColumnClick: () -> Unit,
    onMoreClick: () -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
            .verticalScroll(scrollState)
    ) {
        Spacer(modifier = Modifier.width(14.dp))
        Text(
            text = "UI Components List",
            fontSize = 20.sp,
            color = Color(0xFF33CCCC),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )


        ComponentSection(
            sectionTitle = "Display",
            items = listOf(
                "Text" to "Displays text",
                "Image" to "Displays an image",

            ),
            onItemClick = { title ->
                when (title) {
                    "Text" -> onTextClick()
                    "Image" -> onImageClick()
                }
            }
        )

        ComponentSection(
            sectionTitle = "Input",
            items = listOf(
                "TextField" to "Input field for text",
                "PasswordField" to "Input field for passwords"
            ),
            onItemClick = {title ->
                when (title) {
                    "TextField" -> onTextFieldClick()
                    "PasswordField" -> onPasswordClick()
                }
            }
        )

        ComponentSection(
            sectionTitle = "Layout",
            items = listOf(
                "Column" to "Arrange elements vertically",
                "Row" to "Arrange elements horizontally",
                "Box" to "Stack elements",
                "Tự tìm hiểu" to "Tìm ra tất cả các thành phần UI cơ bản "
            ),
            onItemClick = {title ->
                when (title) {
                    "Row"->onRowClick()
                    "Column"->onColumnClick()
                    "Tự tìm hiểu"->onMoreClick()
                }
            }
        )
    }
}



@Preview(showBackground = true, widthDp = 360, heightDp = 740)
@Composable
fun PreviewComponentScreen() {
    ComponentScreen(
        onTextClick = {},
        onImageClick = {},
        onTextFieldClick = {},
        onPasswordClick = {},
        onRowClick = {},
        onColumnClick = {},
        onMoreClick = {}
    )
}



