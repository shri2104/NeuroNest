package com.example.neuronest.Task4.Selection
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.neuronest.R
import com.example.neuronest.Task2.selection.classSelectionScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.neuronest.Task4.Quiz.task4QuizSelectionScreen


enum class Task4ActivityType {
    HITTING, PINCHING, SPITTING, BITING, SHOUTING
}

enum class Task4QuizType {
    DRAG, SELECTION, YESNO
}

fun activityToType(activity: String): Task4ActivityType {
    return when (activity) {
        "No Hitting" -> Task4ActivityType.HITTING
        "No Pinching" -> Task4ActivityType.PINCHING
        "No Spitting" -> Task4ActivityType.SPITTING
        "No Biting" -> Task4ActivityType.BITING
        "No Shouting" -> Task4ActivityType.SHOUTING
        else -> Task4ActivityType.HITTING
    }
}

@Composable
fun task4Selection(navController: NavHostController) {
    task4selection(
        navController = navController,
        backgroundImage = R.drawable.photo_2025_08_05_00_17_00,
        firstButtonText = "Happy Learning",
        secondButtonText = "Brain Fun",
        onFirstButtonClick = {
            navController.navigate("HappyLearningList")
        },
        onSecondButtonClick = {
            navController.navigate("BrainFunList")
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun task4selection(
    navController: NavHostController,
    backgroundImage: Int,
    firstButtonText: String,
    secondButtonText: String,
    onFirstButtonClick: () -> Unit,
    onSecondButtonClick: () -> Unit
) {
    val lightGreen = Color(0xFF90EE90) // light green
    val darkGreen = Color(0xFF388E3C) // primary for top app bar

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Social Stories",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.White,
                        fontSize = 35.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = darkGreen,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("Dashboard") }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Image(
                painter = painterResource(id = backgroundImage),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(50.dp))

                Button(
                    onClick = onFirstButtonClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(100.dp)
                        .padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = darkGreen)
                ) {
                    Text(
                        text = firstButtonText,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                Spacer(Modifier.height(20.dp))

                Button(
                    onClick = onSecondButtonClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(100.dp)
                        .padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = darkGreen)
                ) {
                    Text(
                        text = secondButtonText,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Task4ActivityListScreen(
    navController: NavHostController,
    backgroundImage: Int,
    onActivityClick: (String) -> Unit
) {
    val darkGreen = Color(0xFF388E3C)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Social Stories",
                        color = Color.White,
                        fontSize = 32.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = darkGreen
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("task4selection1") }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                }
            )
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            Image(
                painter = painterResource(backgroundImage),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                val activities = listOf(
                    "No Hitting",
                    "No Pinching",
                    "No Spitting",
                    "No Biting",
                    "No Shouting"
                )

                activities.forEach { activity ->
                    Button(
                        onClick = { onActivityClick(activity) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                            .padding(vertical = 6.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = darkGreen)
                    ) {
                        Text(
                            text = activity,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun task4HappyLearningList(navController: NavHostController) {
    Task4ActivityListScreen(
        navController = navController,
        backgroundImage = R.drawable.task4bg,
        onActivityClick = {
            navController.navigate("task4presentation")
        }
    )
}

@Composable
fun task4BrainFunList(navController: NavHostController) {
    Task4ActivityListScreen(
        navController = navController,
        backgroundImage = R.drawable.task4bg,
        onActivityClick = { activity ->
            val type = activityToType(activity)
            navController.navigate("Task4QuizSelection/${type.name}")
        }
    )
}


