package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.myapplication.ui.MainApp
import com.example.myapplication.ui.theme.MyApplicationTheme

sealed class Screen(val title: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    object QuanLy : Screen("Quản lý", Icons.Default.Home)
    object DSSach : Screen("DS Sách", Icons.Default.List)
    object SinhVien : Screen("Sinh viên", Icons.Default.Person)
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Đặt màu status bar và navigation bar
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = android.graphics.Color.WHITE
        window.navigationBarColor = android.graphics.Color.WHITE

        // Tùy chỉnh icon màu status bar cho đủ sáng/tối
        val insetsController = WindowInsetsControllerCompat(window, window.decorView)
        insetsController.isAppearanceLightStatusBars = true  // icon status bar màu tối cho nền sáng
        insetsController.isAppearanceLightNavigationBars = true  // icon navigation bar màu tối cho nền sáng

        setContent {
            MyApplicationTheme {
                MainApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainApp() {
    MainApp()
}