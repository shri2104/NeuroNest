package com.example.neuronest.Task4.task4quiz

import android.annotation.SuppressLint
import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.example.neuronest.Task4.Selection.Task4ActivityType
import lastquizScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
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
import com.example.neuronest.R


data class DragDropQuestion(
    val questionText: String? = null,
    val questionImage: Int? = null,
    val options: List<String>,
    val correctIndex: Int
)



@Composable
fun Task4DragDrop(
    navController: NavHostController,
    activity: Task4ActivityType
) {
    val questions = remember {
        Task4DragDropProvider.getQuestions(activity)
    }

    DragDropPager(
        navController = navController,
        activity = activity,
        questions = questions
    )
}

@Composable
fun DragDropPager(
    navController: NavHostController,
    questions: List<DragDropQuestion>,
    activity: Task4ActivityType
) {
    var currentIndex by remember { mutableStateOf(0) }
    var quizFinished by remember { mutableStateOf(false) }

    if (quizFinished) {
        lastquizScreen(
            navController = navController,
            destinationRoute = "Task4QuizSelection/${activity.name}"
        )
        return
    }

    val currentQuestion = questions[currentIndex]

    DragAndDropQuestionScreen4(
        question = currentQuestion,
        currentQuestionNumber = currentIndex + 1,
        totalQuestions = questions.size,
        onAnswerCorrect = {},
        onNextQuestion = {
            if (currentIndex < questions.lastIndex) {
                currentIndex++
            } else {
                quizFinished = true
            }
        },
        onPreviousQuestion = {
            if (currentIndex > 0) currentIndex--
        }
    )
}



@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun DragAndDropQuestionScreen4(
    question: DragDropQuestion,
    currentQuestionNumber: Int,
    totalQuestions: Int,
    onAnswerCorrect: () -> Unit,
    onNextQuestion: () -> Unit,
    onPreviousQuestion: () -> Unit
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val offsets = remember { mutableStateListOf(*Array(question.options.size) { Offset.Zero }) }

    var draggedItemIndex by remember { mutableStateOf<Int?>(null) }
    var isAnswerCorrect by remember { mutableStateOf(false) }
    var droppedTextIndex by remember { mutableStateOf<Int?>(null) }

    LaunchedEffect(currentQuestionNumber) {
        for (i in offsets.indices) offsets[i] = Offset.Zero
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
                title = { Text("Drag And Drop", fontSize = 35.sp) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF388E3C), // green
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
                        .weight(1f)
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF388E3C))
                ) {
                    Text("Previous", fontSize = 28.sp, color = Color.White)
                }

                Spacer(modifier = Modifier.width(24.dp))

                Button(
                    onClick = onNextQuestion,
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF388E3C))
                ) {
                    Text("Next", fontSize = 28.sp, color = Color.White)
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
            val optionBoxSize = if (isTablet) 180.dp else 120.dp
            val dropBoxHeight = if (isTablet) 220.dp else 160.dp
            val fontSize = if (isTablet) 36.sp else 20.sp

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

                question.questionImage?.let {
                    Image(
                        painter = painterResource(id = it),
                        contentDescription = "Question Image",
                        modifier = Modifier
                            .size(imageSize)
                            .clip(RoundedCornerShape(16.dp))
                            .border(2.dp, Color.Gray, RoundedCornerShape(16.dp))
                    )
                }

                question.questionText?.let {
                    Text(
                        text = it,
                        fontSize = fontSize,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // OPTIONS
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    question.options.forEachIndexed { index, text ->
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
                                .background(Color(0xFF4CAF50))
                                .then(
                                    if (!isAnswerCorrect) Modifier.pointerInput(Unit) {
                                        detectDragGestures(
                                            onDragStart = { draggedItemIndex = index },
                                            onDrag = { change, dragAmount ->
                                                change.consume()
                                                offsets[index] += dragAmount
                                            },
                                            onDragEnd = {
                                                if (!isAnswerCorrect && index == question.correctIndex) {
                                                    isAnswerCorrect = true
                                                    droppedTextIndex = index
                                                    playSound(R.raw.correct)
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
                                    } else Modifier
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = text,
                                fontSize = fontSize,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(8.dp),
                                color = Color.White,
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

                // DROP ZONE
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dropBoxHeight)
                        .clip(RoundedCornerShape(16.dp))
                        .background(if (isAnswerCorrect) Color(0xFF81C784) else Color(0xFFC8E6C9)),
                    contentAlignment = Alignment.Center
                ) {
                    if (isAnswerCorrect && droppedTextIndex != null) {
                        Text(
                            text = question.options[droppedTextIndex!!],
                            fontSize = if (isTablet) 100.sp else 32.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center,
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

object Task4DragDropProvider {
    fun getQuestions(activity: Task4ActivityType): List<DragDropQuestion> =
        when (activity) {

            Task4ActivityType.HITTING -> listOf(
                DragDropQuestion(
                    questionImage = R.drawable.hitting_img,
                    options = listOf("exercise bike", "squeeze hands", "belly breathing","quiet chair"),
                    correctIndex = 1
                )
            )

            Task4ActivityType.PINCHING -> listOf(
                DragDropQuestion(
                    questionText = "Pinching makes others feel .............",
                    options = listOf("upset", "happy"),
                    correctIndex = 0
                ),
                DragDropQuestion(
                    questionText = "When I am angry, instead of pinching ,I can ..........",
                    options = listOf("pinch myself", "talk about my feelings"),
                    correctIndex = 1
                ),
                DragDropQuestion(
                    questionText = "When you pinch someone, It can make them feel ............",
                    options = listOf("sad", "joy"),
                    correctIndex = 0
                )
            )

            Task4ActivityType.BITING -> listOf(
                DragDropQuestion(
                    questionText = "I can use my teeth to bite and chew ............ ",
                    options = listOf("food", "people"),
                    correctIndex = 0
                ),
                DragDropQuestion(
                    questionText = "I can use my ............... instead of biting",
                    options = listOf("teeth", "words"),
                    correctIndex = 0
                ),
                DragDropQuestion(
                    questionText = "Biting makes my friends feel ............",
                    options = listOf("sad", "happy"),
                    correctIndex = 0
                ),
                DragDropQuestion(
                    questionText = "Biting other people ..........",
                    options = listOf("hurts", "heal"),
                    correctIndex = 0
                ),
                DragDropQuestion(
                    questionText = "I can take a deep ............. to calm my body.",
                    options = listOf("bath", "breath"),
                    correctIndex = 1
                )
            )

            else -> emptyList()
        }
}
