package com.nabin0.assignmentquizapp.domain

import com.nabin0.assignmentquizapp.data.Question
import javax.inject.Inject

class GetAllQuestionsUseCase @Inject constructor() {
    fun getAllQuestions(): List<Question> {
        return Question.getAllQuestions()
    }
}