package com.example.neuronest.Task2.Presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.neuronest.R
import android.media.MediaPlayer
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SocialPresentationScreen(navController: NavHostController) {
    val context = LocalContext.current
    val images = listOf(
        R.drawable.s1,
        R.drawable.s2,
        R.drawable.s3,
        R.drawable.s4,
        R.drawable.s5,
        R.drawable.s6,
        R.drawable.s7,
        R.drawable.s8,
        R.drawable.s9,
        R.drawable.s10,
        R.drawable.s11,
        R.drawable.s12,
        R.drawable.s13,
        R.drawable.s14,
        R.drawable.s15,
        R.drawable.s16,
        R.drawable.s17,
        R.drawable.s18,
        R.drawable.s19
    )

    val audios = listOf(
        R.raw.s1, R.raw.s2, R.raw.s3, R.raw.s4, R.raw.s5, R.raw.s6,
        R.raw.s7, R.raw.s8, R.raw.s9, R.raw.s10, R.raw.s11, R.raw.s12,
        R.raw.s13, R.raw.s14, R.raw.s15, R.raw.s16, R.raw.s17, R.raw.s18, R.raw.s19
    )

    var currentIndex by remember { mutableStateOf(0) }
    var mediaPlayer: MediaPlayer? by remember { mutableStateOf(null) }

    DisposableEffect(currentIndex) {
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer.create(context, audios[currentIndex])
        mediaPlayer?.start()

        onDispose {
            mediaPlayer?.release()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "NeuroNest" , fontSize = 35.sp) },
                navigationIcon = {
                    IconButton(onClick =  { navController.navigate("Task2SelectionScreen") }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF52360C),
                    titleContentColor = Color.White
            ))
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White,
                modifier = Modifier.height(64.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text("${currentIndex + 1} / ${images.size}", color = Color.White , fontSize = 25.sp)

                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp), // Add horizontal padding for better alignment
            verticalArrangement = Arrangement.Center, // Center content vertically
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp)) // Add top spacing

            Image(
                painter = painterResource(id = images[currentIndex]),
                contentDescription = "Presentation Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(900.dp)
                    .padding(16.dp) // Add padding around the image
                    .clip(RoundedCornerShape(12.dp))
                    .border(BorderStroke(2.dp, MaterialTheme.colorScheme.primary))
            )

            Spacer(modifier = Modifier.height(32.dp)) // Increase spacing between image and buttons

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { if (currentIndex > 0) currentIndex-- },
                    enabled = currentIndex > 0
                ) {
                    Text("Previous" , fontSize =40.sp)
                }
                Button(
                    onClick = { if (currentIndex < images.size - 1) currentIndex++ },
                    enabled = currentIndex < images.size - 1
                ) {
                    Text("Next" ,fontSize =40.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


