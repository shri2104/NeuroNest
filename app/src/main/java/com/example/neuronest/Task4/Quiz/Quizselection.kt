package com.example.neuronest.Task4.Quiz
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.neuronest.R
import com.example.neuronest.Task2.selection.socialSelectionScreen2
import com.example.neuronest.Task4.Selection.task4Selection

@Composable
fun task4QuizSelectionScreen(navController: NavHostController) {
    Task4QuizSelectionScreen(
        navController = navController,
        backgroundImage = R.drawable.photo_2025_08_05_00_17_00,
        onFirstButtonClick = { navController.navigate("Task4DragAndDrop") },
        onSecondButtonClick = { navController.navigate("Task4SelectionQuiz") },
        onThirdButtonClick = { navController.navigate("Task4McqScreen") },
        firstButtonText = "Drag and Drop",
        secondButtonText = "Selection",
        thirdButtonText = "Yes/No"
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Task4QuizSelectionScreen(
    navController: NavHostController,
    backgroundImage: Int,
    firstButtonText: String,
    secondButtonText: String,
    thirdButtonText: String,
    onFirstButtonClick: () -> Unit,
    onSecondButtonClick: () -> Unit,
    onThirdButtonClick: () -> Unit
) {
    val lightGreen = Color(0xFF90EE90)
    val darkGreen = Color(0xFF388E3C)

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
                    containerColor = darkGreen,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("Task4SelectionScreen") }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
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
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Button(
                    onClick = onFirstButtonClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                        .padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = lightGreen)
                ) {
                    Text(text = firstButtonText, fontSize = 40.sp, fontWeight = FontWeight.Bold)
                }

                Button(
                    onClick = onSecondButtonClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                        .padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = lightGreen)
                ) {
                    Text(text = secondButtonText, fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }

                Button(
                    onClick = onThirdButtonClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                        .padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = lightGreen)
                ) {
                    Text(text = thirdButtonText, fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Task4SelectionPreview() {
    // Use a dummy NavController for preview
    val navController = rememberNavController()

    // Call your Composable
    task4QuizSelectionScreen(navController = navController)
}
