package com.example.neuronest.Task2.Presentation

import PresentationScreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.neuronest.R


val images1 = listOf(
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

val audios1 = listOf(
    R.raw.s1, R.raw.s2, R.raw.s3, R.raw.s4, R.raw.s5, R.raw.s6,
    R.raw.s7, R.raw.s8, R.raw.s9, R.raw.s10, R.raw.s11, R.raw.s12,
    R.raw.s13, R.raw.s14, R.raw.s15, R.raw.s16, R.raw.s17, R.raw.s18, R.raw.s19
)

@Composable
fun SocialPresentationScreen(navController: NavHostController){
    PresentationScreen(
        navController = navController,
        title = "Neuronest",
        themeColor = Color(0xFF704214),
        images = images1,
        audios = audios1,
    )
}
