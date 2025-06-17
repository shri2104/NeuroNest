package com.example.neuronest.Task2.Task2Quiz


import android.widget.Toast
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.neuronest.R
import com.example.neuronest.Task2.socialquiz.Question


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    questions: List<Question>,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    var currentIndex by remember { mutableStateOf(0) }
    var showSnackbar by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }

    val question = questions[currentIndex]

    LaunchedEffect(showSnackbar) {
        if (showSnackbar) {
            snackbarHostState.showSnackbar("Incorrect answer")
            Toast.makeText(context, "Incorrect answer", Toast.LENGTH_SHORT).show()
            showSnackbar = false
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Yes OR No Quiz") },
                navigationIcon = {
                    IconButton(onClick = {
                        backDispatcher?.onBackPressed()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Does this image show a good action?",
                fontSize = 28.sp,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
            )


            Image(
                painter = painterResource(id = question.imageRes),
                contentDescription = "Question Image",
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .aspectRatio(1.2f)
                    .clip(RoundedCornerShape(32.dp))
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Icons Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(
                    onClick = {
                        if (question.answer) {
                            if (currentIndex < questions.size - 1) currentIndex++
                        } else {
                            showSnackbar = true
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(100.dp)
                        .padding(end = 12.dp)
                        .background(Color(0xFF4CAF50), shape = RoundedCornerShape(20.dp)) // Green
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Yes",
                        tint = Color.White,
                        modifier = Modifier.size(48.dp)
                    )
                }

                IconButton(
                    onClick = {
                        if (!question.answer) {
                            if (currentIndex < questions.size - 1) currentIndex++
                        } else {
                            showSnackbar = true
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(100.dp)
                        .padding(start = 12.dp)
                        .background(Color(0xFFF44336), shape = RoundedCornerShape(20.dp)) // Red
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "No",
                        tint = Color.White,
                        modifier = Modifier.size(48.dp)
                    )
                }
            }
        }
    }
}

