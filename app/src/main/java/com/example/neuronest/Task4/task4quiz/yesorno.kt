package com.example.neuronest.Task4.task4quiz

import android.media.MediaPlayer
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.neuronest.R
import com.example.neuronest.Task4.Selection.Task4ActivityType
import lastquizScreen

data class Task4YesNoQuestion(
    val questionText: String,
    val answer: Boolean
)

object Task4YesNoProvider {

    fun getQuestions(activity: Task4ActivityType): List<Task4YesNoQuestion> =
        when (activity) {

            Task4ActivityType.HITTING -> listOf(
                Task4YesNoQuestion(
                    questionText = "Is it ok to hit someone when we are mad?",
                    answer = false
                ),
                Task4YesNoQuestion(
                    questionText = "Should we ask for help when we feel upset?",
                    answer = true
                ),
                Task4YesNoQuestion(
                    questionText = "Is it kind to use gentle hands ?",
                    answer = true
                ),
                Task4YesNoQuestion(
                    questionText = "Can we take deep breaths to caml down ?",
                    answer = true
                )
            )

            Task4ActivityType.PINCHING -> listOf(
                Task4YesNoQuestion(
                    questionText = "Does pinching hurt ? ",
                    answer = true
                ),
                Task4YesNoQuestion(
                    questionText = "Can we use our words to say what I need ? ",
                    answer = true
                ),
                Task4YesNoQuestion(
                    questionText = "Is it ok to pinch when we are angry? ",
                    answer = false
                ),
                Task4YesNoQuestion(
                    questionText = "Is it good to respect other people's body ? ",
                    answer = true
                ),
                Task4YesNoQuestion(
                    questionText = "Is it ok to pinch myself? ",
                    answer = false
                )
            )

            Task4ActivityType.BITING -> listOf(
                Task4YesNoQuestion(
                    questionText = "Is it ok to bite people?",
                    answer = false
                ),
                Task4YesNoQuestion(
                    questionText = "Do bites hurt people ?",
                    answer = true
                ),
                Task4YesNoQuestion(
                    questionText = "Can we bite our friends when we are angry? ",
                    answer = false
                ),
                Task4YesNoQuestion(
                    questionText = "Should i bite on a special chew toy instead of a person? ",
                    answer = true
                ),
                Task4YesNoQuestion(
                    questionText = "Will people feel sad or scared if i bite them?  ",
                    answer = true
                )
            )

            else -> emptyList()
        }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Task4YesNoScreen(
    navController: NavHostController,
    activity: Task4ActivityType
) {
    val context = LocalContext.current
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    val questions = remember { Task4YesNoProvider.getQuestions(activity) }
    var currentIndex by remember { mutableStateOf(0) }
    var showSnackbar by remember { mutableStateOf(false) }
    var quizFinished by remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }

    val greenDark = Color(0xFF2E7D32)
    val greenLight = Color(0xFF4CAF50)

    // ✅ SHOW LAST SCREEN PROPERLY
    if (quizFinished) {
        lastquizScreen(
            navController = navController,
            destinationRoute = "Task4QuizSelection/${activity.name}"
        )
        return
    }

    val question = questions[currentIndex]

    LaunchedEffect(showSnackbar) {
        if (showSnackbar) {
            snackbarHostState.showSnackbar("Incorrect answer")
            showSnackbar = false
        }
    }

    fun playSound(resId: Int) {
        MediaPlayer.create(context, resId).apply {
            start()
            setOnCompletionListener { release() }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Yes / No Quiz", fontSize = 35.sp) },
                navigationIcon = {
                    IconButton(onClick = { backDispatcher?.onBackPressed() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = greenDark,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                // ⬅️ PREVIOUS
                Button(
                    onClick = {
                        if (currentIndex > 0) {
                            currentIndex--
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp).padding(start = 12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF388E3C)),
                    shape = RoundedCornerShape(30.dp),
                ) {
                    Text("Previous", fontSize = 28.sp, color = Color.White)
                }

                // ➡️ NEXT
                Button(
                    onClick = {
                        if (currentIndex < questions.lastIndex) {
                            currentIndex++
                        } else {
                            quizFinished = true
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = greenDark),
                    shape = RoundedCornerShape(30.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp).padding(start = 12.dp, end = 6.dp)
                ) {
                    Text(
                        text = "Next",
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
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Is this a good action?",
                fontSize = 38.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(40.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(RoundedCornerShape(28.dp))
                    .background(Color(0xFFE8F5E9)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = question.questionText,
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(24.dp)
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                // ✅ YES
                IconButton(
                    onClick = {
                        if (question.answer) {
                            playSound(R.raw.correct)
                            if (currentIndex < questions.lastIndex) {
                                currentIndex++
                            } else {
                                quizFinished = true
                            }
                        } else {
                            playSound(R.raw.wrng)
                            showSnackbar = true
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(110.dp)
                        .padding(end = 16.dp)
                        .background(greenLight, RoundedCornerShape(24.dp))
                ) {
                    Icon(
                        Icons.Default.Check,
                        contentDescription = "Yes",
                        tint = Color.White,
                        modifier = Modifier.size(60.dp)
                    )
                }

                // ❌ NO
                IconButton(
                    onClick = {
                        if (!question.answer) {
                            playSound(R.raw.correct)
                            if (currentIndex < questions.lastIndex) {
                                currentIndex++
                            } else {
                                quizFinished = true
                            }
                        } else {
                            playSound(R.raw.wrng)
                            showSnackbar = true
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(110.dp)
                        .padding(start = 16.dp)
                        .background(Color(0xFFC62828), RoundedCornerShape(24.dp))
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "No",
                        tint = Color.White,
                        modifier = Modifier.size(60.dp)
                    )
                }

            }

        }
    }
}
