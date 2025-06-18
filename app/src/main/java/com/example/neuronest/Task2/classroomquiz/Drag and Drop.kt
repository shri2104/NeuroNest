package com.example.neuronest.Task2.classroomquiz


import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavHostController
import com.example.neuronest.GoodandBadtouch.FinishScreen
import com.example.neuronest.R
import com.example.neuronest.Task2.Task2Quiz.DragAndDropQuestionScreen1
import lastquizScreen

data class Question1(
    val questionImage: Int,
    val optionTexts: List<String>,
    val correctTextIndex: Int,
    val dropThresholdY: Float
)

@Composable
fun ClassroomDrapaandDrop(navController: NavHostController) {
    val questions = listOf(
        com.example.neuronest.Task2.socialquiz.Question1(
            questionImage = R.drawable.classdq1,
            optionTexts = listOf("Please", "Thank You", "Sorry"),
            correctTextIndex = 1,
            dropThresholdY = 500f
        ),
        com.example.neuronest.Task2.socialquiz.Question1(
            questionImage = R.drawable.classdq2,
            optionTexts = listOf("Please", "Excuse Me", "Sorry"),
            correctTextIndex = 2,
            dropThresholdY = 500f
        ),
        com.example.neuronest.Task2.socialquiz.Question1(
            questionImage = R.drawable.classdq3,
            optionTexts = listOf("May I?", "Excuse Me", "Hello"),
            correctTextIndex = 0,
            dropThresholdY = 500f
        ),
        com.example.neuronest.Task2.socialquiz.Question1(
            questionImage = R.drawable.classdq4,
            optionTexts = listOf("Please", "Help", "Sorry"),
            correctTextIndex = 1,
            dropThresholdY = 500f
        ),
        com.example.neuronest.Task2.socialquiz.Question1(
            questionImage = R.drawable.classdq5,
            optionTexts = listOf("Sorry", "Excuse Me", "Can I?"),
            correctTextIndex = 2,
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
            destinationRoute = "ClassSelectionScreen"
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

        DragAndDropQuestionScreen1(
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

