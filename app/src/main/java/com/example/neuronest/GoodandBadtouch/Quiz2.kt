package com.example.neuronest.GoodandBadtouch


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.neuronest.R


@Composable
fun QuizScreen(navController: NavHostController) {
    val questions = listOf(
        Question1(
            questionImage = R.drawable.q,
            optionImages = listOf(R.drawable.o1, R.drawable.o2, R.drawable._3, R.drawable.o4),
            correctImageIndex = 0,
            dropThresholdY = 500f
        ),
        Question1(
            questionImage = R.drawable.q2,
            optionImages = listOf(R.drawable.q2o1, R.drawable.q2o2),
            correctImageIndex = 0,
            dropThresholdY = 450f
        ),
        Question1(
            questionImage = R.drawable.q3,
            optionImages = listOf(R.drawable.q2o1, R.drawable.q2o2),
            correctImageIndex = 1,
            dropThresholdY = 450f
        ),
        Question1(
            questionImage = R.drawable.q4,
            optionImages = listOf(R.drawable.q4o1, R.drawable.q4o2, R.drawable.q4o3),
            correctImageIndex = 2,
            dropThresholdY = 500f
        ),
        Question1(
            questionImage = R.drawable.q5,
            optionImages = listOf(R.drawable.q5o1, R.drawable.q5o2, R.drawable.q5o3),
            correctImageIndex = 0,
            dropThresholdY = 500f
        ),
        Question1(
            questionImage = R.drawable.q6,
            optionImages = listOf(R.drawable.q2o1, R.drawable.q2o2),
            correctImageIndex = 0,
            dropThresholdY = 450f
        ),
        Question1(
            questionImage = R.drawable.q7,
            optionImages = listOf(R.drawable.q2o1, R.drawable.q2o2),
            correctImageIndex = 1,
            dropThresholdY = 450f
        ),
        Question1(
            questionImage = R.drawable.q8,
            optionImages = listOf(R.drawable.q2o1, R.drawable.q2o2),
            correctImageIndex = 0,
            dropThresholdY = 450f
        )
    )
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var isAnswerCorrect by remember { mutableStateOf(false) }
    var isQuizFinished by remember { mutableStateOf(false) } // State to track if the quiz is finished
    var score by remember { mutableStateOf(0) }
    if (isQuizFinished) {
        FinishScreen(navController = navController,score = score, totalQuestions = questions.size) // Show Finish Screen when the quiz ends
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
                    colors = CardDefaults.cardColors(Color(0xFFFFE0B2)), // Soft orange background
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
                            color = Color(0xFF4CAF50), // Green for positivity
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                        if (score == totalQuestions) {
                            Text(
                                text = "🏆 You're a Quiz Champion! 🏆",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1976D2), // Blue for excitement
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
                    Text(text = "🎈 Go Back 🎈", fontSize = 16.sp)
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