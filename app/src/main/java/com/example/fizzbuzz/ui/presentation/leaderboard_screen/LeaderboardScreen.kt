package com.example.fizzbuzz.ui.presentation.leaderboard_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fizzbuzz.R
import com.example.fizzbuzz.ui.presentation.leaderboard_screen.components.LeaderboardItem
import com.example.fizzbuzz.ui.presentation.leaderboard_screen.components.LeaderboardScreenHeader
import com.example.fizzbuzz.ui.theme.FizzBuzzTheme

@Composable
fun LeaderboardScreen(
    onBackClick: () -> Unit,
    viewModel: LeaderboardViewModel = hiltViewModel()
) {

    val isInfoVisible = rememberSaveable { mutableStateOf(false) }
    val leaderboard by viewModel.leaderboard.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LeaderboardScreenHeader(onBackClick)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, end = 35.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = if (isInfoVisible.value)
                    stringResource(id = R.string.hide)
                else
                    stringResource(id = R.string.show),
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .clickable {
                        isInfoVisible.value = !isInfoVisible.value
                    }
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .windowInsetsPadding(WindowInsets.systemBars),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            itemsIndexed(leaderboard) { index, score ->
                val backgroundColor = when (index) {
                    0 -> MaterialTheme.colorScheme.secondary
                    1 -> MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f)
                    2 -> MaterialTheme.colorScheme.secondary.copy(alpha = 0.4f)
                    else -> MaterialTheme.colorScheme.tertiary
                }

                val textColor = when (index) {
                    0, 1, 2 -> MaterialTheme.colorScheme.primary
                    else -> MaterialTheme.colorScheme.secondary
                }

                LeaderboardItem(
                    rank = index + 1,
                    score = score,
                    backgroundColor = backgroundColor,
                    textColor = textColor
                )
            }

            item {
                Spacer(modifier = Modifier
                    .height(16.dp)
                    .windowInsetsPadding(WindowInsets.systemBars))
                }
        }


    }

    if (isInfoVisible.value) {
        Text(
            text = "informacije",
            modifier = Modifier
                .padding(start = 50.dp, top = 200.dp)
        )
    }

}

@Preview
@Composable
private fun LeaderboardScreenPreview() {
    FizzBuzzTheme {
        LeaderboardScreen(onBackClick = {})
    }
}