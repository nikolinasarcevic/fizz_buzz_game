package com.example.fizzbuzz.ui.presentation.screens.leaderboard_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fizzbuzz.R
import com.example.fizzbuzz.ui.presentation.components.ImageButtonComponent
import com.example.fizzbuzz.ui.theme.FizzBuzzTheme

@Composable
fun LeaderboardScreenHeader(
    onClickBack: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 50.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        ImageButtonComponent(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .size(45.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.back),
            contentDescription = stringResource(id = R.string.back_description),
            onClick = onClickBack,
        )

        Text(
            modifier = Modifier
                .weight(1f),
            text = stringResource(id = R.string.leaderboard),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.Center,
            fontSize = 40.sp
        )

    }
}

@Preview
@Composable
private fun LeaderboardHeaderPreview() {
    FizzBuzzTheme {
        LeaderboardScreenHeader(onClickBack = {})
    }

}