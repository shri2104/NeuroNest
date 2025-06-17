package com.example.neuronest.Task2

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.neuronest.R
import android.media.MediaPlayer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClassPresentationScreen(navController: NavHostController) {
    val context = LocalContext.current

    val images = listOf(
        R.drawable.c1, R.drawable.c2, R.drawable.c3, R.drawable.c4,
        R.drawable.c5, R.drawable.c6, R.drawable.c7, R.drawable.c8,
        R.drawable.c9, R.drawable.c10, R.drawable.c11, R.drawable.c12,
        R.drawable.c13, R.drawable.c14, R.drawable.c15, R.drawable.c16,
        R.drawable.c17, R.drawable.c18, R.drawable.c19
    )

    val audios = listOf(
        R.raw.c1, R.raw.c2, R.raw.c3, R.raw.c4,
        R.raw.c5, R.raw.c6, R.raw.c7, R.raw.c8,
        R.raw.c9, R.raw.c10, R.raw.c11, R.raw.c12,
        R.raw.c13, R.raw.c14, R.raw.c15, R.raw.c16,
        R.raw.c17, R.raw.c18, R.raw.c19
    )

    var currentIndex by remember { mutableStateOf(0) }
    var mediaPlayer: MediaPlayer? by remember { mutableStateOf(null) }

    // Handle audio playback when index changes
    LaunchedEffect(currentIndex) {
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer.create(context, audios[currentIndex])
        mediaPlayer?.start()
    }

    // Clean up the media player when composable leaves the composition
    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "NeuroNest", fontSize = 35.sp) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("ClassSelectionScreen") }) {
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
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter = painterResource(id = images[currentIndex]),
                contentDescription = "Presentation Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(900.dp)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(BorderStroke(2.dp, MaterialTheme.colorScheme.primary))
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { if (currentIndex > 0) currentIndex-- },
                    enabled = currentIndex > 0
                ) {
                    Text("Previous", fontSize = 40.sp)
                }
                Button(
                    onClick = { if (currentIndex < images.size - 1) currentIndex++ },
                    enabled = currentIndex < images.size - 1
                ) {
                    Text("Next" , fontSize = 40.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
