package com.example.neuronest.Task2

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.neuronest.R

data class SelectableImage(
    val resId: Int,
    val isCorrect: Boolean,
    var isClicked: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageSelectionScreen(navController: NavHostController) {
    val context = LocalContext.current
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    var images by remember {
        mutableStateOf(
            listOf(
                SelectableImage(R.drawable.sq1, false),
                SelectableImage(R.drawable.sq2, true),
                SelectableImage(R.drawable.sq3, false),
                SelectableImage(R.drawable.sq4, false),
                SelectableImage(R.drawable.sq5, true),
                SelectableImage(R.drawable.sq6, true),
                SelectableImage(R.drawable.sq7, false),
                SelectableImage(R.drawable.sq8, false)
            )
        )
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Select Good Manners", fontSize = 22.sp)
                },
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
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            for (row in 0 until 4) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    for (col in 0 until 2) {
                        val index = row * 2 + col
                        if (index < images.size) {
                            val image = images[index]

                            val backgroundColor = when {
                                image.isClicked && image.isCorrect -> Color(0xFF64B5F6) // Blue
                                image.isClicked && !image.isCorrect -> Color(0xFFEF5350) // Red
                                else -> MaterialTheme.colorScheme.surface
                            }

                            Box(
                                modifier = Modifier
                                    .size(150.dp)
                                    .background(backgroundColor, shape = RoundedCornerShape(16.dp))
                                    .clickable(enabled = !image.isClicked) {
                                        images = images.toMutableList().also {
                                            it[index] = it[index].copy(isClicked = true)
                                        }
                                        val msg = if (image.isCorrect) "Correct Answer" else "Incorrect Answer"
                                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = image.resId),
                                    contentDescription = "Option ${index + 1}",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(12.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

