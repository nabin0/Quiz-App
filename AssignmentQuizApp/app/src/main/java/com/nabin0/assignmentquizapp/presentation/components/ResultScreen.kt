package com.nabin0.assignmentquizapp.presentation.components

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nabin0.assignmentquizapp.presentation.MainActivityViewModel
import com.nabin0.assignmentquizapp.presentation.util.Screen

@Composable
fun ResultScreen(
    navController: NavController,
    viewModel: MainActivityViewModel = hiltViewModel()
) {
    val correctAnswers = viewModel.correctAnswerCount
    val wrongAnswers = viewModel.wrongAnswersCount


    Log.d("MyTag", "runig $correctAnswers   $wrongAnswers")

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Result",
            modifier = Modifier
                .fillMaxWidth()
                .align(CenterHorizontally),
            style = MaterialTheme.typography.h1
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Total Questions: ${viewModel.totalQuestions.value}",
            modifier = Modifier
                .fillMaxWidth()
                .align(CenterHorizontally),
            style = MaterialTheme.typography.h5
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Correct Answers: $correctAnswers",
            modifier = Modifier
                .fillMaxWidth()
                .align(CenterHorizontally),
            style = MaterialTheme.typography.h5
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Wrong Answers: $wrongAnswers",
            modifier = Modifier
                .fillMaxWidth()
                .align(CenterHorizontally),
            style = MaterialTheme.typography.h5
        )
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            viewModel.resetAllData()
            navController.navigate(Screen.HomeScreen.route){
                popUpTo(Screen.ResultScreen.route){
                    inclusive = true
                }
            }
        },
        modifier = Modifier.fillMaxWidth()) {
            Text(text = "Play Again")
        }
    }
}