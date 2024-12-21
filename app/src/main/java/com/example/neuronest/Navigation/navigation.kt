package com.example.neuronest.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.neuronest.DashboardScreen
import com.example.neuronest.GoodandBadtouch.QuizScreen


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
    }
}