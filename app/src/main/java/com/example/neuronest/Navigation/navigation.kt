package com.example.neuronest.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.neuronest.DashboardScreen
import com.example.neuronest.GoodandBadtouch.QuizScreen
import com.example.neuronest.GoodandBadtouch.SelectionScreen
import com.example.neuronest.GoodandBadtouch.SplashScreen


@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController=navController,startDestination = "DashBoard"){
        composable("DashBoard"){
            DashboardScreen(navController=navController)
        }
        composable("GoodandBadtouchQuiz"){
            QuizScreen(navController=navController)
        }
        composable("SelectionScreen"){
            SelectionScreen(navController=navController)
        }
        composable("Splashscreen"){
            SplashScreen(navController=navController)
        }

    }
}