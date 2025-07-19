package com.example.neuronest.Task3.task3Quiz

import android.media.MediaPlayer
import android.widget.Toast
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.neuronest.R
import kotlinx.coroutines.delay
import lastquizScreen

data class McqImage(
    val resId: Int,
    val isCorrect: Boolean,
    var isSelected: Boolean = false
)

data class McqPage(
    val questionImageResId: Int,
    val options: List<McqImage>
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun McqQuizScreen(navController: NavHostController) {
    val babyPink = Color(0xFFFF80A6)
    val deepPink = Color(0xFFE91E63)
    val context = LocalContext.current
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    val pages = listOf(
        McqPage(
            questionImageResId = R.drawable.mcq1,
            options = listOf(
                McqImage(R.drawable.e6, true),
                McqImage(R.drawable.e7, false),
                McqImage(R.drawable.e4, false),
                McqImage(R.drawable.e9, false)
            )
        ),
        McqPage(
            questionImageResId = R.drawable.mcq2,
            options = listOf(
                McqImage(R.drawable.e5, true),
                McqImage(R.drawable.e4, false),
                McqImage(R.drawable.e3, false),
                McqImage(R.drawable.e4, false)
            )
        ),
        McqPage(
            questionImageResId = R.drawable.mcq3,
            options = listOf(
                McqImage(R.drawable.e9, false),
                McqImage(R.drawable.e2, false),
                McqImage(R.drawable.e3, false),
                McqImage(R.drawable.e4, true)
            )
        ),
        McqPage(
            questionImageResId = R.drawable.mcq4,
            options = listOf(
                McqImage(R.drawable.e1, true),
                McqImage(R.drawable.e2, false),
                McqImage(R.drawable.e3, false),
                McqImage(R.drawable.e4, false)
            )
        ),
        McqPage(
            questionImageResId = R.drawable.mcq5,
            options = listOf(
                McqImage(R.drawable.e1, false),
                McqImage(R.drawable.e2, false),
                McqImage(R.drawable.e3, false),
                McqImage(R.drawable.e9, true)
            )
        )

    )

    var currentPage by remember { mutableStateOf(0) }
    var selectedIndex by remember { mutableStateOf<Int?>(null) }
    var showLastScreen by remember { mutableStateOf(false) }

    val currentQuestion = pages[currentPage]
    val options = currentQuestion.options

    fun playSound(resId: Int) {
        val mediaPlayer = MediaPlayer.create(context, resId)
        mediaPlayer?.start()
        mediaPlayer.setOnCompletionListener { it.release() }
    }

    // ✅ Delay before navigating to final screen
    if (selectedIndex != null && currentPage == pages.lastIndex && !showLastScreen) {
        LaunchedEffect(Unit) {
            delay(1500)
            showLastScreen = true
        }
    }

    // ✅ Navigate to last screen if flag is true
    if (showLastScreen) {
        lastquizScreen(navController = navController, destinationRoute = "Task3SelectionScreen")
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("MCQs", fontSize = 30.sp) },
                navigationIcon = {
                    IconButton(onClick = { backDispatcher?.onBackPressed() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = deepPink,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //Spacer(modifier = Modifier.height(16.dp))
            Text("Choose the Correct Answer", fontSize = 28.sp, fontWeight = FontWeight.Bold)
            Image(
                painter = painterResource(id = currentQuestion.questionImageResId),
                contentDescription = "Question Image",
                modifier = Modifier
                    .fillMaxWidth().padding(top = 10.dp)
                    .height(400.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Column(
                //verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 35.dp)
            ) {
                for (row in 0 until 2) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        for (col in 0 until 2) {
                            val index = row * 2 + col
                            if (index < options.size) {
                                val option = options[index]
                                val bgColor = when {
                                    selectedIndex == null -> MaterialTheme.colorScheme.surface
                                    index == selectedIndex && !option.isCorrect -> Color(0xFFEF5350)
                                    option.isCorrect -> Color(0xFF81C784)
                                    else -> MaterialTheme.colorScheme.surface
                                }

                                Box(
                                    modifier = Modifier
                                        .size(300.dp)
                                        .background(bgColor, RoundedCornerShape(12.dp))
                                        .clickable(enabled = selectedIndex == null) {
                                            selectedIndex = index
                                            option.isSelected = true
                                            if (option.isCorrect) {
                                                playSound(R.raw.correct)
                                                Toast.makeText(context, "Correct!", Toast.LENGTH_SHORT).show()
                                            } else {
                                                playSound(R.raw.wrng)
                                                Toast.makeText(context, "Wrong! See the correct one.", Toast.LENGTH_SHORT).show()
                                            }
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        painter = painterResource(id = option.resId),
                                        contentDescription = "Option ${index + 1}",
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(4.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(5.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth().padding(bottom = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        if (currentPage > 0) {
                            currentPage--
                            selectedIndex = null
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = babyPink),
                    modifier = Modifier.weight(1f),
                    enabled = currentPage > 0
                ) {
                    Text("Previous", fontSize = 25.sp)
                }

                Button(
                    onClick = {
                        if (currentPage < pages.lastIndex) {
                            currentPage++
                            selectedIndex = null
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = babyPink),
                    modifier = Modifier.weight(1f),
                    enabled = selectedIndex != null
                ) {
                    Text("Next", fontSize = 25.sp)
                }
            }

        }
    }
}
