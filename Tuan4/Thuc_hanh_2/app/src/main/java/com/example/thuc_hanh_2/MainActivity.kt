package com.example.thuc_hanh_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.thuc_hanh_2.ui.theme.Thuc_hanh_2Theme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Thuc_hanh_2Theme {
                val navController = rememberNavController()
                val viewModel: OnboardingViewModel = viewModel()

                NavHost(navController, startDestination = "home") {
                    composable("home") {
                        SplashHomeScreen(navController = navController)
                    }
                    composable("onboarding") {
                        OnboardingScreen(viewModel = viewModel) {
                            navController.navigate("home") {
                                popUpTo("onboarding") { inclusive = false }
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun SplashHomeScreen(navController: NavHostController) {
        LaunchedEffect(Unit) {
            delay(2000)
            navController.navigate("onboarding") {
                popUpTo("home") { inclusive = true }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_gtvt),
                contentDescription = "UTH logo",
                modifier = Modifier.size(150.dp)
            )

            Text(
                text = "UTH SmartTasks",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2196F3),
                modifier = Modifier.padding(top = 30.dp)
            )
        }
    }

}
