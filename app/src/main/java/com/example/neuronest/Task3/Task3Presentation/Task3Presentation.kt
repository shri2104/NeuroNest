package com.example.neuronest.Task3.Task3Presentation

import PresentationScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.neuronest.R

val images3 = listOf(
    R.drawable.t3p1, R.drawable.t3p3, R.drawable.t3p5, R.drawable.t3p7,
    R.drawable.t3p9, R.drawable.t3p11, R.drawable.t3p13, R.drawable.t3p15,
    R.drawable.t3p17, R.drawable.t3p19,R.drawable.t3p2,R.drawable.t3p4,R.drawable.t3p6,
    R.drawable.t3p8,R.drawable.t3p10,R.drawable.t3p12,R.drawable.t3p14,R.drawable.t3p16,
    R.drawable.t3p18,R.drawable.t3p20,
)

val audios3 = listOf(
    R.raw.a1, R.raw.a3, R.raw.a8, R.raw.a7, R.raw.a9, R.raw.a4,
    R.raw.a5, R.raw.a6, R.raw.a10, R.raw.a2, R.raw.a1, R.raw.a3, R.raw.a8, R.raw.a7, R.raw.a9, R.raw.a4,
    R.raw.a5, R.raw.a6, R.raw.a10, R.raw.a2
)

@Composable
fun task3presentation(navController: NavHostController){
    PresentationScreen(
        navController = navController,
        title = "Emotions",
        themeColor = Color(0xFFFF80A6),
        images = images3,
        audios = audios3,
    )
}
