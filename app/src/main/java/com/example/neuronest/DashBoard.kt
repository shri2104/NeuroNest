package com.example.neuronest

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavHostController) {
    var expanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("NeuroNest", fontWeight = FontWeight.Bold, fontSize = 35.sp)
                },
                actions = {
                    Box {
                        IconButton(onClick = { expanded = true }) {
                            Icon(
                                imageVector = Icons.Default.List,
                                contentDescription = "Menu",
                                tint = Color.White
                            )
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier
                                .width(280.dp)   // wider menu for tablet
                                .heightIn(max = 400.dp) // taller if needed
                        ) {
                            DropdownMenuItem(
                                text = { Text("Profile", fontSize = 20.sp) },
                                onClick = {
                                    expanded = false
                                    navController.navigate("profile")
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("About App", fontSize = 20.sp) },
                                onClick = {
                                    expanded = false
                                    navController.navigate("about")
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Tutorial", fontSize = 20.sp) },
                                onClick = {
                                    expanded = false
                                    navController.navigate("tutorial")
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Settings", fontSize = 20.sp) },
                                onClick = {
                                    expanded = false
                                    navController.navigate("settings")
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Feedback", fontSize = 20.sp) },
                                onClick = {
                                    expanded = false
                                    navController.navigate("feedback")
                                }
                            )
                        }
                    }
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
                        IconButton(onClick = { }) {
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
            TaskGrid(modifier = Modifier.padding(paddingValues), navController)
        }
    )
}

@Composable
fun DashboardMenu(
    expanded: Boolean,
    onDismiss: () -> Unit,
    navController: NavHostController
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { onDismiss() },
        modifier = Modifier
            .widthIn(min = 200.dp, max = 300.dp) // wider for tablet
            .heightIn(max = 400.dp) // allow scroll if too long
    ) {
        // Wrap in a scrollable column
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState()) // enables scrolling
                .padding(8.dp) // more padding for tablet
        ) {
            DropdownMenuItem(
                text = { Text("Profile", fontSize = 20.sp) },
                onClick = {
                    onDismiss()
                    navController.navigate("profile")
                }
            )
            DropdownMenuItem(
                text = { Text("About App", fontSize = 20.sp) },
                onClick = {
                    onDismiss()
                    navController.navigate("about")
                }
            )
            DropdownMenuItem(
                text = { Text("Tutorial", fontSize = 20.sp) },
                onClick = {
                    onDismiss()
                    navController.navigate("tutorial")
                }
            )
            DropdownMenuItem(
                text = { Text("Settings", fontSize = 20.sp) },
                onClick = {
                    onDismiss()
                    navController.navigate("settings")
                }
            )
            DropdownMenuItem(
                text = { Text("Feedback", fontSize = 20.sp) },
                onClick = {
                    onDismiss()
                    navController.navigate("feedback")
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("About App", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text("This is a dummy About App screen.", fontSize = 20.sp)
            Spacer(Modifier.height(10.dp))
            Text("Here you can show app details, version info, and developer credits.")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutorialScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tutorial", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text("Tutorial Steps", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(8.dp))
            Text("1. Open the app\n2. Navigate through dashboard\n3. Use menu options\n4. Enjoy the features")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text("Settings", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(8.dp))
            Text("• Notification Preferences\n• Theme Selection\n• Account Settings\n(Just dummy data for now)")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedbackScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Feedback", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text("We value your feedback!", fontSize = 20.sp)
            Spacer(Modifier.height(12.dp))
            Text("Tell us what you think about the app.\n(Dummy feedback form placeholder)")
        }
    }
}



@Composable
fun TaskGrid(modifier: Modifier = Modifier, navController: NavHostController) {
    val tasks = listOf(
        "Good & Bad Touch" to R.drawable.rb_5272,
        "Manners" to R.drawable.rb_5272,
        "Emotions" to R.drawable.rb_5272,
        "Task 4" to R.drawable.rb_5272,
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
                fontSize = 32.sp,
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
                        if (taskName == "Good & Bad Touch") {
                            navController.navigate("Task1SelectionScreen")
                        }
                        if (taskName == "Manners") {
                            navController.navigate("Task2SelectionScreen")
                        }
                        if (taskName == "Emotions") {
                            navController.navigate("Task3SelectionScreen")
                        }
                        if (taskName == "Task 4") {
                            navController.navigate("task4selection1")
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

                Text(taskName, fontWeight = FontWeight.SemiBold, color = Color(0xFF37474F),fontSize = 26.sp)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedbackScreen(
    onSubmit: (Float, Float, Float, Float, String) -> Unit
) {
    var easyToUse by remember { mutableStateOf(0f) }
    var clearPictures by remember { mutableStateOf(0f) }
    var clearVoice by remember { mutableStateOf(0f) }
    var easyToNavigate by remember { mutableStateOf(0f) }
    var comments by remember { mutableStateOf("") }
    val themeColor = Color(0xFFF9F9F9) // soft white

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "🌟 Feedback Time!",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black // contrast with light bar
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = themeColor)
            )
        },
        bottomBar = {
            Button(
                onClick = {
                    onSubmit(easyToUse, clearPictures, clearVoice, easyToNavigate, comments)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(64.dp),
                colors = ButtonDefaults.buttonColors(containerColor = themeColor),
                shape = RoundedCornerShape(50)
            ) {
                Text(
                    "SUBMIT",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black // readable on light background
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFFFFF59D), Color(0xFFBBDEFB))
                    )
                )
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            RatingCard("🎮 Easy to Use", easyToUse) { easyToUse = it }
            RatingCard("🖼 Clear Pictures", clearPictures) { clearPictures = it }
            RatingCard("🎤 Clear Voice", clearVoice) { clearVoice = it }
            RatingCard("🧭 Easy to Navigate", easyToNavigate) { easyToNavigate = it }

            Text(
                text = "💡 Comments, Suggestions or Fun Ideas",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                color = Color(0xFF4E342E)
            )

            OutlinedTextField(
                value = comments,
                onValueChange = { comments = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                placeholder = { Text("Write here... ✏️") },
                shape = RoundedCornerShape(20.dp)
            )
        }
    }
}

@Composable
fun RatingCard(title: String, rating: Float, onRatingChange: (Float) -> Unit) {
    val coroutineScope = rememberCoroutineScope()

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(title, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF3E2723))
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(5) { index ->
                    val selected = index < rating
                    val scale = remember { Animatable(1f) }

                    IconButton(
                        onClick = {
                            onRatingChange(index + 1f)
                            coroutineScope.launch {
                                scale.animateTo(
                                    targetValue = 1.3f,
                                    animationSpec = tween(150, easing = FastOutSlowInEasing)
                                )
                                scale.animateTo(1f, animationSpec = tween(150))
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (selected) Icons.Default.Star else Icons.Default.StarBorder,
                            contentDescription = null,
                            tint = if (selected) Color(0xFFFFC107) else Color.Gray,
                            modifier = Modifier
                                .size(50.dp)
                                .scale(scale.value)
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true, device = "spec:width=1280dp,height=800dp,dpi=240")
@Composable
fun FeedbackScreenPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            FeedbackScreen { _, _, _, _, _ -> }
        }
    }
}
