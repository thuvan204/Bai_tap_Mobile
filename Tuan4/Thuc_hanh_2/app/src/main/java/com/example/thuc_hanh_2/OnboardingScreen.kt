package com.example.thuc_hanh_2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel,
    onDone: () -> Unit
) {
    val page = viewModel.currentPage.value
    val item = viewModel.items[page]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp)
            .padding(all = 10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(top = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = painterResource(id = item.pathImage),
                contentDescription = "Jetpack Compose Icon",
                modifier = Modifier.size(250.dp)
            )

            Text(
                text = item.title,
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 30.dp),
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Text(
                text = item.description,
                fontSize = 13.sp,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 10.dp, end = 10.dp),
                textAlign = TextAlign.Center
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            if (page > 0) {
                Button(
                    onClick = { viewModel.previousPage() },
                    modifier = Modifier
                        .size(50.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2196F3),
                        contentColor = Color.White
                    ),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.backleft),
                        contentDescription = "Back",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Button(
                onClick = {
                    if (page == viewModel.items.lastIndex) {
                        onDone()
                    } else {
                        viewModel.nextPage()
                    }
                },
                modifier = Modifier
                    .weight(2f)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2196F3),
                    contentColor = Color.White
                )
            ) {
                Text(if (page == viewModel.items.lastIndex) "Get Started" else "Next")
            }
        }

    }
}