package com.example.neuronest.Task3.task3Quiz

import android.media.MediaPlayer
import android.widget.Toast
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import com.example.neuronest.Task2.socialquiz.SelectableImage
import com.example.neuronest.R
import kotlinx.coroutines.delay
import lastquizScreen


data class QuizPage(
    val title: String,
    val images: List<SelectableImage>
)

@Composable
fun Task3SelectionQuiz(navController: NavHostController) {
    val pages = listOf(
        QuizPage(
            title = "Select where boy is happy:",
            images = listOf(
                SelectableImage(R.drawable.es1, false),
                SelectableImage(R.drawable.es2, false),
                SelectableImage(R.drawable.es3, true),
                SelectableImage(R.drawable.es4, true)
            )
        ),
        QuizPage(
            title = "Select where boy is calm",
            images = listOf(
                SelectableImage(R.drawable.calm_dark, true),
                SelectableImage(R.drawable.excited_fair, false),
                SelectableImage(R.drawable.calm_fair, true),
                SelectableImage(R.drawable.funny_dark, false)
            )
        ),
        QuizPage(
            title = "Select where boy is nervous",
            images = listOf(
                SelectableImage(R.drawable.grumpy_fair, false),
                SelectableImage(R.drawable.nervous_dark, true),
                SelectableImage(R.drawable.nervous_fair, true),
                SelectableImage(R.drawable.sicky_fair, false)
            )
        )
    )

    SelectionQuizScreen(
        navController = navController,
        pages = pages,
        destinationRoute = "Task3SelectionScreen"
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectionQuizScreen(
    navController: NavHostController,
    pages: List<QuizPage>,
    destinationRoute: String
) {
    val babyPink = Color(0xFFFF80A6)
    val deepPink = Color(0xFFE91E63)
    val context = LocalContext.current
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    var currentPage by remember { mutableStateOf(0) }
    val currentQuizPage = pages[currentPage]

    var images by remember { mutableStateOf(currentQuizPage.images) }
    val allClicked = images.all { it.isClicked }
    val isLastPage = currentPage == pages.lastIndex
    var showLastScreen by remember { mutableStateOf(false) }

    fun playSound(resId: Int) {
        val mediaPlayer = MediaPlayer.create(context, resId)
        mediaPlayer?.apply {
            start()
            setOnCompletionListener { release() }
        }
    }

    if (showLastScreen) {
        lastquizScreen(navController = navController, destinationRoute = destinationRoute)
        return
    }

    if (allClicked && isLastPage && !showLastScreen) {
        LaunchedEffect(Unit) {
            kotlinx.coroutines.delay(1500)
            showLastScreen = true
        }
    } else {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Emotions", fontSize = 35.sp) },
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
            },
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(25.dp))
                Text(currentQuizPage.title, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                Column(
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    for (row in 0 until 2) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            for (col in 0 until 2) {
                                val index = row * 2 + col
                                if (index < images.size) {
                                    val image = images[index]

                                    val backgroundColor = when {
                                        image.isClicked && image.isCorrect -> Color(0xFF64B5F6)
                                        image.isClicked && !image.isCorrect -> Color(0xFFEF5350)
                                        else -> MaterialTheme.colorScheme.surface
                                    }

                                    Box(
                                        modifier = Modifier
                                            .size(420.dp)
                                            .background(backgroundColor, RoundedCornerShape(16.dp))
                                            .clickable(enabled = !image.isClicked) {
                                                images = images.toMutableList().also {
                                                    it[index] = it[index].copy(isClicked = true)
                                                }
                                                val msg = if (image.isCorrect) {
                                                    playSound(R.raw.correct)
                                                    "Correct Answer"
                                                } else {
                                                    playSound(R.raw.wrng)
                                                    "Incorrect Answer"
                                                }
                                                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                                            },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Image(
                                            painter = painterResource(id = image.resId),
                                            contentDescription = "Option ${index + 1}",
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(15.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            if (currentPage > 0) {
                                currentPage--
                                images = pages[currentPage].images
                            }
                        },
                        enabled = currentPage > 0,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(babyPink)
                    ) {
                        Text("Previous", fontSize = 25.sp)
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    val correctSelections = images.count { it.isCorrect && it.isClicked }
                    val allCurrentClicked = images.all { it.isClicked }
                    Button(
                        onClick = {
                            if (currentPage < pages.lastIndex) {
                                currentPage++
                                images = pages[currentPage].images
                            }
                        },
                        enabled = currentPage < pages.lastIndex && (correctSelections >= 2 || allCurrentClicked),
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(babyPink)
                    ) {
                        Text("Next", fontSize = 25.sp)
                    }
                }
            }
        }
    }
}
