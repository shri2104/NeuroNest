package com.example.neuronest.Task4.task4quiz

import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.neuronest.Task4.Selection.Task4ActivityType
import lastquizScreen
import com.example.neuronest.R

data class Task4McqOption(
    val text: String,
    val isCorrect: Boolean
)

data class Task4McqPage(
    val question: String,
    val options: List<Task4McqOption>
)

object Task4McqProvider {

    fun getQuestions(activity: Task4ActivityType): List<Task4McqPage> =
        when (activity) {

            Task4ActivityType.HITTING -> listOf(
                Task4McqPage(
                    question = ".......... is not okay",
                    options = listOf(
                        Task4McqOption("Helping", false),
                        Task4McqOption("Hitting", true)
                    )
                ),
                Task4McqPage(
                    question = "My hands are for .........",
                    options = listOf(
                        Task4McqOption("greeting", true),
                        Task4McqOption("Hitting", false)
                    )
                ),
                Task4McqPage(
                    question = "Hitting my friends and teachers will make them feel .........",
                    options = listOf(
                        Task4McqOption("sad", true),
                        Task4McqOption("Happy", false)
                    )
                ),
                Task4McqPage(
                    question = "My friends and teachers are happy when i keep my ........ to myself.",
                    options = listOf(
                        Task4McqOption("hands", true),
                        Task4McqOption("books", false)
                    )
                ),
                Task4McqPage(
                    question = "When I get upset I can go to my safe ........",
                    options = listOf(
                        Task4McqOption("place", true),
                        Task4McqOption("shop", false)
                    )
                )
            )

            Task4ActivityType.PINCHING -> listOf(
                Task4McqPage(
                    question = "What should I do when i feel like pinching ?",
                    options = listOf(
                        Task4McqOption("Walk away and deep breaths", true),
                        Task4McqOption("Pinch the person", false)
                    )
                ),
                Task4McqPage(
                    question = "Why is pinching not ok ?",
                    options = listOf(
                        Task4McqOption("It makes people feel sad and angry", true),
                        Task4McqOption("It doesn't hurt anyone", false)
                    )
                ),
                Task4McqPage(
                    question = "Wgat is good replacement behavior for pinching when I feel angry ?",
                    options = listOf(
                        Task4McqOption("Keep your hands to yourself", false),
                        Task4McqOption("Squeeze a stress ball", true)
                    )
                ),
            )

            else -> emptyList()
        }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun Task4McqScreen(
    navController: NavHostController,
    activity: Task4ActivityType
) {
    val context = LocalContext.current

    val lightGreen = Color(0xFF81C784)
    val darkGreen = Color(0xFF388E3C)

    val pages = remember { Task4McqProvider.getQuestions(activity) }

    var currentPage by remember { mutableStateOf(0) }
    var selectedIndex by remember { mutableStateOf<Int?>(null) }
    var showLastScreen by remember { mutableStateOf(false) }

    val currentQuestion = pages[currentPage]
    val options = currentQuestion.options

    val onPreviousQuestion = {
        if (currentPage > 0) {
            currentPage--
            selectedIndex = null
        }
    }

    val onNextQuestion = {
        if (currentPage < pages.lastIndex) {
            currentPage++
            selectedIndex = null
        } else {
            showLastScreen = true
        }
    }


    fun playSound(resId: Int) {
        MediaPlayer.create(context, resId).apply {
            start()
            setOnCompletionListener { release() }
        }
    }

    // ✅ Navigate to last screen after final question
    if (showLastScreen) {
        lastquizScreen(
            navController = navController,
            destinationRoute = "Task4QuizSelection/${activity.name}"
        )
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Choose the Correct Answer", fontSize = 28.sp) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = darkGreen,
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Button(
                    onClick = onPreviousQuestion,
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp).padding(start = 12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = darkGreen)
                ) {
                    Text("Previous", fontSize = 28.sp, color = Color.White)
                }

                Button(
                    onClick = onNextQuestion,
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp).padding(start = 12.dp, end = 6.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = darkGreen)
                ) {
                    Text(
                        "Next",
                        fontSize = 28.sp,
                        color = Color.White
                    )
                }
            }
        }

    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 54.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = currentQuestion.question,
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(40.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth(0.85f), // 👈 tablet width usage
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                options.forEachIndexed { index, option ->

                    val bgColor = when {
                        selectedIndex == null -> Color(0xFFE8F5E9)
                        index == selectedIndex && !option.isCorrect -> Color(0xFFEF5350)
                        option.isCorrect -> Color(0xFF81C784)
                        else -> Color(0xFFE8F5E9)
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp) // 👈 bigger for tablet
                            .background(bgColor, RoundedCornerShape(18.dp))
                            .clickable(enabled = selectedIndex == null) {
                                selectedIndex = index
                                playSound(
                                    if (option.isCorrect) R.raw.correct else R.raw.wrng
                                )
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = option.text,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f)) // 👈 pushes buttons to bottom
        }

    }
}
