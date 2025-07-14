package com.example.neuronest.Task1.task1quiz

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.example.neuronest.R
import com.example.neuronest.Task2.Task2Quiz.DragAndDropQuestionScreen1
import lastquizScreen

data class Question1(
    val questionImage: Int,
    val optionImageRes: List<Int>, // Now images for options
    val correctImageIndex: Int,
    val dropThresholdY: Float
)


@Composable
fun task1draganddrop(navController: NavHostController) {
    val questions = listOf(
        Question1(
            questionImage = R.drawable.task1dq1,
            optionImageRes = listOf(R.drawable.task1do1, R.drawable.task1do2, R.drawable.task1do3),
            correctImageIndex = 1,
            dropThresholdY = 500f
        ),
        Question1(
            questionImage = R.drawable.task1dq2,
            optionImageRes = listOf(R.drawable.task1do4, R.drawable.task1do5, R.drawable.taskdo6),
            correctImageIndex = 1,
            dropThresholdY = 500f
        ),
        Question1(
            questionImage = R.drawable.task1dq3,
            optionImageRes = listOf(R.drawable.task1do7, R.drawable.task1do8, R.drawable.task1do9),
            correctImageIndex = 1,
            dropThresholdY = 500f
        ),
        Question1(
            questionImage = R.drawable.task1dq4,
            optionImageRes = listOf(R.drawable.task1do10, R.drawable.task1do11, R.drawable.task1do12),
            correctImageIndex = 1,
            dropThresholdY = 500f
        ),
    )


    var currentQuestionIndex by remember { mutableStateOf(0) }
    var isAnswerCorrect by remember { mutableStateOf(false) }
    var isQuizFinished by remember { mutableStateOf(false) }
    var score by remember { mutableStateOf(0) }

    if (isQuizFinished) {
        lastquizScreen(
            navController = navController,
            destinationRoute = "Task1SelectionScreen2"
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

        task1draganddrop2(
            questionImage = currentQuestion.questionImage,
            optionImageRes = currentQuestion.optionImageRes,
            correctImageIndex = currentQuestion.correctImageIndex,
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

