package com.example.neuronest.Task3.task3Quiz

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.neuronest.GoodandBadtouch.FinishScreen
import com.example.neuronest.R
import com.example.neuronest.Task2.Task2Quiz.DragAndDropQuestionScreen1
import com.example.neuronest.Task2.classroomquiz.Question1
import lastquizScreen

//data class Question1(
//    val questionImage: Int,
//    val optionTexts: List<String>,
//    val correctTextIndex: Int,
//    val dropThresholdY: Float
//)

@Composable
fun Task3DrapaandDrop(navController: NavHostController) {
    val questions = listOf(
        Question1(
            questionImage = R.drawable.e1,
            optionTexts = listOf("Happy", "Angry", "Calm"),
            correctTextIndex = 0,
            dropThresholdY = 500f
        ),
        Question1(
            questionImage = R.drawable.e2,
            optionTexts = listOf("Happy", "Angry", "Calm"),
            correctTextIndex = 1,
            dropThresholdY = 500f
        ),
        Question1(
            questionImage = R.drawable.e3,
            optionTexts = listOf("Happy", "Angry", "Calm"),
            correctTextIndex = 2,
            dropThresholdY = 500f
        ),
        Question1(
            questionImage = R.drawable.e4,
            optionTexts = listOf("Happy", "Excited", "Calm"),
            correctTextIndex = 1,
            dropThresholdY = 500f
        ),
        Question1(
            questionImage = R.drawable.e5,
            optionTexts = listOf("Funny", "Angry", "Calm"),
            correctTextIndex = 0,
            dropThresholdY = 500f
        ),
    )

    var currentQuestionIndex by remember { mutableStateOf(0) }
    var isAnswerCorrect by remember { mutableStateOf(false) }
    var isQuizFinished by remember { mutableStateOf(false) }
    var score by remember { mutableStateOf(0) }

    if (isQuizFinished) {
        lastquizScreen(
            navController = navController ,
            destinationRoute = "SocialSelectionScreen"
        )
    } else {
        val onNextQuestion: () -> Unit = {
            if (currentQuestionIndex < questions.size - 1) {
                currentQuestionIndex++
                isAnswerCorrect = false
            } else {
                isQuizFinished = true
            }
        }

        val onPreviousQuestion: () -> Unit = {
            if (currentQuestionIndex > 0) {
                currentQuestionIndex--
                isAnswerCorrect = false
            }
        }

        val currentQuestion = questions[currentQuestionIndex]

        DragAndDropQuestionScreen2(
            questionImage = currentQuestion.questionImage,
            optionTexts = currentQuestion.optionTexts,
            correctTextIndex = currentQuestion.correctTextIndex,
            dropThresholdY = currentQuestion.dropThresholdY,
            currentQuestionNumber = currentQuestionIndex + 1,
            totalQuestions = questions.size,
            onAnswerCorrect = {
                if (!isAnswerCorrect) {
                    score++
                    isAnswerCorrect = true
                }
            },
            onNextQuestion = onNextQuestion,
            onPreviousQuestion = onPreviousQuestion
        )
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DragAndDropQuestionScreen2(
    questionImage: Int,
    optionTexts: List<String>,
    correctTextIndex: Int,
    currentQuestionNumber: Int,
    totalQuestions: Int,
    dropThresholdY: Float,
    onAnswerCorrect: () -> Unit,
    onNextQuestion: () -> Unit,
    onPreviousQuestion: () -> Unit
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val offsets = remember { mutableStateListOf(*Array(optionTexts.size) { Offset.Zero }) }

    var draggedItemIndex by remember { mutableStateOf<Int?>(null) }
    var isAnswerCorrect by remember { mutableStateOf(false) }
    var droppedTextIndex by remember { mutableStateOf<Int?>(null) }

    LaunchedEffect(currentQuestionNumber) {
        for (i in offsets.indices) {
            offsets[i] = Offset.Zero
        }
        isAnswerCorrect = false
        droppedTextIndex = null
        draggedItemIndex = null
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
                title = { Text("Drag And Drop" , fontSize = 35.sp) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFF80A6),
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = onPreviousQuestion,
                    modifier = Modifier
                        .weight(1f).size(80.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF80A6))
                ) {
                    Text("Previous", fontSize = 40.sp)
                }

                Spacer(modifier = Modifier.width(24.dp))

                Button(
                    onClick = onNextQuestion,
                    modifier = Modifier
                        .weight(1f).size(80.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF80A6))
                ) {
                    Text("Next" , fontSize = 40.sp)
                }
            }
        }
    ) { innerPadding ->
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            val isTablet = maxWidth > 600.dp
            val imageSize = if (isTablet) 450.dp else 260.dp
            val optionBoxSize = if (isTablet) 180.dp else 110.dp
            val dropBoxHeight = if (isTablet) 220.dp else 160.dp
            val fontSize = if (isTablet) 35.sp else MaterialTheme.typography.bodyLarge.fontSize

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Question $currentQuestionNumber of $totalQuestions",
                    fontSize = fontSize,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(12.dp))

                Image(
                    painter = painterResource(id = questionImage),
                    contentDescription = "Question Image",
                    modifier = Modifier
                        .size(imageSize)
                        .clip(RoundedCornerShape(16.dp))
                        .border(2.dp, Color.Gray, RoundedCornerShape(16.dp))
                )

                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    optionTexts.forEachIndexed { index, text ->
                        Box(
                            modifier = Modifier
                                .size(optionBoxSize)
                                .offset {
                                    IntOffset(
                                        offsets[index].x.toInt(),
                                        (offsets[index].y - scrollState.value).toInt()
                                    )
                                }
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color(0xFFFF80A6))
                                .then(
                                    if (!isAnswerCorrect)
                                        Modifier.pointerInput(Unit) {
                                            detectDragGestures(
                                                onDragStart = {
                                                    draggedItemIndex = index
                                                },
                                                onDrag = { change, dragAmount ->
                                                    change.consume()
                                                    offsets[index] += Offset(
                                                        dragAmount.x,
                                                        dragAmount.y
                                                    )
                                                },
                                                onDragEnd = {
                                                    val dropY = offsets[index].y + scrollState.value
                                                    if (!isAnswerCorrect && index == correctTextIndex && dropY > dropThresholdY) {
                                                        isAnswerCorrect = true
                                                        droppedTextIndex = index
                                                        playSound(R.raw.correct)
                                                        Toast.makeText(
                                                            context,
                                                            "Correct Answer!",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                        onAnswerCorrect()
                                                    } else {
                                                        playSound(R.raw.wrng)
                                                        offsets[index] = Offset.Zero
                                                    }
                                                    draggedItemIndex = null
                                                },
                                                onDragCancel = {
                                                    offsets[index] = Offset.Zero
                                                }
                                            )
                                        }
                                    else Modifier
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = text,
                                fontSize = fontSize,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(8.dp),
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(48.dp))

                Divider(
                    color = Color.Gray,
                    thickness = 1.5.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dropBoxHeight)
                        .clip(RoundedCornerShape(16.dp))
                        .background(if (isAnswerCorrect) Color(0xFF81C784) else Color(0xFFD6D6D6)),
                    contentAlignment = Alignment.Center
                ) {
                    if (isAnswerCorrect && droppedTextIndex != null) {
                        Text(
                            text = optionTexts[droppedTextIndex!!],
                            fontSize = if (isTablet) 100.sp else 32.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(8.dp),
                            fontWeight = FontWeight.Bold
                        )
                    } else {
                        Text(
                            text = "Drop Answer Here",
                            fontSize = fontSize,
                            color = Color.DarkGray
                        )
                    }
                }

            }
        }
    }
}