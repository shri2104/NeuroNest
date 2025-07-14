package com.example.neuronest.Task3.task3selection

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.neuronest.R
import com.example.neuronest.Task2.selection.classSelectionScreen
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun task3Selection(navController: NavHostController) {
    task3selection(
        navController = navController,
        backgroundImage = R.drawable.task1selection2,
        firstButtonText = "Happy Learning!",
        secondButtonText = "Brain Fun!",
        onFirstButtonClick = { navController.navigate("Task3presentationScreen") },
        onSecondButtonClick = {  }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun task3selection(
    navController: NavHostController,
    backgroundImage: Int,
    firstButtonText: String,
    secondButtonText: String,
    onFirstButtonClick: () -> Unit,
    onSecondButtonClick: () -> Unit
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
                    IconButton(onClick = { navController.popBackStack() }) {
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
                Spacer(modifier = Modifier.height(150.dp))

                Button(
                    onClick = onFirstButtonClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(100.dp)
                        .padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = babyPink)
                ) {
                    Text(
                        text = firstButtonText,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                Button(
                    onClick = onSecondButtonClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(100.dp)
                        .padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = babyPink)
                ) {
                    Text(
                        text = secondButtonText,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

