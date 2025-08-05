package com.example.neuronest.Task2.selection

import androidx.compose.runtime.Composable


import androidx.navigation.NavHostController
import com.example.neuronest.R

@Composable
fun classroomquiz(navController: NavHostController) {
    classSelectionScreen2(
        navController = navController,
        backgroundImage = R.drawable.classbg,
        titleText = "",
        onFirstButtonClick = { navController.navigate("ClassroomDrapaandDrop") },
        onSecondButtonClick = { navController.navigate("classroomselection") },
        onThirdButtonClick = { navController.navigate("YesNoClass") },
        firstButtonText = "Drag and Drop",
        secondButtonText = "Selection",
        thirdButtonText = "Yes and No"
    )
}