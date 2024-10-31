package com.example.fizzbuzz.ui.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fizzbuzz.ui.theme.FizzBuzzTheme
import kotlinx.coroutines.delay

@Composable
fun GradientAnimatedButton(
    modifier: Modifier,
    buttonText: String,
    firstColor: Color,
    secondColor: Color,
    textColor: Color,
    onClick: () -> Unit,
) {

    var colorIndex by remember { mutableIntStateOf(0) }


    val leftSide by animateColorAsState(
        targetValue = if (colorIndex == 0) firstColor else secondColor,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    val rightSide by animateColorAsState(
        targetValue = if (colorIndex == 0) secondColor else firstColor,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 400,
                easing = LinearOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )



    LaunchedEffect(Unit) {
        while (true) {
            delay(400)
            colorIndex = (colorIndex + 1) % 2
        }
    }


    Button(
        modifier = modifier,
        onClick = onClick,
        contentPadding = PaddingValues(),
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            leftSide,
                            rightSide
                        )
                    )
                )
        ) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 35.dp, vertical = 12.dp),
                text = buttonText,
                color = textColor,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }

}

@Preview
@Composable
private fun ButtonAnimatedPreview() {
    FizzBuzzTheme {
        GradientAnimatedButton(
            buttonText = "PLAY NOW",
            firstColor = MaterialTheme.colorScheme.secondary,
            secondColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f),
            textColor = MaterialTheme.colorScheme.primary,
            onClick = {},
            modifier = Modifier
        )
    }

}