package com.example.fizzbuzz.ui.presentation.screens.leaderboard_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fizzbuzz.R
import com.example.fizzbuzz.ui.presentation.screens.leaderboard_screen.components.LeaderboardScreenHeader
import com.example.fizzbuzz.ui.presentation.screens.leaderboard_screen.components.PullToRefreshLazyColumn
import com.example.fizzbuzz.ui.presentation.screens.leaderboard_screen.intent.LeaderboardIntent
import com.example.fizzbuzz.ui.theme.FizzBuzzTheme

@Composable
fun LeaderboardScreen(
    onBackClick: () -> Unit,
    viewModel: LeaderboardViewModel = hiltViewModel()
) {

    val isInfoVisible = rememberSaveable { mutableStateOf(false) }
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        LeaderboardScreenHeader(onBackClick)

        Text(
            modifier = Modifier
                .padding(top = 6.dp, end = 30.dp)
                .align(Alignment.End)
                .clickable {
                    isInfoVisible.value = !isInfoVisible.value
                },

            text = if (isInfoVisible.value)
                stringResource(id = R.string.hide)
            else
                stringResource(id = R.string.show),

            style = MaterialTheme.typography.bodyMedium,
            fontSize = 15.sp,
            color = MaterialTheme.colorScheme.secondary,
            textAlign = TextAlign.End,
        )


        PullToRefreshLazyColumn(
            leaderboard = state.leaderboard,
            isInfoVisible = isInfoVisible.value,
            onRefresh = { viewModel.processIntent(LeaderboardIntent.LoadLeaderboard) }
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