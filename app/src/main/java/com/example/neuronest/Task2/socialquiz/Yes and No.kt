package com.example.neuronest.Task2.socialquiz

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.neuronest.R
import com.example.neuronest.Task2.Task2Quiz.QuizScreen

@Composable
fun YesNosocial(navController: NavController) {
    val questions = listOf(
        Question(R.drawable.syn1, true),
        Question(R.drawable.syn2, false),
        Question(R.drawable.syn3, false),
        Question(R.drawable.syn4, true),
        Question(R.drawable.syn5, false),
        Question(R.drawable.syn6, false),
        Question(R.drawable.syn7, true),
        Question(R.drawable.syn8, true),
        Question(R.drawable.syn9, true),
        Question(R.drawable.syn10, false),
        Question(R.drawable.syn11, false),
        Question(R.drawable.syn12, true)
    )
    QuizScreen(questions = questions)
}

data class Question(
    val imageRes: Int,
    val answer: Boolean
)
