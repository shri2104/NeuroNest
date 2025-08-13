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
   R.drawable.t1p18,R.drawable.t1p19,R.drawable.t1p20,
)

val audios2 = listOf(
    R.raw.gb1, R.raw.c2, R.raw.gb3, R.raw.gb4,
    R.raw.gb5, R.raw.gb6, R.raw.gb16, R.raw.gb11,
    R.raw.gb14, R.raw.gb12, R.raw.gb13, R.raw.gb15,
    R.raw.gb8, R.raw.gb10, R.raw.gb17, R.raw.gb18,
    R.raw.gb8, R.raw.gb20, R.raw.gb19, R.raw.c12,
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

