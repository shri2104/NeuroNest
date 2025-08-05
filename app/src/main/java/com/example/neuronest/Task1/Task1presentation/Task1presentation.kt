package com.example.neuronest.Task1.Task1presentation


import PresentationScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.neuronest.R

val images2 = listOf(
    R.drawable.task1p1, R.drawable.task1p2, R.drawable.task1p3, R.drawable.task1p4,
    R.drawable.task1p5, R.drawable.task1p6, R.drawable.task1p7, R.drawable.task1p8,
    R.drawable.task1p9, R.drawable.task1p10, R.drawable.task1p11, R.drawable.task1p12,
    R.drawable.t1p13, R.drawable.t1p14, R.drawable.t1p15, R.drawable.t1p16,
    R.drawable.t1p17, R.drawable.t1p18,R.drawable.t1p19,R.drawable.t1p20,
)

val audios2 = listOf(
    R.raw.c1, R.raw.c2, R.raw.c3, R.raw.c4,
    R.raw.c5, R.raw.c6, R.raw.c7, R.raw.c8,
    R.raw.c9, R.raw.c10, R.raw.c11, R.raw.c12,
    R.raw.c9, R.raw.c10, R.raw.c11, R.raw.c12,
    R.raw.c9, R.raw.c10, R.raw.c11, R.raw.c12,
    )

@Composable
fun Task1presentation(navController: NavHostController){
    PresentationScreen(
        navController = navController,
        title = "Good and Bad Touch",
        themeColor = Color(0xFF2196F3),
        images = images2,
        audios = audios2,
    )
}

