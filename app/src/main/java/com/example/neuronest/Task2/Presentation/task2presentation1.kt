package com.example.neuronest.Task2.Presentation

import PresentationScreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.neuronest.R


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

@Composable
fun ClassPresentationScreen(navController: NavHostController){
    PresentationScreen(
        navController = navController,
        title = "Neuronest",
        themeColor = Color(0xFF704214),
        images = images,
        audios = audios,
    )
}
