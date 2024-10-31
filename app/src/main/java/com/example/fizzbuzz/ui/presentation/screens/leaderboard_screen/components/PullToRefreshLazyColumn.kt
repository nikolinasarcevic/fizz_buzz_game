@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.fizzbuzz.ui.presentation.screens.leaderboard_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fizzbuzz.database.Score

@Composable
fun PullToRefreshLazyColumn(
    leaderboard: List<Score>,
    isInfoVisible: Boolean,
    onRefresh: () -> Unit
) {
    val refreshState = rememberPullToRefreshState()
    var isRefreshing by remember { mutableStateOf(false) }

    PullToRefreshBox(
        state = refreshState,
        isRefreshing = isRefreshing,
        onRefresh = onRefresh
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(top = 10.dp, bottom = 30.dp)
                .windowInsetsPadding(WindowInsets.systemBars),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            itemsIndexed(leaderboard, key = {_, score -> score.id }) { index, score ->
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
                    textColor = textColor,
                    isTimeVisible = isInfoVisible,
                    dateTextColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.4f)
                )
            }

            item {
                Spacer(modifier = Modifier
                    .height(16.dp)
                    .padding()
                    .windowInsetsPadding(WindowInsets.systemBars))
            }
        }
    }
}