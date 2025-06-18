package com.example.neuronest.Task2.Task2Quiz

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
import com.example.neuronest.R
import com.example.neuronest.Task2.socialquiz.Question

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
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
                        backDispatcher?.onBackPressed()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF52360C),
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
                color = MaterialTheme.colorScheme.primary,
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
                        .background(Color(0xFFF44336), shape = RoundedCornerShape(20.dp))
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
