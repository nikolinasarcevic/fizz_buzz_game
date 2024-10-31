package com.example.fizzbuzz.ui.presentation.screens.play_game_screen.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fizzbuzz.ui.theme.FizzBuzzTheme
import kotlinx.coroutines.delay
import timber.log.Timber

@SuppressLint("DefaultLocale")
@Composable
fun PlayGameTimer(
    modifier: Modifier = Modifier,
    totalTime: Long,
    inactiveBarColor: Color,
    activeBarColor: Color,
    onTimerFinished: () -> Unit,
    displayText: String,
    resetTimer: Boolean,
    onTimerReset: () -> Unit,
    onTimeTick: (Long) -> Unit
) {

    var timeRemaining by rememberSaveable { mutableLongStateOf(totalTime) }
    var startTime by rememberSaveable { mutableLongStateOf(0L) }
    var isRunning by rememberSaveable { mutableStateOf(false) }


    LaunchedEffect(isRunning, resetTimer) {
        val time1 = System.currentTimeMillis()
        isRunning = true
        startTime = System.currentTimeMillis()

        if (resetTimer) {
            timeRemaining = totalTime
            startTime = System.currentTimeMillis()
            isRunning = true
            onTimerReset()
        }

        while (isRunning && timeRemaining > 0) {
            delay(10)

            val elapsedTime = System.currentTimeMillis() - startTime
            timeRemaining = (totalTime - elapsedTime).coerceAtLeast(0)
            onTimeTick(timeRemaining)

            if (timeRemaining <= 0) {
                onTimerFinished()
                isRunning = false
            }
        }
        val time2 = System.currentTimeMillis()

        Timber.d("razlika: ${time2-time1}")
    }

    val displaySeconds = ((timeRemaining / 1000) + 1).coerceAtMost(totalTime / 1000)

    val minutes = displaySeconds / 60
    val seconds = displaySeconds % 60
    val formattedTime = String.format("%02d:%02d", minutes, seconds)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .size(180.dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(180.dp)
            ) {
                val diameter = size.minDimension
                val strokeWidth = 10.dp.toPx()

                val centerX = size.width / 2
                val centerY = size.height / 2

                val radius = diameter / 2 - strokeWidth / 2

                val angle = 360f * ((totalTime - timeRemaining).toFloat() / totalTime)

                drawCircle(
                    color = inactiveBarColor,
                    radius = radius,
                    center = Offset(centerX, centerY),
                    style = Stroke(strokeWidth)
                )

                drawArc(
                    color = activeBarColor,
                    startAngle = -90f,
                    sweepAngle = angle,
                    useCenter = false,
                    style = Stroke(strokeWidth),
                    size = Size(radius * 2, radius * 2),
                    topLeft = Offset(centerX - radius, centerY - radius)
                )
            }


            Text(

                modifier = Modifier,
                text = displayText,
                style = MaterialTheme.typography.labelMedium,
                fontSize = 80.sp,
                color = MaterialTheme.colorScheme.secondary,
            )
        }


        Text(
            modifier = Modifier
                .padding(top = 5.dp),
            text = formattedTime,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.secondary,
        )
    }
}


@Preview
@Composable
private fun TimerView() {
    FizzBuzzTheme {
        PlayGameTimer(
            totalTime = 5,
            inactiveBarColor = MaterialTheme.colorScheme.secondary,
            activeBarColor = MaterialTheme.colorScheme.tertiary,
            onTimerFinished = {},
            displayText = "1",
            resetTimer = false,
            onTimerReset = {},
            onTimeTick = {}
        )
    }

}