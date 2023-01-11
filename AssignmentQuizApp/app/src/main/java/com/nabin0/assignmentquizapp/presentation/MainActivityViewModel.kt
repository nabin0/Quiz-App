package com.nabin0.assignmentquizapp.presentation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.nabin0.assignmentquizapp.data.Question
import com.nabin0.assignmentquizapp.domain.GetAllQuestionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getAllQuestionsUseCase: GetAllQuestionsUseCase
) : ViewModel() {
    private val _allQuestions: MutableState<List<Question>> = mutableStateOf(listOf())
    val allQuestions: State<List<Question>> = _allQuestions

    private val _totalQuestions = mutableStateOf(0)
    val totalQuestions: State<Int> = _totalQuestions

    private val _currentQuestionIndex = mutableStateOf(0)
    val currentQuestionIndex: State<Int> = _currentQuestionIndex

    val selectedAnswerIndex: MutableState<Int> = mutableStateOf(-1)

    var correctAnswerCount: Int = 0
    var wrongAnswersCount: Int = 0

    init {
        getAllQuestions()
        _totalQuestions.value = allQuestions.value.size
    }


    private fun getAllQuestions() {
        _allQuestions.value = getAllQuestionsUseCase.getAllQuestions()
    }

    fun onNextEvent() {
        if (currentQuestionIndex.value < totalQuestions.value) {
            if (allQuestions.value[currentQuestionIndex.value].answer.equals(
                    allQuestions.value[currentQuestionIndex.value].choices[selectedAnswerIndex.value],
                    true
                )
            ) {
                correctAnswerCount += 1
                Log.d("MyTag", "correct $correctAnswerCount")
            } else {
                wrongAnswersCount++
                Log.d("MyTag", "not correct $wrongAnswersCount")
            }
            selectedAnswerIndex.value = -1
        }
        if (currentQuestionIndex.value < totalQuestions.value - 1) {
            _currentQuestionIndex.value += 1
        }
    }

    fun selectedChoice(index: Int) {
        selectedAnswerIndex.value = index
    }

    fun resetAllData() {
        _currentQuestionIndex.value = 0
        correctAnswerCount = 0
        wrongAnswersCount = 0
        selectedAnswerIndex.value = -1
    }
}