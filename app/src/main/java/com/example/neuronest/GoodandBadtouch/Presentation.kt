package com.example.neuronest.GoodandBadtouch

import android.media.MediaPlayer
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.neuronest.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PresentationScreen(navController: NavHostController) {
    val context = LocalContext.current
    val images = listOf(
        R.drawable.pre1,
        R.drawable.pre3,
        R.drawable.pre4,
        R.drawable.pre5,
        R.drawable.pre6,
        R.drawable.pre7,
        R.drawable.pre8,
        R.drawable.pre9,
        R.drawable.pre10,
        R.drawable.pre11,
        R.drawable.pre12,
        R.drawable.pre13,
        R.drawable.pre14,
        R.drawable.pre15,
        R.drawable.pre16,
        R.drawable.pre17,
        R.drawable.pre18,
        R.drawable.pre19,
        R.drawable.pre20
    )
    val audios = listOf(
        R.raw.gb1, R.raw.gb3 ,R.raw.gb4,
        R.raw.gb5, R.raw.gb6, R.raw.gb7, R.raw.gb8,
        R.raw.gb9, R.raw.gb10, R.raw.gb11, R.raw.gb12,
        R.raw.gb13, R.raw.gb14, R.raw.gb15, R.raw.gb16,
        R.raw.gb17, R.raw.gb18, R.raw.gb19 , R.raw.gb20
    )

    var currentIndex by remember { mutableStateOf(0) }
    var mediaPlayer : MediaPlayer? by remember {mutableStateOf(null)}

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
                title = { Text(text = "Good & Bad Touch") },
                navigationIcon = {
                    IconButton(onClick =  { navController.navigate("SelectionScreen") }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
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
                    Button(
                        onClick = { if (currentIndex > 0) currentIndex-- },
                        enabled = currentIndex > 0
                    ) {
                        Text("Previous")
                    }
                    Text("${currentIndex + 1} / ${images.size}", color = Color.White)
                    Button(
                        onClick = { if (currentIndex < images.size - 1) currentIndex++ },
                        enabled = currentIndex < images.size - 1
                    ) {
                        Text("Next")
                    }
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
                    .height(500.dp)
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
                    Text("Previous")
                }
                Button(
                    onClick = { if (currentIndex < images.size - 1) currentIndex++ },
                    enabled = currentIndex < images.size - 1
                ) {
                    Text("Next")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PresentationScreen(navController = NavHostController(LocalContext.current))
}