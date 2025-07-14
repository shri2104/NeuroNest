package com.example.neuronest.Navigation

import LoginScreen
import SignUpScreen
import SplashScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.neuronest.DashboardScreen
import com.example.neuronest.GoodandBadtouch.QuizScreen
import com.example.neuronest.Task1.Task1presentation.Task1presentation
import com.example.neuronest.Task1.selection.Task1SelectionScreen1
import com.example.neuronest.Task1.selection.Task1SelectionScreen2
import com.example.neuronest.Task1.selection.Task1SelectionScreen3
import com.example.neuronest.Task1.task1quiz.Task1yeandno
import com.example.neuronest.Task1.task1quiz.task1draganddrop
import com.example.neuronest.Task1.task1quiz.task1selectionquiz1
import com.example.neuronest.Task1.task1quiz.task1selectionquiz2
import com.example.neuronest.Task2.Presentation.ClassPresentationScreen
import com.example.neuronest.Task2.selection.ClassSelectionScreen
import com.example.neuronest.Task2.Presentation.SocialPresentationScreen
import com.example.neuronest.Task2.selection.SocialSelectionScreen
import com.example.neuronest.Task2.selection.classroomquiz

import com.example.neuronest.Task2.selection.socialquiz
import com.example.neuronest.Task2.selection.Task2SelectionScreen
import com.example.neuronest.Task2.classroomquiz.ClassroomDrapaandDrop
import com.example.neuronest.Task2.classroomquiz.YesNoClass
import com.example.neuronest.Task2.classroomquiz.classroomselection
import com.example.neuronest.Task2.socialquiz.SocialDrapaandDrop
import com.example.neuronest.Task2.socialquiz.SocialSelectionQuiz
import com.example.neuronest.Task2.socialquiz.YesNosocial
import com.example.neuronest.Task3.Task3Presentation.task3presentation
import com.example.neuronest.Task3.task3selection.task3Selection
import com.example.neuronest.Task3.task3selection.task3selection
import com.example.neuronest.login.ProfileScreen
import com.example.neuronest.retrofit.ApiService


@Composable
fun Navigation(apiService: ApiService) {
    val navController = rememberNavController()
    NavHost(navController=navController,startDestination = "DashBoard"){

        composable("LoginScreen") {
            LoginScreen(
                onSignUp = { navController.navigate("SignUpScreen") },
               navController
            )
        }
        composable("SignUpScreen") {
            SignUpScreen(navController = navController)
        }
        composable("DashBoard"){
            DashboardScreen(navController=navController)
        }
        composable("profile"){
            ProfileScreen(navController=navController)
        }
        composable("DragandDropQuiz"){
            QuizScreen(navController=navController)
        }
        composable("Task1SelectionScreen"){
            Task1SelectionScreen1(navController=navController)
        }
        composable("Task1SelectionScreen2"){
            Task1SelectionScreen2(navController=navController)
        }
        composable("Task1SelectionScreen3"){
            Task1SelectionScreen3(navController=navController)
        }
        composable("Task1presentation"){
            Task1presentation(navController=navController)
        }
        composable("Task1draganddrop"){
            task1draganddrop(navController=navController)
        }
        composable("Task1YasandNo"){
            Task1yeandno(navController=navController)
        }
        composable("task1selectionquiz1"){
            task1selectionquiz1(navController=navController)
        }
        composable("task1selectionquiz2"){
            task1selectionquiz2(navController=navController)
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
        composable("socialquizselection"){
            socialquiz(navController=navController)
        }
        composable("classroomquizselection") {
            classroomquiz(navController = navController)
        }
        composable("classpresentationscreen"){
            ClassPresentationScreen(navController = navController)
        }
        composable("socialpresentationscreen"){
            SocialPresentationScreen(navController = navController)
        }
        composable("socialdraganddrop") {
            SocialDrapaandDrop(navController = navController)
        }
        composable("SocialSelectionQuiz") {
            SocialSelectionQuiz(navController = navController)
        }
        composable("YesNosocial") {
            YesNosocial(navController = navController)
        }
        composable("ClassroomDrapaandDrop") {
            ClassroomDrapaandDrop(navController = navController)
        }
        composable("classroomselection") {
            classroomselection(navController = navController)
        }
        composable("YesNoClass") {
            YesNoClass(navController = navController)
        }
        composable("Task3SelectionScreen") {
            task3Selection(navController = navController)
        }
        composable("Task3presentationScreen") {
            task3presentation(navController = navController)
        }

    }
}