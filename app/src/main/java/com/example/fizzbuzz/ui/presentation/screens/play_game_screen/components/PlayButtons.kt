package com.example.fizzbuzz.ui.presentation.screens.play_game_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fizzbuzz.R
import com.example.fizzbuzz.ui.presentation.components.CustomButton
import com.example.fizzbuzz.ui.theme.FizzBuzzTheme

@Composable
fun PlayButtons(
    modifier: Modifier,
    onClickFizz: () -> Unit,
    onClickBuzz: () -> Unit,
    onClickFizzBuzz: () -> Unit,
    onClickNext: () -> Unit
) {
    Column(
        modifier = Modifier
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            CustomButton(
                text = stringResource(id = R.string.Fizz),
                onClick = onClickFizz,
                modifier = Modifier
                    .weight(1f)

            )


            CustomButton(
                text = stringResource(id = R.string.Buzz),
                onClick = onClickBuzz,
                modifier = Modifier
                    .weight(1f)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .padding(top = 10.dp, bottom = 50.dp),
            horizontalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            CustomButton(
                text = stringResource(id = R.string.FizzBuzz),
                onClick = onClickFizzBuzz,
                modifier = Modifier
                    .weight(1f)
            )

            CustomButton(
                text = stringResource(id = R.string.Next),
                onClick = onClickNext,
                modifier = Modifier
                    .weight(1f)
            )
        }
    }
}

@Preview
@Composable
private fun ButtonsPreview() {
    FizzBuzzTheme {
        PlayButtons(modifier = Modifier, onClickFizz = {}, onClickBuzz = {}, onClickFizzBuzz = {}, onClickNext = {})
    }
}
