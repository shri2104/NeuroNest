package com.example.neuronest.Task3.Task3Presentation

import PresentationScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.neuronest.R

val images3 = listOf(
    R.drawable.t3p1, R.drawable.t3p2, R.drawable.t3p3, R.drawable.t3p4,
    R.drawable.t3p5, R.drawable.t3p6, R.drawable.t3p7, R.drawable.t3p8,
    R.drawable.t3p9, R.drawable.t3p10,R.drawable.t3p11,R.drawable.t3p12,R.drawable.t3p13,
    R.drawable.t3p14,R.drawable.t3p15,R.drawable.t3p16,R.drawable.t3p17,R.drawable.t3p18,
    R.drawable.t3p19,R.drawable.t3p20,
)

val audios3 = listOf(
    R.raw.s1, R.raw.s2, R.raw.s3, R.raw.s4, R.raw.s5, R.raw.s6,
    R.raw.s7, R.raw.s8, R.raw.s9, R.raw.s10, R.raw.s11, R.raw.s12,
    R.raw.s13, R.raw.s14, R.raw.s15, R.raw.s16, R.raw.s17, R.raw.s18, R.raw.s19, R.raw.s19
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
