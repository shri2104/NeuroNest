package com.example.neuronest.Task2.selection

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.neuronest.R

@Composable
fun socialquiz(navController: NavHostController) {
    socialSelectionScreen2(
        navController = navController,
        backgroundImage = R.drawable.social4,
        titleText = "Social Manners",
        onFirstButtonClick = { navController.navigate("socialdraganddrop") },
        onSecondButtonClick = { navController.navigate("SocialSelectionQuiz") } ,
        onThirdButtonClick =  { navController.navigate("YesNosocial") }
    )
}