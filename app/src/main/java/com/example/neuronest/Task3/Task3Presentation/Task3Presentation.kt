package com.example.neuronest.Task3.Task3Presentation

import PresentationScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.neuronest.R

val images3 = listOf(
    R.drawable.task3p1, R.drawable.task3p2, R.drawable.task3p3, R.drawable.task3p4,
    R.drawable.task3p5, R.drawable.task3p6, R.drawable.task3p7, R.drawable.task3p8,
    R.drawable.task3p9, R.drawable.task3p10,
)

val audios3 = listOf(
    R.raw.s1, R.raw.s2, R.raw.s3, R.raw.s4, R.raw.s5, R.raw.s6,
    R.raw.s7, R.raw.s8, R.raw.s9, R.raw.s10, R.raw.s11, R.raw.s12,
    R.raw.s13, R.raw.s14, R.raw.s15, R.raw.s16, R.raw.s17, R.raw.s18, R.raw.s19
)

@Composable
fun task3presentation(navController: NavHostController){
    PresentationScreen(
        navController = navController,
        title = "Neuronest",
        themeColor = Color(0xFFFF80A6),
        images = images3,
        audios = audios3,
    )
}
