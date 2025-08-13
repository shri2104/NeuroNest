import android.media.MediaPlayer
import android.net.Uri
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.neuronest.R
import kotlinx.coroutines.delay

@Composable
fun lastquizScreen(
    navController: NavController,
    destinationRoute: String // new parameter
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF1F1F1)),
        color = Color(0xFFF1F1F1)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Quiz is Finished!",
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3F51B5)
            )

            Spacer(modifier = Modifier.height(48.dp))

            Button(
                onClick = {
                    navController.navigate(destinationRoute)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3F51B5))
            ) {
                Text(text = "Go Back", fontSize = 20.sp, color = Color.White)
            }
        }
    }
}

@Composable
fun SplashScreen(navController: NavHostController, viewModel: LoginScreenViewModel = viewModel()) {
    val scale = remember { Animatable(0f) }
    val isUserLoggedIn by remember { mutableStateOf(viewModel.isUserLoggedIn()) } // Check login status

    LaunchedEffect(Unit) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                }
            )
        )
        delay(2000L)

        if (isUserLoggedIn) {
            navController.navigate("DashBoard") {
                popUpTo("Splashscreen") { inclusive = true } // Clears splash from backstack
            }
        } else {
            navController.navigate("LoginScreen") {
                popUpTo("Splashscreen") { inclusive = true } // Clears splash from backstack
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ub_logo_new_1_photoaidcom_cropped), // Replace with your actual image resource
            contentDescription = "App Logo",
            modifier = Modifier
                .size(150.dp) // Adjust size as needed
                .graphicsLayer(scaleX = scale.value, scaleY = scale.value)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PresentationScreen(
    navController: NavHostController,
    title: String = "Presentation",
    themeColor: Color = Color(0xFF52360C),
    images: List<Int>,
    audios: List<Int>,
) {
    val context = LocalContext.current

    var currentIndex by remember { mutableStateOf(0) }
    var mediaPlayer: MediaPlayer? by remember { mutableStateOf(null) }

    LaunchedEffect(currentIndex) {
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer.create(context, audios[currentIndex])
        mediaPlayer?.start()
    }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = title, fontSize = 35.sp) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = themeColor,
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = themeColor,
                contentColor = Color.White,
                modifier = Modifier.height(64.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("${currentIndex + 1} / ${images.size}", color = Color.White, fontSize = 25.sp)
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
                    .border(BorderStroke(2.dp, themeColor))
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { if (currentIndex > 0) currentIndex-- },
                    enabled = currentIndex > 0,
                    colors = ButtonDefaults.buttonColors(containerColor = themeColor)
                ) {
                    Text("Previous", fontSize = 40.sp)
                }
                Button(
                    onClick = { if (currentIndex < images.size - 1) currentIndex++ },
                    enabled = currentIndex < images.size - 1,
                    colors = ButtonDefaults.buttonColors(containerColor = themeColor)
                ) {
                    Text("Next", fontSize = 40.sp)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoPlayerScreen(
    videoResId: Int,
    themeColor: Color = Color(0xFF81C784) // light green
) {
    val context = LocalContext.current
    val player = remember {
        ExoPlayer.Builder(context).build().apply {
            val videoUri = Uri.parse("android.resource://${context.packageName}/$videoResId")
            setMediaItem(MediaItem.fromUri(videoUri))
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(C.AUDIO_CONTENT_TYPE_MOVIE)
                    .setUsage(C.USAGE_MEDIA)
                    .build(),
                true
            )
            prepare()
        }
    }

    val isPlaying = remember { mutableStateOf(false) }

    DisposableEffect(Unit) {
        onDispose { player.release() }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Video Lesson",
                        fontSize = 28.sp,
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = themeColor)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFFDF6FD)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Video View
            AndroidView(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) // take available space
                    .padding(16.dp),
                factory = {
                    PlayerView(it).apply {
                        useController = false
                        this.player = player
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (player.isPlaying) {
                        player.pause()
                        isPlaying.value = false
                    } else {
                        player.play()
                        isPlaying.value = true
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = themeColor),
                shape = RoundedCornerShape(30.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp),
                modifier = Modifier
                    .padding(16.dp)
                    .height(70.dp)
                    .width(240.dp)
            ) {
                Text(
                    text = if (isPlaying.value) "⏸ Pause" else "▶ Play",
                    fontSize = 26.sp,
                    color = Color.White
                )
            }

        }
    }
}




@Preview(showBackground = true)
@Composable
fun VideoPlayerScreenPreview() {
    VideoPlayerScreen(videoResId = R.raw.my_video)
}
