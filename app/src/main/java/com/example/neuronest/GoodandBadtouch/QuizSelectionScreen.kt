package com.example.neuronest.GoodandBadtouch

import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.neuronest.R

@Composable
fun QuizSelectionScreen(navController: NavHostController) {
    quizselectionscreen(
        navController = navController,
        backgroundImage = R.drawable.dolphin,
        titleText = "Good and Bad Touch",
        onFirstButtonClick = { navController.navigate("DragandDropQuiz") },
        onSecondButtonClick = { navController.navigate("MatchQuiz") }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun quizselectionscreen(
    navController: NavHostController,
    backgroundImage: Int,
    titleText: String,
    onFirstButtonClick: () -> Unit,
    onSecondButtonClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "NeuroNest",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF3F51B5),
                    titleContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Image(
                painter = painterResource(id = backgroundImage),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = titleText,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontSize = 65.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 65.sp
                    ),
                    textAlign = TextAlign.Center,
                    color = Color(0xFF3F51B5)
                )


                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    onClick = onFirstButtonClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF2196F3))
                ) {
                    Text(text = "Drag And Drop", fontSize = 25.sp, fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = onSecondButtonClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF2196F3))
                ) {
                    Text(text = "Match The Pair ", fontSize = 25.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }
            }
        }
    }
}

