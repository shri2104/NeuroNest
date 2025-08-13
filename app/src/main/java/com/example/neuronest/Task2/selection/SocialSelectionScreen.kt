package com.example.neuronest.Task2.selection

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.neuronest.R

@Composable
fun SocialSelectionScreen(navController: NavHostController) {
    socialSelectionScreen(
        navController = navController,
        backgroundImage = R.drawable.socialbg,
        titleText = "Social Manners",
        onFirstButtonClick = { navController.navigate("socialpresentationscreen") },
        onSecondButtonClick = { navController.navigate("socialquizselection") }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun socialSelectionScreen(
    navController: NavHostController,
    backgroundImage: Int,
    titleText: String,
    onFirstButtonClick: () -> Unit,
    onSecondButtonClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Social Manners",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.White,
                        fontSize = 35.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF52360C),
                    titleContentColor = Color.Black

                ),
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("Task2SelectionScreen") }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
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
                contentScale = ContentScale.Fit
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


//                Divider(
//                    color = Color(0xFF3F51B5),
//                    thickness = 2.dp,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(vertical = 16.dp)
//                )

                Spacer(modifier = Modifier.height(90.dp))
                Button(
                    onClick = onFirstButtonClick,
                    modifier = Modifier
                        .fillMaxWidth().size(100.dp)
                        .padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xC38D5F1A))
                ) {
                    Text(text = "Happy Learning!", fontSize = 40.sp, fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = onSecondButtonClick,
                    modifier = Modifier
                        .fillMaxWidth().size(100.dp)
                        .padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xC38D5F1A))
                ) {
                    Text(text = "Brain Fun!", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun socialSelectionScreen2(
    navController: NavHostController,
    backgroundImage: Int,
    titleText: String,
    onFirstButtonClick: () -> Unit,
    onSecondButtonClick: () -> Unit,
    onThirdButtonClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Social Manners",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.White,
                        fontSize = 32.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF52360C),
                    titleContentColor = Color.Black

                ),
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("SocialSelectionScreen") }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
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
                contentScale = ContentScale.Fit
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


//                Divider(
//                    color = Color(0xFF3F51B5),
//                    thickness = 2.dp,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(vertical = 16.dp)
//                )

                Spacer(modifier = Modifier.height(120.dp))
                Button(
                    onClick = onFirstButtonClick,
                    modifier = Modifier
                        .fillMaxWidth().size(100.dp)
                        .padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xC38D5F1A))
                ) {
                    Text(text = "Drag & Drop", fontSize = 40.sp, fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = onSecondButtonClick,
                    modifier = Modifier
                        .fillMaxWidth().size(100.dp)
                        .padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xC38D5F1A))
                ) {
                    Text(text = "Selection", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }
                Button(
                    onClick = onThirdButtonClick,
                    modifier = Modifier
                        .fillMaxWidth().size(100.dp)
                        .padding(vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(Color(0xC38D5F1A))
                ) {
                    Text(text = "Yes/No", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }
            }
        }
    }
}