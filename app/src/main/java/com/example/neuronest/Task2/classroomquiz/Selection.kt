package com.example.neuronest.Task2.classroomquiz

import android.widget.Toast
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.neuronest.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun classroomselection(navController: NavHostController) {
    val context = LocalContext.current
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    var currentPage by remember { mutableStateOf(0) }

    val allImages = listOf(
        SelectableImage(R.drawable.classsel1, false),
        SelectableImage(R.drawable.classsel2, true),
        SelectableImage(R.drawable.classsel3, true),
        SelectableImage(R.drawable.classsel4, false),
        SelectableImage(R.drawable.classsel5, true),
        SelectableImage(R.drawable.classsel6, false),
        SelectableImage(R.drawable.classsel7, true),
        SelectableImage(R.drawable.classsel8, false)
    )

    var images by remember { mutableStateOf(allImages) }

    val imagesPerPage = 4
    val totalPages = (images.size + imagesPerPage - 1) / imagesPerPage
    val currentImages = images.drop(currentPage * imagesPerPage).take(imagesPerPage)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Select Good Manners", fontSize = 22.sp) },
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
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Select good manners from below:",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(vertical = 16.dp)
            )

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
                        image.isClicked && image.isCorrect -> Color(0xFF64B5F6)
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
                                val msg = if (image.isCorrect) "Correct Answer" else "Incorrect Answer"
                                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
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
                    Text("Previous")
                }
                Spacer(modifier = Modifier.width(12.dp))
                Button(
                    onClick = { if (currentPage < totalPages - 1) currentPage++ },
                    enabled = currentPage < totalPages - 1,
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp)
                ) {
                    Text("Next")
                }
            }
        }
    }
}


data class SelectableImage(
    val resId: Int,
    val isCorrect: Boolean,
    val isClicked: Boolean = false
)
