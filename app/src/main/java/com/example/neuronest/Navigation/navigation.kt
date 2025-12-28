package com.example.neuronest.Navigation

import LoginScreen
import SignUpScreen
import SplashScreen
import VideoPlayerScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.neuronest.AboutScreen
import com.example.neuronest.DashboardScreen
import com.example.neuronest.FeedbackScreen
import com.example.neuronest.FeedbackScreenPreview
import com.example.neuronest.GoodandBadtouch.QuizScreen
import com.example.neuronest.R
import com.example.neuronest.SettingsScreen
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
import com.example.neuronest.Task3.task3Quiz.McqQuizScreen
import com.example.neuronest.Task3.task3Quiz.Task3DrapaandDrop
import com.example.neuronest.Task3.task3Quiz.Task3SelectionQuiz
import com.example.neuronest.Task3.task3Quiz.task3QuizSelectionScreen
import com.example.neuronest.Task3.task3selection.task3Selection
import com.example.neuronest.Task3.task3selection.task3selection
import com.example.neuronest.Task4.Quiz.task4QuizSelectionScreen
import com.example.neuronest.Task4.Selection.Task4ActivityType
import com.example.neuronest.Task4.Selection.task4BrainFunList
import com.example.neuronest.Task4.Selection.task4HappyLearningList
import com.example.neuronest.Task4.Selection.task4Selection
import com.example.neuronest.Task4.task4quiz.Task4DragDrop
import com.example.neuronest.Task4.task4quiz.Task4McqScreen
import com.example.neuronest.Task4.task4quiz.Task4YesNoScreen
import com.example.neuronest.TutorialScreen
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
        composable("Task3QuizSelectionScreen") {
            task3QuizSelectionScreen(navController = navController)
        }
        composable("Task3DrapaandDrop") {
            Task3DrapaandDrop(navController = navController)
        }
        composable("Task3SelectionQuiz") {
            Task3SelectionQuiz(navController = navController)
        }
        composable("task3mcqScreen") {
            McqQuizScreen(navController)
        }
        composable("task4selection1") {
            task4Selection(navController)
        }
        composable("HappyLearningList") {
            task4HappyLearningList(navController)
        }

        composable("BrainFunList") {
            task4BrainFunList(navController)
        }

        composable("Task4QuizSelection/{activity}") { backStackEntry ->
            val activity = Task4ActivityType.valueOf(
                backStackEntry.arguments!!.getString("activity")!!
            )

            task4QuizSelectionScreen(
                navController = navController,
                activity = activity
            )
        }

        composable("Task4DragDrop/{activity}") { backStackEntry ->
            val activity = Task4ActivityType.valueOf(
                backStackEntry.arguments!!.getString("activity")!!
            )

            Task4DragDrop(navController, activity)
        }

        composable("Task4Mcq/{activity}") { backStackEntry ->
            val activity = Task4ActivityType.valueOf(
                backStackEntry.arguments?.getString("activity")!!
            )

            Task4McqScreen(navController, activity)
        }


        composable("Task4YesNo/{activity}") { backStackEntry ->
            val activity = Task4ActivityType.valueOf(
                backStackEntry.arguments!!.getString("activity")!!
            )

            Task4YesNoScreen(navController, activity)
        }



        composable("task4presentation") {
            VideoPlayerScreen(videoResId = R.raw.my_video)
        }
        composable("dashboard") { DashboardScreen(navController) }
        composable("about") { AboutScreen(navController) }
        composable("tutorial") { TutorialScreen(navController) }
        composable("settings") { SettingsScreen(navController) }
        composable("feedback") { FeedbackScreenPreview() }

    }
}