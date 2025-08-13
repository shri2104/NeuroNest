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
            optionImageRes = listOf(R.drawable._o18, R.drawable._o17, R.drawable._o15),
            correctImageIndex = 1,
            dropThresholdY = 500f
        ),
        Question1(
            questionImage = R.drawable.task1dq2,
            optionImageRes = listOf(R.drawable._o16, R.drawable._o19, R.drawable._o21),
            correctImageIndex = 1,
            dropThresholdY = 500f
        ),
        Question1(
            questionImage = R.drawable.task1dq3,
            optionImageRes = listOf(R.drawable._o12, R.drawable._013, R.drawable._o10),
            correctImageIndex = 1,
            dropThresholdY = 500f
        ),
        Question1(
            questionImage = R.drawable.task1dq4,
            optionImageRes = listOf(R.drawable._o5, R.drawable._o1, R.drawable._o14),
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

