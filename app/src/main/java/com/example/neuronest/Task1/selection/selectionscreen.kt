package com.example.neuronest.Task1.selection

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.neuronest.R
import com.example.neuronest.Task2.selection.classSelectionScreen

@Composable
fun Task1SelectionScreen1(navController: NavHostController) {
    SelectionScreen(
        navController = navController,
        backgroundImage = R.drawable.task1bgimage2,
        titleText = "Good and Bad Touch",
        firstButtonText = "Happy Learning!",
        secondButtonText = "Brain Fun!",
        onFirstButtonClick = { navController.navigate("Task1presentation") },
        onSecondButtonClick = { navController.navigate("Task1SelectionScreen2") }
    )
}

@Composable
fun Task1SelectionScreen2(navController: NavHostController) {
    SelectionScreenforquiz(
        navController = navController,
        backgroundImage = R.drawable.task1bgimage2,
        titleText = "Good and Bad Touch",
        firstButtonText = "Drag and Drop",
        secondButtonText = "Selection",
        thirdButtonText = "Yes and No",
        onFirstButtonClick = { navController.navigate("Task1draganddrop") },
        onSecondButtonClick = { navController.navigate("Task1SelectionScreen3")  },
        onThirdButtonClick = { navController.navigate("Task1YasandNo") }
    )
}

@Composable
fun Task1SelectionScreen3(navController: NavHostController) {
    SelectionScreen(
        navController = navController,
        backgroundImage = R.drawable.task1bgimage2,
        titleText = "Good and Bad Touch",
        firstButtonText = "Selection Part 1",
        secondButtonText = "Selection Part 2",
        onFirstButtonClick = { navController.navigate("task1selectionquiz1") },
        onSecondButtonClick = { navController.navigate("task1selectionquiz2") }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectionScreen(
    navController: NavHostController,
    backgroundImage: Int,
    titleText: String,
    firstButtonText: String,
    secondButtonText: String,
    onFirstButtonClick: () -> Unit,
    onSecondButtonClick: () -> Unit
) {
    val lightBlue = Color(0xFF00BFFF)
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
                    containerColor = Color(0xFF2196F3),
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

                /*Divider(
                    color = Color(0xFF3F51B5),
                    thickness = 2.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                )*/

                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    onClick = onFirstButtonClick,
                    modifier = Modifier
                        .fillMaxWidth().size(100.dp)
                        .padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF2196F3))
                ) {
                    Text(text = firstButtonText, fontSize = 40.sp, fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = onSecondButtonClick,
                    modifier = Modifier
                        .fillMaxWidth().size(100.dp)
                        .padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF2196F3))
                ) {
                    Text(text = secondButtonText, fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectionScreenforquiz(
    navController: NavHostController,
    backgroundImage: Int,
    titleText: String,
    firstButtonText: String,
    secondButtonText: String,
    thirdButtonText: String,
    onFirstButtonClick: () -> Unit,
    onSecondButtonClick: () -> Unit,
    onThirdButtonClick: () -> Unit
) {
    val lightBlue = Color(0xFF00BFFF)
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
                    containerColor = Color(0xFF2196F3),
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

                /*Divider(
                    color = Color(0xFF3F51B5),
                    thickness = 2.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                )*/

                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    onClick = onFirstButtonClick,
                    modifier = Modifier
                        .fillMaxWidth().size(100.dp)
                        .padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF2196F3))
                ) {
                    Text(text = firstButtonText, fontSize = 40.sp, fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = onSecondButtonClick,
                    modifier = Modifier
                        .fillMaxWidth().size(100.dp)
                        .padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF2196F3))
                ) {
                    Text(
                        text = secondButtonText,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                Button(
                    onClick = onThirdButtonClick,
                    modifier = Modifier
                        .fillMaxWidth().size(100.dp)
                        .padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFF2196F3))
                ) {
                    Text(
                        text = thirdButtonText,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}