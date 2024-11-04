package com.example.fizzbuzz.ui.presentation.screens.end_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fizzbuzz.R
import com.example.fizzbuzz.ui.presentation.screens.end_screen.components.EndScreenFooter

@Composable
fun EndScreen(
    endScreenScore: Int,
    isHighScore: Boolean,
    navigateToHomeScreen: () -> Unit,
    navigateToPlayScreen: () -> Unit,
    viewModel: EndViewModel = hiltViewModel(),
) {

    val state by viewModel.state.collectAsStateWithLifecycle()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        val textDisplay = if (isHighScore) {
            stringResource(id = R.string.newScore)

        } else {
            stringResource(id = R.string.score)
        }

        Text(
            modifier = Modifier
                .padding(top = 100.dp),
            text = textDisplay,
            style = MaterialTheme.typography.labelMedium,
            fontSize = 70.sp,
            color = MaterialTheme.colorScheme.secondary
        )

        Text(
            modifier = Modifier
                .padding(bottom = 55.dp),
            text = "$endScreenScore",
            style = MaterialTheme.typography.labelMedium,
            fontSize = 80.sp,
            color = MaterialTheme.colorScheme.secondary
        )

        Text(
            modifier = Modifier
                .padding(bottom = 10.dp),
            text = stringResource(id = R.string.highest_score_text),
            style = MaterialTheme.typography.labelMedium,
            fontSize = 30.sp,
            color = MaterialTheme.colorScheme.secondary,
        )

        Text(
            modifier = Modifier
                .padding(bottom = 150.dp),
            text = if (isHighScore) "$endScreenScore" else "${state.highestScore}",
            style = MaterialTheme.typography.labelMedium,
            fontSize = 50.sp,
            color = MaterialTheme.colorScheme.secondary,
        )

        EndScreenFooter(
            onClickHome = navigateToHomeScreen,
            onClickRepeat = navigateToPlayScreen
        )
    }
}

//@Preview
//@Composable
//private fun EndScreenPreview() {
//    FizzBuzzTheme {
//        EndScreen(endScreenScore = 120, isHighScore = true, navigateToHomeScreen = { /*TODO*/ }) {
//        }
//    }
//
//}
