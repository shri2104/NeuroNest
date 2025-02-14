package com.example.neuronest.GoodandBadtouch

import androidx.compose.foundation.Image
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.neuronest.R

data class PairItem(
    val id: Int, // Unique identifier for the pair
    val leftItem: String, // Text or image resource for the left column
    val rightItem: Int // Text or image resource for the right column
)

data class Question(
    val pairs: List<PairItem>, // List of pairs for the question
    val correctMatches: Map<Int, Int> // Map of correct matches (leftItemId to rightItemId)
)

@Composable
fun MatchThePairQuizScreen(navController: NavHostController) {
    val questions = listOf(
        Question(
            pairs = listOf(
                PairItem(1, "GoodTouch",R.drawable.pre4),
                PairItem(2, "BadTouch", R.drawable.q5o3),
            ),
            correctMatches = mapOf(1 to 1, 2 to 2) // Left ID -> Right ID
        ),
    )

    var currentQuestionIndex by remember { mutableStateOf(0) }
    var score2 by remember { mutableStateOf(0) }
    var isQuizFinished by remember { mutableStateOf(false) }

    if (isQuizFinished) {
        FinishScreen2(navController = navController, score = score2, totalQuestions = questions.size)
    } else {
        val currentQuestion = questions[currentQuestionIndex]

        MatchThePairQuestionScreen(
            question = currentQuestion,
            currentQuestionNumber = currentQuestionIndex + 1,
            totalQuestions = questions.size ,
            onAnswerCorrect = {
                score2 = 1// Increase score when the answer is correct
            },
            onNextQuestion = {
                if (currentQuestionIndex < questions.size - 1) {
                    currentQuestionIndex++
                } else {
                    isQuizFinished = true
                }
            },
            onPreviousQuestion = {
                if (currentQuestionIndex > 0) {
                    currentQuestionIndex--
                }
            }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinishScreen2(navController: NavHostController, score: Int, totalQuestions: Int) {
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
                    onClick = { navController.navigate("SelectionScreen") },
                    modifier = Modifier.padding(top = 16.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xFFFFA500))
                ) {
                    Text(text = "🎈 Go Back 🎈", fontSize = 16.sp)
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewScreen() {
    MaterialTheme {
        MatchThePairQuizScreen(navController = NavHostController(LocalContext.current))
    }
}

