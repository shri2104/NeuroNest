package com.example.neuronest.GoodandBadtouch

import SelectionScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.ui.res.painterResource
import com.example.neuronest.R

@Composable
fun Task1SelectionScreen(navController: NavHostController) {
    SelectionScreen(
        navController = navController,
        backgroundImage = R.drawable.dolphin,
        titleText = "Good and Bad Touch",
        onFirstButtonClick = { navController.navigate("presentationscreen") },
        onSecondButtonClick = { navController.navigate("QuizSelectionScreen") }
    )
}
