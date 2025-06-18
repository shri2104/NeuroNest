package com.example.neuronest.Task2.classroomquiz

import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.example.neuronest.GoodandBadtouch.FinishScreen
import com.example.neuronest.R
import com.example.neuronest.Task2.Task2Quiz.QuizScreen
import com.example.neuronest.Task2.socialquiz.Question
import lastquizScreen

@Composable
fun YesNoClass(navController: NavController) {
    val questions = listOf(
        Question(R.drawable.cyn1, true),
        Question(R.drawable.cyn2, false),
        Question(R.drawable.cyn3, true),
        Question(R.drawable.cyn4, false),
        Question(R.drawable.cyn5, true),
        Question(R.drawable.cyn6, true),
        Question(R.drawable.cyn7, false),
        Question(R.drawable.cyn8, false),
        Question(R.drawable.cyn9, false),
        Question(R.drawable.cyn10, true),
    )

    var currentIndex by remember { mutableStateOf(0) }
    var isQuizFinished by remember { mutableStateOf(false) }

    if (isQuizFinished) {
        lastquizScreen(navController = navController , destinationRoute = "ClassSelectionScreen")
    } else {
        QuizScreen(
            questions = questions,
            currentIndex = currentIndex,
            onAnswer = { isCorrect ->
                if (currentIndex < questions.lastIndex) {
                    currentIndex++
                } else {
                    isQuizFinished = true
                }
            }
        )
    }
}
