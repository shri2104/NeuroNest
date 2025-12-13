package com.example.neuronest.Task1.Task1presentation


import PresentationScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.neuronest.R

val images2 = listOf(
    R.drawable.t1n1, R.drawable.t1n2, R.drawable.t1n3, R.drawable.t1n4,
    R.drawable.t1n5, R.drawable.t1n6, R.drawable.t1n7, R.drawable.t1n8,
    R.drawable.t1n9, R.drawable.t1n10, R.drawable.t1n11, R.drawable.t1n12,
    R.drawable.t1n13, R.drawable.t1n14, R.drawable.t1n15, R.drawable.t1n16,
   R.drawable.t1n17,R.drawable.t1n18,R.drawable.t1n19,
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

