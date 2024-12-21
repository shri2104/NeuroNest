package com.example.neuronest.GoodandBadtouch

import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavHostController
import com.example.neuronest.R

@Composable
fun QuizScreen(navController: NavHostController) {
    val questions = listOf(
        Question(
            questionImage = R.drawable.q,
            optionImages = listOf(R.drawable.o1, R.drawable.o2, R.drawable._3, R.drawable.o4),
            correctImageIndex = 0,
            dropThresholdY = 500f
        ),
        Question(
            questionImage = R.drawable.q2,
            optionImages = listOf(R.drawable.q2o1, R.drawable.q2o2),
            correctImageIndex = 1,
            dropThresholdY = 450f
        )
    )
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var isAnswerCorrect by remember { mutableStateOf(false) }
    val onNextQuestion: () -> Unit = {
        if (currentQuestionIndex < questions.size - 1) {
            currentQuestionIndex++
            isAnswerCorrect = false
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
        },
        onNextQuestion = onNextQuestion,
        onPreviousQuestion = onPreviousQuestion
    )
}


data class Question(
    val questionImage: Int,
    val optionImages: List<Int>,
    val correctImageIndex: Int,
    val dropThresholdY: Float
)
