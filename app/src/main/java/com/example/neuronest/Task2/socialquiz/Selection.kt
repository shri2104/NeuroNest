package com.example.neuronest.Task2.socialquiz

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.neuronest.R
import lastquizScreen

data class SelectableImage(
    val resId: Int,
    val isCorrect: Boolean,
    val isClicked: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SocialSelectionQuiz(navController: NavHostController) {
    val context = LocalContext.current
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    var currentPage by remember { mutableStateOf(0) }

    val allImages = listOf(
        SelectableImage(R.drawable.socialsel1, false),
        SelectableImage(R.drawable.socialsel2, true),
        SelectableImage(R.drawable.socialsel3, true),
        SelectableImage(R.drawable.socialsel4, false),
        SelectableImage(R.drawable.socialsel5, true),
        SelectableImage(R.drawable.socialsel6, false),
        SelectableImage(R.drawable.socialsel7, true),
        SelectableImage(R.drawable.socialsel8, false)
    )

    var images by remember { mutableStateOf(allImages) }

    val imagesPerPage = 4
    val totalPages = (images.size + imagesPerPage - 1) / imagesPerPage
    val currentImages = images.drop(currentPage * imagesPerPage).take(imagesPerPage)

    val allClicked = images.all { it.isClicked }
    val isLastPage = currentPage == totalPages - 1
    var showLastScreen by remember { mutableStateOf(false) }

    fun playSound(resId: Int) {
        val mediaPlayer = MediaPlayer.create(context, resId)
        mediaPlayer?.apply {
            start()
            setOnCompletionListener { release() }
        }
    }
    if (showLastScreen) {
        lastquizScreen(navController = navController, destinationRoute = "SocialSelectionScreen")
        return
    }
    if (allClicked && isLastPage && !showLastScreen) {
        LaunchedEffect(Unit) {
            kotlinx.coroutines.delay(1500)
            showLastScreen = true
        }
    }else {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Select Good Manners", fontSize = 35.sp) },
                    navigationIcon = {
                        IconButton(onClick = { backDispatcher?.onBackPressed() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFF52360C),
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

                Text(
                    text = "Select good manners from below:",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 12.dp),
                    fontSize = 35.sp
                )
                Spacer(modifier = Modifier.height(20.dp))

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
                                if (index < currentImages.size) {
                                    val image = currentImages[index]
                                    val globalIndex = currentPage * imagesPerPage + index

                                    val backgroundColor = when {
                                        image.isClicked && image.isCorrect -> Color(0xFF90EE90)
                                        image.isClicked && !image.isCorrect -> Color(0xFFEF5350)
                                        else -> MaterialTheme.colorScheme.surface
                                    }

                                    Box(
                                        modifier = Modifier
                                            .size(420.dp)
                                            .background(backgroundColor, RoundedCornerShape(16.dp))
                                            .clickable(enabled = !image.isClicked) {
                                                images = images.toMutableList().also {
                                                    it[globalIndex] =
                                                        it[globalIndex].copy(isClicked = true)
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
                                            contentDescription = "Option ${globalIndex + 1}",
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
                        onClick = { if (currentPage > 0) currentPage-- },
                        enabled = currentPage > 0,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Previous", fontSize = 25.sp)
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    val correctSelections = images.count { it.isCorrect && it.isClicked }
                    val allCurrentClicked = currentImages.all { it.isClicked }
                    Button(
                        onClick = { if (currentPage < totalPages - 1) currentPage++ },
                        enabled = currentPage < totalPages - 1 && (correctSelections == 2 || allCurrentClicked),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Next", fontSize = 25.sp)
                    }
                }
            }
        }
    }
}


