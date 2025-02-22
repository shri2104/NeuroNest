package com.example.neuronest

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("NeuroNest", fontWeight = FontWeight.Bold)
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF228B22),
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xFF228B22),
                content = {
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        IconButton(onClick = { }) {
                            Icon(
                                Icons.Default.Home,
                                contentDescription = "Home",
                                tint = Color.White
                            )
                        }
                        IconButton(onClick = { }) {
                            Icon(
                                Icons.Default.CheckCircle,
                                contentDescription = "Tasks",
                                tint = Color.White
                            )
                        }
                        IconButton(onClick = {  }) {
                            Icon(
                                Icons.Default.Settings,
                                contentDescription = "Settings",
                                tint = Color.White
                            )
                        }
                    }
                }
            )
        },
        content = { paddingValues ->
            TaskGrid(modifier = Modifier.padding(paddingValues),navController)
        }
    )
}
@Composable
fun TaskGrid(modifier: Modifier = Modifier, navController: NavHostController) {
    val tasks = listOf(
        "Task 1" to R.drawable.rb_5272,
        "Task 2" to R.drawable.rb_5272,
        "Task 3" to R.drawable.rb_5272,
        "Task 5" to R.drawable.rb_5272,
        "Task 6" to R.drawable.rb_5272,
        "Task 7" to R.drawable.rb_5272,
        "Task 8" to R.drawable.rb_5272,
        "Task 9" to R.drawable.rb_5272,
        "Task 10" to R.drawable.rb_5272,
    )
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.rb_55653),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Choose a Task",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(16.dp)
            ) {
                items(tasks) { (taskName, taskImage) ->
                    TaskItem(taskName = taskName, taskImage = taskImage,onClick={
                        if (taskName == "Task 1") {
                            navController.navigate("Task1SelectionScreen")
                        }
                        if (taskName == "Task 2") {
                            navController.navigate("Task2SelectionScreen")
                        }
                    })
                }
            }
        }
    }
}
@Composable
fun TaskItem(taskName: String, taskImage: Int,onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable {onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = taskImage),
                    contentDescription = taskName,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(64.dp)
                        .background(Color(0xFFFFF59D), shape = CircleShape)
                        .padding(8.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(taskName, fontWeight = FontWeight.SemiBold, color = Color(0xFF37474F))
            }
        }
    }
}
