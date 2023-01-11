package com.nabin0.assignmentquizapp.presentation.util

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object HomeScreen : Screen("home_screen")
    object ResultScreen : Screen("result_screen")
}
