package com.example.neuronest.Task2.Presentation

import PresentationScreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.neuronest.R


val images = listOf(
    R.drawable.t2n1, R.drawable.t2n2, R.drawable.t2n3, R.drawable.t2n4,
    R.drawable.t2n5, R.drawable.t2n6, R.drawable.t2n7, R.drawable.t2n8,
    R.drawable.t2n9, R.drawable.t2n10, R.drawable.t2n11, R.drawable.t2n12,
    R.drawable.t2n13, R.drawable.t2n14, R.drawable.t2n15, R.drawable.t2n16,
    R.drawable.t2n17, R.drawable.t218, R.drawable.t2n19
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
        title = "Classroom Manners",
        themeColor = Color(0xFF704214),
        images = images,
        audios = audios,
    )
}
