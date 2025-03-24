package com.example.neuronest.GoodandBadtouch

import LoginScreenViewModel
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.neuronest.R
import kotlinx.coroutines.delay



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
        Surface(
            modifier = Modifier
                .size(350.dp)
                .graphicsLayer(scaleX = scale.value, scaleY = scale.value),
            shape = CircleShape,
            color = Color.White,
            border = BorderStroke(3.dp, Color.LightGray)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Neuronest",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        color = Color(0xFF3F51B5),
                        fontWeight = FontWeight.Bold,
                        fontSize = 50.sp,
                        letterSpacing = 2.sp
                    )
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(navController = NavHostController(LocalContext.current))
}
