package com.example.neuronest.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.neuronest.DashboardScreen
import com.example.neuronest.GoodandBadtouch.MatchThePairQuizScreen
import com.example.neuronest.GoodandBadtouch.PresentationScreen
import com.example.neuronest.GoodandBadtouch.QuizScreen
import com.example.neuronest.GoodandBadtouch.QuizSelectionScreen
import com.example.neuronest.GoodandBadtouch.SplashScreen
import com.example.neuronest.GoodandBadtouch.Task1SelectionScreen
import com.example.neuronest.Task2.ClassPresentationScreen
import com.example.neuronest.Task2.ClassSelectionScreen
import com.example.neuronest.Task2.SocialPresentationScreen
import com.example.neuronest.Task2.SocialSelectionScreen
import com.example.neuronest.Task2.Task2SelectionScreen


@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController=navController,startDestination = "DashBoard"){
        composable("DashBoard"){
            DashboardScreen(navController=navController)
        }
        composable("DragandDropQuiz"){
            QuizScreen(navController=navController)
        }
        composable("MatchQuiz"){
            MatchThePairQuizScreen(navController=navController)
        }
        composable("QuizSelectionScreen"){
            QuizSelectionScreen(navController=navController)
        }
        composable("Task1SelectionScreen"){
            Task1SelectionScreen(navController=navController)
        }
        composable("Task2SelectionScreen"){
            Task2SelectionScreen(navController=navController)
        }
        composable("ClassSelectionScreen"){
            ClassSelectionScreen(navController=navController)
        }
        composable("SocialSelectionScreen"){
            SocialSelectionScreen(navController=navController)
        }
        composable("Splashscreen"){
            SplashScreen(navController=navController)
        }
        composable("presentationscreen"){
            PresentationScreen(navController = navController)
        }
        composable("classpresentationscreen"){
            ClassPresentationScreen(navController = navController)
        }
        composable("socialpresentationscreen"){
            SocialPresentationScreen(navController = navController)
        }

    }
}