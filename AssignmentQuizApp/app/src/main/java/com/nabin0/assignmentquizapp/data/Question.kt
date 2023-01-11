package com.nabin0.assignmentquizapp.data

import com.nabin0.assignmentquizapp.R

data class Question(
    val title: String,
    val choices: List<String>,
    val answer: String,
    val image: Int
) {
    companion object {
        fun getAllQuestions(): List<Question> {
            return listOf(
                Question(
                    "What is the primary programming language used for Android app development?",
                    listOf("Java", "C++", "Python"),
                    "Java",
                    R.drawable.img1
                ),

                Question(
                    "What is the name of Android's layout manager?",
                    listOf("LinearLayout", "RelativeLayout", "ConstraintLayout"),
                    "ConstraintLayout",
                    R.drawable.img2
                ),
                Question(
                    "What is the name of Android's component for background tasks?",
                    listOf("Services", "Receivers", "Broadcast"),
                    "Services",
                    R.drawable.img1
                ),
                Question(
                    "What is the name of Android's component for notifications?",
                    listOf("Services", "Receivers", "Broadcast"),
                    "Broadcast",
                    R.drawable.img1
                ),
                Question(
                    "What is the name of Android's component for background tasks?",
                    listOf("Services", "Receivers", "Broadcast"),
                    "Services",
                    R.drawable.img2
                ),
                Question(
                    "What is the name of Android's component for handling user input?",
                    listOf("EditText", "TextInputLayout", "TextField"),
                    "EditText",
                    R.drawable.img2
                ),

                Question(
                    "What is the name of the Android framework for asynchronous execution?",
                    listOf("AsyncTask", "Coroutines", "Thread"),
                    "Coroutines",
                    R.drawable.img1
                ),
                Question(
                    "What is the name of Android's component for handling UI?",
                    listOf("View", "TextView", "ImageView"),
                    "View",
                    R.drawable.img2
                )
            )
        }
    }
}
