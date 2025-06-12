package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.model.Book
import com.example.myapplication.model.Student

@Composable
fun LibraryScreen() {
    val books = remember {
        mutableStateListOf(
            Book(1, "Sách 01"),
            Book(2, "Sách 02"),
            Book(3, "Sách 03")
        )
    }

    val students = remember {
        mutableStateListOf(
            Student(1, "Nguyen Van A", mutableListOf(Book(1, "Sách 01", true), Book(2, "Sách 02", true))),
            Student(2, "Nguyen Thi B", mutableListOf(Book(3, "Sách 03", true))),
            Student(3, "Nguyen Van C", mutableListOf())
        )
    }

    var selectedStudentIndex by remember { mutableStateOf(0) }
    val selectedStudent = students[selectedStudentIndex]

    // Biến state theo dõi danh sách sách mượn đang hiển thị
    var borrowedBooks by remember { mutableStateOf(selectedStudent.borrowedBooks.toList()) }
    var showAddDialog by remember { mutableStateOf(false) }

    // Cập nhật khi chọn sinh viên mới
    LaunchedEffect(selectedStudentIndex) {
        borrowedBooks = selectedStudent.borrowedBooks.toList()
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.White)
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Hệ thống", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Text("Quản lý Thư viện", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        }
        Spacer(modifier = Modifier.height(25.dp))

        Text("Sinh viên:", fontWeight = FontWeight.Bold, color = Color.Black)
        DropdownMenuStudent(
            selectedIndex = selectedStudentIndex,
            students = students,
            selectedStudent = selectedStudent,
            onSelect = { index ->
                selectedStudentIndex = index
            },
            onChangeBooks = {
                // Cập nhật borrowedBooks theo sách đang được chọn trong danh sách sinh viên
                borrowedBooks = selectedStudent.borrowedBooks.filter { it.isSelected.value }
            },
            books = books,
        )

        Spacer(modifier = Modifier.height(20.dp))
        Text("Danh sách sách:", fontWeight = FontWeight.Bold, color = Color.Black)

        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFFFFFFCC))
                .padding(10.dp)
        ) {
            if (borrowedBooks.isEmpty()) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Bạn chưa mượn quyển sách nào",
                            fontSize = 14.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Nhấn 'Thêm' để bắt đầu hành trình đọc sách!",
                            fontSize = 14.sp,
                            color = Color.Black,
                        )
                    }
                }
            } else {
                items(borrowedBooks) { book ->
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
                            .padding(horizontal = 8.dp, vertical = 6.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = book.isSelected.value,
                                onCheckedChange = { checked -> book.isSelected.value = checked },
                                modifier = Modifier.padding(end = 4.dp),
                                colors = CheckboxDefaults.colors(
                                    checkedColor = Color.White,
                                    uncheckedColor = Color.Gray,
                                    checkmarkColor = Color.Red
                            )
                            )
                            Text(book.title, fontSize = 14.sp, color = Color.Black)
                        }
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }


        Spacer(modifier = Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Spacer(modifier = Modifier.height(4.dp).weight(1f))

            Button(
                modifier = Modifier.weight(2f),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF99CC),
                    contentColor = Color.White
                ),
                onClick = {
                    showAddDialog = true
                }
            ) {
                Text("Thêm", color = Color.White)
            }

            // Dialog thêm sách
            if (showAddDialog) {
                AddBooksDialog(
                    allBooks = books,
                    borrowedBooks = borrowedBooks,
                    onDismiss = { showAddDialog = false },
                    onConfirm = { selectedBooks ->
                        // Thêm sách được chọn vào borrowedBooks và selectedStudent.borrowedBooks
                        selectedStudent.borrowedBooks.addAll(selectedBooks)
                        borrowedBooks = selectedStudent.borrowedBooks.toList()
                        showAddDialog = false
                    }
                )
            }

            Spacer(modifier = Modifier.height(4.dp).weight(1f))
        }
    }
}

@Composable
fun DropdownMenuStudent(
    selectedIndex: Int,
    students: List<Student>,
    selectedStudent: Student,
    onSelect: (Int) -> Unit,
    onChangeBooks: () -> Unit,
    books: List<Book>
) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
                .padding(horizontal = 12.dp, vertical = 8.dp)
                .width(200.dp)
                .height(25.dp)
                .weight(4f)
                .clickable { expanded = true }
        ) {
            Text(selectedStudent.name, fontWeight = FontWeight.Bold, color = Color.Black)
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.width(200.dp)
            ) {
                students.forEachIndexed { index, student ->
                    DropdownMenuItem(
                        text = { Text(student.name) },
                        onClick = {
                            onSelect(index)
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        Button(
            modifier = Modifier.weight(2f),
            shape = RoundedCornerShape(6.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFF99CC),
                contentColor = Color.White
            ),
            onClick = {
                // Lọc lại danh sách sách mượn: chỉ giữ lại sách được check
                val filteredBooks = selectedStudent.borrowedBooks.filter { it.isSelected.value }

                selectedStudent.borrowedBooks.clear()
                selectedStudent.borrowedBooks.addAll(filteredBooks)

                onChangeBooks()
            }
        ) {
            Text(
                text = "Thay đổi",
                color = Color.White,
                fontSize = 12.5.sp,
                fontWeight = FontWeight.Bold
            )

        }
    }

}
