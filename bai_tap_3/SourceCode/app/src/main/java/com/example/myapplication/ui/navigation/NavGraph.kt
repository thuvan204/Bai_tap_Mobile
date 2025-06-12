package com.example.myapplication.ui.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.ui.screens.*

import com.example.myapplication.ui.screens.WelcomeScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") {
            WelcomeScreen(
                onReadyClick = {
                    navController.navigate("component")
                }
            )
        }
        composable("component") {
            ComponentScreen(
                onTextClick = {
                    navController.navigate("text_detail")
                },
                onImageClick = {
                    navController.navigate("image")
                },
                onTextFieldClick = {
                    navController.navigate("text_field")
                },
                onPasswordClick = {
                    navController.navigate("password")
                },
                onRowClick = {
                    navController.navigate("row")
                },
                onColumnClick = {
                    navController.navigate("column")
                },
                onMoreClick = {
                    navController.navigate("more")
                }
            )
        }

        composable("text_detail") {
            TextDetailScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable("image") {
            ImageScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable("text_field") {
            TextField(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable("password") {
            PasswordScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable("row") {
            RowLayout(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable("column") {
            ColumnLayout(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable("more") {
            More(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }

}