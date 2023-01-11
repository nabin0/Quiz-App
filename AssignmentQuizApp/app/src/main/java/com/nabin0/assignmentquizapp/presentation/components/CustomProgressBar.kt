package com.nabin0.assignmentquizapp.presentation.components

import android.view.RoundedCorner
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CustomProgressBar(
    modifier: Modifier = Modifier,
    currentProgressValue: Int,
    totalProgressValue: Int
) {
    val progressInPercentage = (currentProgressValue.toFloat() / totalProgressValue)
    val progressValue by animateFloatAsState(
        targetValue = progressInPercentage,
        tween(
            durationMillis = 400,
            delayMillis = 50,
            easing = LinearEasing
        )
    )
    Column(modifier = modifier) {
        // Text
        Text(
            text = "Question ${currentProgressValue} / $totalProgressValue",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h4
        )
        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
                    .background(Color.Cyan)
                    .clip(RoundedCornerShape(12.dp))
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(progressValue)
                    .fillMaxHeight()
                    .background(Color.Magenta)
                    .clip(RoundedCornerShape(12.dp))
                    .animateContentSize()
            )
        }

    }

}