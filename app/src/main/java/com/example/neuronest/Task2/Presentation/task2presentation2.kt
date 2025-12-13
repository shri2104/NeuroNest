package com.example.neuronest.Task2.Presentation

import PresentationScreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.neuronest.R


val images1 = listOf(
    R.drawable.t2m1,
    R.drawable.t2m2,
    R.drawable.t2m3,
    R.drawable.t2m4,
    R.drawable.t2m5,
    R.drawable.t2m6,
    R.drawable.t2m7,
    R.drawable.t2m8,
    R.drawable.t2m9,
    R.drawable.s10,
    R.drawable.t2m11,
    R.drawable.t2m12,
    R.drawable.t2m13,
    R.drawable.t2m14,
    R.drawable.t2m15,
    R.drawable.t2m16,
    R.drawable.t2m17,
    R.drawable.t2m18,
    R.drawable.t2m19
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
        title = "Social Manners",
        themeColor = Color(0xFF704214),
        images = images1,
        audios = audios1,
    )
}
