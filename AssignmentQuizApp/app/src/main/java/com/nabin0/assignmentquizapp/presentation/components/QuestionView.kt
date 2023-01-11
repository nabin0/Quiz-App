package com.nabin0.assignmentquizapp.presentation.components

import android.graphics.drawable.shapes.Shape
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nabin0.assignmentquizapp.data.Question
import com.nabin0.assignmentquizapp.presentation.util.Screen
import com.nabin0.assignmentquizapp.ui.theme.Shapes
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun QuestionView(
    modifier: Modifier = Modifier,
    question: Question,
    onNextClick: () -> Unit,
    selectedAnswerIndex: Int,
    onSelect: (Int) -> Unit,
    scaffoldState: ScaffoldState,
    currentQuestionIdx: Int,
    totalQuestionCount: Int,
    navController: NavController
) {

    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier, horizontalAlignment = CenterHorizontally
    ) {
        AnimatedContent(targetState = question, transitionSpec = {
            slideInHorizontally { fullWidth ->
                fullWidth
            } + fadeIn() with slideOutHorizontally { fullWidth -> -fullWidth } + fadeOut()
        }) {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                item {
                    Text(
                        text = question.title,
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.h6,
                        textAlign = TextAlign.Center
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(130.dp)
                    ) {
                        Image(
                            painter = painterResource(id = question.image),
                            contentDescription = "Question Image",
                            modifier = Modifier
                                .height(120.dp)
                                .align(Alignment.Center)
                        )
                    }
                }

                itemsIndexed(question.choices) { index, choice ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onSelect(index)
                            },
                        shape = Shapes.medium,
                        backgroundColor = if (selectedAnswerIndex == index) Color.Gray.copy(alpha = 0.4f) else MaterialTheme.colors.surface.copy(
                            alpha = 0.9f
                        )
                    ) {
                        Text(
                            text = choice,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                                .align(CenterHorizontally),
                            style = MaterialTheme.typography.h6,
                            textAlign = TextAlign.Center
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    Button(
                        onClick = {
                            if (selectedAnswerIndex == -1) {
                                scope.launch {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        message = "Select a answer to continue.",
                                        duration = SnackbarDuration.Short
                                    )
                                }
                            } else if (currentQuestionIdx + 1 == totalQuestionCount) {
                                onNextClick()
                                navController.navigate(Screen.ResultScreen.route) {
                                    popUpTo(Screen.HomeScreen.route) {
                                        inclusive = true
                                    }
                                }
                            } else {
                                onNextClick()
                            }
                        }, modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ) {
                        Text(text = "Next")
                    }
                }
            }
        }

    }

}
