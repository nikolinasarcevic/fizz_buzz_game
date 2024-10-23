package com.example.fizzbuzz.ui.presentation.leaderboard_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fizzbuzz.database.Score
import com.example.fizzbuzz.ui.theme.FizzBuzzTheme

@Composable
fun LeaderboardItem(
    rank: Int,
    score: Score,
    backgroundColor: Color,
    textColor: Color,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = backgroundColor, shape = RoundedCornerShape(20.dp))
            .padding(vertical = 16.dp, horizontal = 40.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = "$rank.",
            color = textColor,
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 40.sp,
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.CenterVertically)
        )

        Text(
            text = score.nickname,
            color = textColor,
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 25.sp
        )

        Text(
            text = "${score.scoreValue}",
            color = textColor,
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 40.sp
        )
    }
}

@Preview
@Composable
private fun ItemPreview() {
    FizzBuzzTheme {
        LeaderboardItem(
            score = Score(nickname = "Player1", scoreValue = 150),
            rank = 1,
            backgroundColor = MaterialTheme.colorScheme.secondary,
            textColor = MaterialTheme.colorScheme.primary
        )
    }

}