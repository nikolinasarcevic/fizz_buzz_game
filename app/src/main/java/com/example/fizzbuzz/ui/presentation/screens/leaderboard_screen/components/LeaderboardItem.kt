package com.example.fizzbuzz.ui.presentation.screens.leaderboard_screen.components

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.example.fizzbuzz.utils.dateFormatterHelper
import com.example.fizzbuzz.utils.timeFormatterHelper
import java.time.LocalDateTime

@SuppressLint("SimpleDateFormat")
@Composable
fun LeaderboardItem(
    rank: Int,
    score: Score,
    backgroundColor: Color,
    textColor: Color,
    dateTextColor: Color,
    isTimeVisible: Boolean
) {

    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = backgroundColor, shape = RoundedCornerShape(20.dp))
                .padding(vertical = 16.dp, horizontal = 40.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                text = "$rank.",
                color = textColor,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 40.sp,
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

        AnimatedVisibility (isTimeVisible) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = dateFormatterHelper(playedAt = score.playedAt),
                    color = dateTextColor,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 16.sp
                )
                Text(
                    text = timeFormatterHelper(playedAt = score.playedAt),
                    color = dateTextColor,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Preview
@Composable
private fun ItemPreview() {
    FizzBuzzTheme {
        LeaderboardItem(
            score = Score(nickname = "Player1", scoreValue = 150, playedAt = LocalDateTime.now()),
            rank = 1,
            backgroundColor = MaterialTheme.colorScheme.secondary,
            textColor = MaterialTheme.colorScheme.primary,
            isTimeVisible = true,
            dateTextColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.4f)
        )
    }

}