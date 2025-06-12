package com.example.myapplication.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.myapplication.Screen
import com.example.myapplication.ui.screens.BookListScreen
import com.example.myapplication.ui.screens.LibraryScreen
import com.example.myapplication.ui.screens.StudentListScreen
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Divider
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.ui.unit.dp

@Composable
fun MainApp() {
    var selectedScreen by remember { mutableStateOf<Screen>(Screen.QuanLy) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    )
    {
        Scaffold(

            bottomBar = {
                Column {
                    Divider(
                        color = Color(0x33000000),
                        thickness = 1.5.dp
                    )
                    NavigationBar(
                        containerColor = Color.White
                    )
                    {
                        listOf(Screen.QuanLy, Screen.DSSach, Screen.SinhVien).forEach { screen ->
                            NavigationBarItem(
                                selected = screen == selectedScreen,
                                onClick = { selectedScreen = screen },
                                icon = { Icon(screen.icon, contentDescription = screen.title) },
                                label = { Text(screen.title) },
                                alwaysShowLabel = true,
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = Color(0xFF3F51B5),
                                    unselectedIconColor = Color.Gray,
                                    selectedTextColor = Color(0xFF3F51B5),
                                    unselectedTextColor = Color.Gray,
                                    indicatorColor = Color.Transparent // <-- bỏ màu nền được chọn
                                )
                            )
                        }
                    }
                }
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .background(Color.White)
            ) {
                when (selectedScreen) {
                    is Screen.QuanLy -> LibraryScreen()
                    is Screen.DSSach -> BookListScreen()
                    is Screen.SinhVien -> StudentListScreen()
                }
            }
        }
    }
}