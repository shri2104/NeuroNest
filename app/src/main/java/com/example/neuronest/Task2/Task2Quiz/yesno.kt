package com.example.neuronest.Task2.Task2Quiz

import android.widget.Toast
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.neuronest.R

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
                title = { Text("Image Quiz") },
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
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = question.imageRes),
                contentDescription = "Question Image",
                modifier = Modifier
                    .size(400.dp)
                    .clip(RoundedCornerShape(24.dp))
            )
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        if (question.answer) {
                            if (currentIndex < questions.size - 1) {
                                currentIndex++
                            }
                        } else {
                            showSnackbar = true
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                        .padding(end = 8.dp)
                ) {
                    Text("Yes")
                }
                Button(
                    onClick = {
                        if (!question.answer) {
                            if (currentIndex < questions.size - 1) {
                                currentIndex++
                            }
                        } else {
                            showSnackbar = true
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                        .padding(start = 8.dp)
                ) {
                    Text("No")
                }
            }
        }
    }
}

// Sample model
data class Question(
    val imageRes: Int,
    val answer: Boolean
)

val sampleQuestions = listOf(
    Question(R.drawable.yn1, false),
    Question(R.drawable.yn2, false),
    Question(R.drawable.yn3, true),
    Question(R.drawable.yn4, true),
    Question(R.drawable.yn5, false),
    Question(R.drawable.yn6, false),
    Question(R.drawable.yn7, true),
    Question(R.drawable.yn8, true),
    Question(R.drawable.yn9, true),
    Question(R.drawable.yn10, true),
    Question(R.drawable.yn11, true),
    Question(R.drawable.yn12, true),
)

@Preview(showSystemUi = true)
@Composable
fun QuizScreenPreview() {
    QuizScreen(questions = sampleQuestions)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen2(
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
                title = { Text("Image Quiz") },
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
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = question.imageRes),
                contentDescription = "Question Image",
                modifier = Modifier
                    .size(400.dp)
                    .clip(RoundedCornerShape(24.dp))
            )
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        if (question.answer) {
                            if (currentIndex < questions.size - 1) {
                                currentIndex++
                            }
                        } else {
                            showSnackbar = true
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                        .padding(end = 8.dp)
                ) {
                    Text("Yes")
                }
                Button(
                    onClick = {
                        if (!question.answer) {
                            if (currentIndex < questions.size - 1) {
                                currentIndex++
                            }
                        } else {
                            showSnackbar = true
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                        .padding(start = 8.dp)
                ) {
                    Text("No")
                }
            }
        }
    }
}

val sampleQuestions2 = listOf(
    Question(R.drawable.yns1, true),
    Question(R.drawable.yns2, false),
    Question(R.drawable.yns3, true),
    Question(R.drawable.yns4, true),
    Question(R.drawable.yns5, true),
    Question(R.drawable.yns6, true),
    Question(R.drawable.yns7, true),
    Question(R.drawable.yns8, true),
)