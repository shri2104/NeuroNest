package com.example.neuronest.Task1.task1quiz


import android.media.MediaPlayer
import android.widget.Toast
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import lastquizScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun task1selectionquiz2(navController: NavHostController) {
    val context = LocalContext.current
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    var currentPage by remember { mutableStateOf(0) }
    var isFinished by remember { mutableStateOf(false) }
    var showLastScreen by remember { mutableStateOf(false) }

    val allImages = listOf(
        SelectableImage(R.drawable.task1sel9, false),
        SelectableImage(R.drawable.task1sel10, false),
        SelectableImage(R.drawable.task1sel11, true),
        SelectableImage(R.drawable.task1sel12, true),
        SelectableImage(R.drawable.task1sel13, false),
        SelectableImage(R.drawable.task1sel15, true),
        SelectableImage(R.drawable.task1sel14, false),
        SelectableImage(R.drawable.task1sel16, true)
    )

    var images by remember { mutableStateOf(allImages) }

    val imagesPerPage = 4
    val totalPages = (images.size + imagesPerPage - 1) / imagesPerPage
    val currentImages = images.drop(currentPage * imagesPerPage).take(imagesPerPage)

    fun playSound(resId: Int) {
        val mediaPlayer = MediaPlayer.create(context, resId)
        mediaPlayer?.apply {
            start()
            setOnCompletionListener { release() }
        }
    }

    if (isFinished && !showLastScreen) {
        LaunchedEffect(Unit) {
            kotlinx.coroutines.delay(1500)
            showLastScreen = true
        }
    }
    if (showLastScreen) {
        lastquizScreen(navController = navController, destinationRoute = "Task1SelectionScreen3")
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Select Good Touch Images", fontSize = 35.sp) },
                navigationIcon = {
                    IconButton(onClick = { backDispatcher?.onBackPressed() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF2196F3),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
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
                text = "Tap the images that show Good Touch:",
                fontSize = 35.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                items(currentImages.size) { index ->
                    val image = currentImages[index]
                    val globalIndex = currentPage * imagesPerPage + index

                    val backgroundColor = when {
                        image.isClicked && image.isCorrect -> Color(0xFF90EE90)
                        image.isClicked && !image.isCorrect -> Color(0xFFEF5350)
                        else -> MaterialTheme.colorScheme.surface
                    }

                    Box(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .background(backgroundColor, RoundedCornerShape(16.dp))
                            .clickable(enabled = !image.isClicked) {
                                images = images.toMutableList().also {
                                    it[globalIndex] = it[globalIndex].copy(isClicked = true)
                                }
                                val msg = if (image.isCorrect) {
                                    playSound(R.raw.correct)
                                    "Correct Answer"
                                } else {
                                    playSound(R.raw.wrng)
                                    "Incorrect Answer"
                                }
                                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()

                                val updatedCurrentImages = images.drop(currentPage * imagesPerPage).take(imagesPerPage)
                                if (currentPage == totalPages - 1) {
                                    val correctClickedOnLastPage = updatedCurrentImages.count { it.isCorrect && it.isClicked }
                                    if (correctClickedOnLastPage >= 2) {
                                        isFinished = true
                                    }
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = image.resId),
                            contentDescription = "Option ${globalIndex + 1}",
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(12.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { if (currentPage > 0) currentPage-- },
                    enabled = currentPage > 0,
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp)
                ) {
                    Text("Previous", fontSize = 20.sp)
                }
                Spacer(modifier = Modifier.width(12.dp))
                val correctSelections = images.count { it.isCorrect && it.isClicked }
                val allCurrentClicked = currentImages.all { it.isClicked }
                Button(
                    onClick = { if (currentPage < totalPages - 1) currentPage++ },
                    enabled = currentPage < totalPages - 1 && (correctSelections == 2 || allCurrentClicked),
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp)
                ) {
                    Text("Next", fontSize = 20.sp)
                }
            }
        }
    }
}

