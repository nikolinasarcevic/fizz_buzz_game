package com.example.fizzbuzz.ui.presentation.play_game_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fizzbuzz.ui.presentation.components.CustomButton
import com.example.fizzbuzz.ui.theme.FizzBuzzTheme

@Composable
fun PlayButtons() {
    Column(
        modifier = Modifier
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 36.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CustomButton(
                text = "FIZZ",
                onClick = {},
                height = 65,
                width = 160
            )

            CustomButton(
                text = "BUZZ",
                onClick = {},
                height = 65,
                width = 160
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CustomButton(
                text = "FIZZBUZZ",
                onClick = {},
                height = 65,
                width = 160
            )

            CustomButton(
                text = "NEXT",
                onClick = {},
                height = 65,
                width = 160
            )
        }
    }
}

@Preview
@Composable
private fun ButtonsPreview() {
    FizzBuzzTheme {
        PlayButtons()
    }
}
