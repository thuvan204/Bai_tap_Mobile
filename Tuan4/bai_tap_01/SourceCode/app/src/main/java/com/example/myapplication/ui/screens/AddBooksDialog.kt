package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.myapplication.model.Book

@Composable
fun AddBooksDialog(
    allBooks: List<Book>,
    borrowedBooks: List<Book>,
    onDismiss: () -> Unit,
    onConfirm: (List<Book>) -> Unit,
) {
    val notBorrowedBooks = remember(allBooks, borrowedBooks) {
        allBooks.filter { book ->
            borrowedBooks.none { it.id == book.id }
        }.map { book ->
            Book(book.id, book.title, isSelectedBoolean = false)
        }
    }

    val selectedBooksState = remember { mutableStateListOf<Book>() }

    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(16.dp)
        ) {
            Text(
                text = "Thêm sách",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn(
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
                    .padding(10.dp)
            ) {
                if (notBorrowedBooks.isEmpty()) {
                    item {
                        Text("Không còn sách để thêm", color = Color.Black)
                    }
                } else {
                    items(notBorrowedBooks) { book ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 4.dp, vertical = 2.dp)
                                .shadow(
                                    elevation = 4.dp,
                                    shape = RoundedCornerShape(25.dp),
                                    clip = true
                                )
                                .background(Color.White, shape = RoundedCornerShape(8.dp))
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                                    .clickable {
                                        book.isSelected.value = !book.isSelected.value
                                        if (book.isSelected.value) {
                                            selectedBooksState.add(book)
                                        } else {
                                            selectedBooksState.remove(book)
                                        }
                                    }
                            ) {
                                Checkbox(
                                    checked = book.isSelected.value,
                                    onCheckedChange = { checked ->
                                        book.isSelected.value = checked
                                        if (checked) {
                                            selectedBooksState.add(book)
                                        } else {
                                            selectedBooksState.remove(book)
                                        }
                                    },
                                    modifier = Modifier.padding(end = 8.dp),
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = Color.White,
                                        uncheckedColor = Color.Gray,
                                        checkmarkColor = Color.Red
                                    )
                                )
                                Text(book.title, fontSize = 14.sp, color = Color.Black)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF99CC),
                        contentColor = Color.White
                    )
                ) {
                    Text("Hủy")
                }

                Button(
                    onClick = {
                        onConfirm(selectedBooksState.toList())
                    },
                    enabled = selectedBooksState.isNotEmpty(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF99CC),
                        contentColor = Color.White
                    )
                ) {
                    Text("Xác nhận")
                }
            }
        }
    }
}
