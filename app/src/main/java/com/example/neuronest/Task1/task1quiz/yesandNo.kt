package com.example.neuronest.Task1.task1quiz

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.example.neuronest.R
import com.example.neuronest.Task2.Task2Quiz.QuizScreen
import com.example.neuronest.Task2.socialquiz.Question
import lastquizScreen
import android.media.MediaPlayer
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

@Composable
fun Task1yeandno(navController: NavController) {
    val questions = listOf(
        Question(R.drawable.task1yn1, false),
        Question(R.drawable.task1yn2, true),
        Question(R.drawable.task1yn3, true),
        Question(R.drawable.task1yn4, false),
        Question(R.drawable.task1yn5, true),

    )

    var currentIndex by remember { mutableStateOf(0) }
    var isQuizFinished by remember { mutableStateOf(false) }

    if (isQuizFinished) {
        lastquizScreen(navController = navController , destinationRoute = "Task1SelectionScreen2")
    } else {
        QuizScreentask1(
            navController,
            questions = questions,
            currentIndex = currentIndex,
            onAnswer = { isCorrect ->
                if (currentIndex < questions.lastIndex) {
                    currentIndex++
                } else {
                    isQuizFinished = true
                }
            },
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreentask1(
    navController: NavController,
    questions: List<Question>,
    currentIndex: Int,
    onAnswer: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

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

    fun playSound(resId: Int) {
        val mediaPlayer = MediaPlayer.create(context, resId)
        mediaPlayer?.apply {
            start()
            setOnCompletionListener { release() }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Yes OR No Quiz", fontSize = 35.sp) },
                navigationIcon = {
                    IconButton(onClick = {
                      navController.popBackStack()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF2196F3),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
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
                fontSize = 35.sp,
                color = Color(0xFF2196F3),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(40.dp))

            Image(
                painter = painterResource(id = question.imageRes),
                contentDescription = "Question Image",
                modifier = Modifier
                    .size(800.dp)
                    .clip(RoundedCornerShape(32.dp))
            )

            Spacer(modifier = Modifier.height(48.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(
                    onClick = {
                        if (question.answer) {
                            playSound(R.raw.correct)
                            onAnswer(true)
                        } else {
                            playSound(R.raw.wrng)
                            showSnackbar = true
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(100.dp)
                        .padding(end = 12.dp)
                        .background(Color(0xFF4CAF50), shape = RoundedCornerShape(20.dp))
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Yes",
                        tint = Color.White,
                        modifier = Modifier.size(55.dp)
                    )
                }

                IconButton(
                    onClick = {
                        if (!question.answer) {
                            playSound(R.raw.correct)
                            onAnswer(true)
                        } else {
                            playSound(R.raw.wrng)
                            showSnackbar = true
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(100.dp)
                        .padding(start = 12.dp)
                        .background(Color(0xFFF44336), shape = RoundedCornerShape(20.dp)) // keeping No as red
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "No",
                        tint = Color.White,
                        modifier = Modifier.size(55.dp)
                    )
                }
            }
        }
    }
}
