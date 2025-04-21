package com.example.neuronest.Task2.Task2Quiz

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.neuronest.R
import com.example.neuronest.Task2.classSelectionScreen
import com.example.neuronest.Task2.socialSelectionScreen
import com.example.neuronest.Task2.socialSelectionScreen2

@Composable
fun socialquiz(navController: NavHostController) {
    socialSelectionScreen2(
        navController = navController,
        backgroundImage = R.drawable.social4,
        titleText = "Social Manners",
        onFirstButtonClick = { navController.navigate("selctionforsocial") },
        onSecondButtonClick = { navController.navigate("ynforsocial") }
    )
}