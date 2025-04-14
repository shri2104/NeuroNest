package com.example.neuronest.Task2.Task2Quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.neuronest.GoodandBadtouch.DragAndDropQuestionScreen
import com.example.neuronest.R
import com.example.neuronest.Task2.classSelectionScreen

@Composable
fun classroomquizselection(navController: NavHostController) {
    classSelectionScreen(
        navController = navController,
        backgroundImage = R.drawable.resized_girl,
        titleText = "Classroom Manners",
        firstButtonText = "Drag & Drop",
        secondButtonText = "Match",
        onFirstButtonClick = { navController.navigate("classroomquiz") },
        onSecondButtonClick = { navController.navigate("classroomquizselection") }
    )
}

@Composable
fun Classroomquiz1(navController: NavHostController) {
    val questions = listOf(
        Question1(
            questionImage = R.drawable.classq1,
            optionImages = listOf(R.drawable.classo1, R.drawable.classo6, R.drawable.classo3, R.drawable.classo2),
            correctImageIndex = 0,
            dropThresholdY = 500f
        ),
        Question1(
            questionImage = R.drawable.classq2,
            optionImages = listOf(R.drawable.classo4, R.drawable.classo1,R.drawable.classo2,R.drawable.classo3),
            correctImageIndex = 0,
            dropThresholdY = 450f
        ),
        Question1(
            questionImage = R.drawable.classq3,
            optionImages = listOf(R.drawable.classo1, R.drawable.classo7,R.drawable.classo3,R.drawable.classo4),
            correctImageIndex = 1,
            dropThresholdY = 450f
        ),
        Question1(
            questionImage = R.drawable.classq4,
            optionImages = listOf(R.drawable.classo1, R.drawable.classo6, R.drawable.classo7, R.drawable.classo9),
            correctImageIndex = 0,
            dropThresholdY = 500f
        ),
        Question1(
            questionImage = R.drawable.classq5,
            optionImages = listOf(R.drawable.classo1, R.drawable.classo5, R.drawable.classo3,R.drawable.classo9),
            correctImageIndex = 1,
            dropThresholdY = 500f
        ),
        Question1(
            questionImage = R.drawable.classq6,
            optionImages = listOf(R.drawable.classo9, R.drawable.classo2,R.drawable.classo4,R.drawable.classo7),
            correctImageIndex = 0,
            dropThresholdY = 450f
        ),
        Question1(
            questionImage = R.drawable.classq7,
            optionImages = listOf(R.drawable.classo3, R.drawable.classo4,R.drawable.classo2,R.drawable.classo1),
            correctImageIndex = 2,
            dropThresholdY = 450f
        ),
        Question1(
            questionImage = R.drawable.classq8,
            optionImages = listOf(R.drawable.classo2, R.drawable.classo6,R.drawable.classo3,R.drawable.classo5),
            correctImageIndex = 2,
            dropThresholdY = 450f
        ),
        Question1(
            questionImage = R.drawable.classq9,
            optionImages = listOf(R.drawable.classo8, R.drawable.classo7,R.drawable.classo6,R.drawable.classo5),
            correctImageIndex = 0,
            dropThresholdY = 450f
        )
    )
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var isAnswerCorrect by remember { mutableStateOf(false) }
    var isQuizFinished by remember { mutableStateOf(false) }
    var score by remember { mutableStateOf(0) }
    if (isQuizFinished) {
        FinishScreen(navController = navController,score = score, totalQuestions = questions.size)
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
        DragAndDropQuestionScreen(
            questionImage = currentQuestion.questionImage,
            optionImages = currentQuestion.optionImages,
            correctImageIndex = currentQuestion.correctImageIndex,
            dropThresholdY = currentQuestion.dropThresholdY,
            currentQuestionNumber = currentQuestionIndex + 1,
            totalQuestions = questions.size,
            onAnswerCorrect = {
                isAnswerCorrect = true
                score++;
            },
            onNextQuestion = onNextQuestion,
            onPreviousQuestion = onPreviousQuestion
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinishScreen(navController: NavHostController, score: Int, totalQuestions: Int) {
    val backgroundImage = R.drawable.dolphins
    val message = when {
        score == totalQuestions -> "🎉 Amazing! You got everything right! 🎉"
        score > totalQuestions / 2 -> "🌟 Great Job! You're learning fast! 🌟"
        else -> "😊 Keep trying! You're doing awesome! 😊"
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "NeuroNest", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFFFA500))
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        ) {
            Image(
                painter = painterResource(id = backgroundImage),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    colors = CardDefaults.cardColors(Color(0xFFFFE0B2)),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = message,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFFA500),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Your Score: $score / $totalQuestions 🎯",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF4CAF50),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                        if (score == totalQuestions) {
                            Text(
                                text = "🏆 You're a Quiz Champion! 🏆",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1976D2),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    }
                }
                Button(
                    onClick = { navController.navigate("DashBoard") },
                    modifier = Modifier.padding(top = 16.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFFFFA500))
                ) {
                    Text(text = "Go Back", fontSize = 16.sp)
                }
            }
        }
    }
}
data class Question1(
    val questionImage: Int,
    val optionImages: List<Int>,
    val correctImageIndex: Int,
    val dropThresholdY: Float
)