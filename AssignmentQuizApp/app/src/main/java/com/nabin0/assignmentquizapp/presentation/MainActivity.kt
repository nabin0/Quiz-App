package com.nabin0.assignmentquizapp.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.findNavController
import com.nabin0.assignmentquizapp.data.Question
import com.nabin0.assignmentquizapp.presentation.components.MainScreen
import com.nabin0.assignmentquizapp.presentation.components.QuestionView
import com.nabin0.assignmentquizapp.presentation.components.ResultScreen
import com.nabin0.assignmentquizapp.presentation.components.SplashScreen
import com.nabin0.assignmentquizapp.presentation.util.Screen
import com.nabin0.assignmentquizapp.ui.theme.AssignmentQuizAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AssignmentQuizAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    val viewModel: MainActivityViewModel = hiltViewModel()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.SplashScreen.route
                    ) {

                        //Splash Screen
                        composable(route = Screen.SplashScreen.route) {
                            SplashScreen(navController = navController)
                        }

                        //Home Screen
                        composable(route = Screen.HomeScreen.route) {
                            MainScreen(navController = navController, viewModel = viewModel)
                        }

                        //Result Screen
                        composable(route = Screen.ResultScreen.route) {
                            ResultScreen(navController = navController, viewModel = viewModel)
                        }
                    }
                }
            }
        }
    }
}
