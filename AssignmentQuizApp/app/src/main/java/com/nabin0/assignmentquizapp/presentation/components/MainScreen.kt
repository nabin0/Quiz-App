package com.nabin0.assignmentquizapp.presentation.components

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nabin0.assignmentquizapp.data.Question
import com.nabin0.assignmentquizapp.presentation.MainActivityViewModel

@Composable
fun MainScreen(
    viewModel: MainActivityViewModel = hiltViewModel(),
    navController: NavController
) {

    val currentQuestionIndex = viewModel.currentQuestionIndex.value
    val currentQuestion: Question = viewModel.allQuestions.value[currentQuestionIndex]
    val totalQuestions = viewModel.totalQuestions.value
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            CustomProgressBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                currentProgressValue = currentQuestionIndex,
                totalProgressValue = totalQuestions
            )
            Spacer(modifier = Modifier.height(20.dp))
            QuestionView(
                question = currentQuestion,
                modifier = Modifier.padding(16.dp),
                onNextClick = viewModel::onNextEvent,
                selectedAnswerIndex = viewModel.selectedAnswerIndex.value,
                onSelect = viewModel::selectedChoice,
                scaffoldState = scaffoldState,
                currentQuestionIdx = currentQuestionIndex,
                totalQuestionCount = totalQuestions,
                navController = navController
            )
        }
    }
}