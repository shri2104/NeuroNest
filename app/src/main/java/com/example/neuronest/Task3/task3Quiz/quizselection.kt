package com.example.neuronest.Task3.task3Quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.neuronest.R
import com.example.neuronest.Task2.selection.socialSelectionScreen2


@Composable
fun task3QuizSelectionScreen(navController: NavHostController) {
    Task3QuizSelectionScreen(
        navController = navController,
        backgroundImage = R.drawable.task1selection2,
        //titleText = "Emotions",
        onFirstButtonClick = { navController.navigate("Task3DrapaandDrop") },
        onSecondButtonClick = { navController.navigate("Task3SelectionQuiz") } ,
       onThirdButtonClick =  { navController.navigate("task3mcqScreen") },
        firstButtonText = "Drag and Drop",
        secondButtonText = "Selection",
        thirdButtonText = "MCQs"
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Task3QuizSelectionScreen(
    navController: NavHostController,
    backgroundImage: Int,
    firstButtonText: String,
    secondButtonText: String,
    thirdButtonText: String,
    onFirstButtonClick: () -> Unit,
    onSecondButtonClick: () -> Unit,
    onThirdButtonClick: () -> Unit
) {
    val babyPink = Color(0xFFFF80A6)
    val deepPink = Color(0xFFE91E63)
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "NeuroNest",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.White,
                        fontSize = 35.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = deepPink,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("Task3SelectionScreen") }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Background Image
            Image(
                painter = painterResource(id = backgroundImage),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            // Buttons
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
                    .padding(top = 250.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = onFirstButtonClick,
                    modifier = Modifier
                        .fillMaxWidth().size(100.dp)
                        .height(60.dp)
                        .padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = babyPink)
                ) {
                    Text(text = firstButtonText, fontSize = 40.sp, fontWeight = FontWeight.Bold)
                }

                Button(
                    onClick = onSecondButtonClick,
                    modifier = Modifier
                        .fillMaxWidth().size(100.dp)
                        .height(60.dp)
                        .padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = babyPink)
                ) {
                    Text(text = secondButtonText, fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }

                Button(
                    onClick = onThirdButtonClick,
                    modifier = Modifier
                        .fillMaxWidth().size(100.dp)
                        .height(60.dp)
                        .padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = babyPink)
                ) {
                    Text(text = thirdButtonText, fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }
            }
        }
    }
}
