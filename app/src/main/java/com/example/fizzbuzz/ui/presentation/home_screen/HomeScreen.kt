package com.example.fizzbuzz.ui.presentation.home_screen

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fizzbuzz.R
import com.example.fizzbuzz.ui.presentation.home_screen.components.ExitDialog
import com.example.fizzbuzz.ui.presentation.home_screen.components.HomeScreenHeader
import com.example.fizzbuzz.ui.presentation.home_screen.components.NicknameDialog
import com.example.fizzbuzz.ui.presentation.home_screen.components.NicknameDisplay
import com.example.fizzbuzz.ui.presentation.home_screen.intent.HomeIntent

@Composable
fun HomeScreen(
    navigateToPlayGameScreen: () -> Unit,
    navigateToLeaderboardScreen: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val activity = LocalContext.current as? ComponentActivity

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column(
            modifier = Modifier
                .weight(1f),
        ) {

            HomeScreenHeader(
                onClick = navigateToLeaderboardScreen,
                onClickExit = {
                    viewModel.processIntent(HomeIntent.ShowExitDialog)
                }
            )

            NicknameDisplay(
                nickname = state.nickname,
                onClick = {
                    viewModel.processIntent(HomeIntent.ShowNicknameDialog)
                }

            )
        }


        Image(
            painter = painterResource(id = R.drawable.logo2),
            contentDescription = "Logo",
            modifier = Modifier
                .size(350.dp),
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.secondary)
        )


        Row(
            modifier = Modifier
                .weight(1f)
                .padding(top = 90.dp)
        ) {
            Button(
                onClick = navigateToPlayGameScreen,
                modifier = Modifier,
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = stringResource(id = R.string.play),
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }

    if (state.exitDialog) {
        ExitDialog(
            onDismissRequest = { viewModel.processIntent(HomeIntent.DismissExitDialog) },
            onConfirmation = {
                activity?.finishAffinity()
            },
            painter = painterResource(id = R.drawable.exit_from_app),
            imageDescription = "exit"
        )

    }

    if (state.nicknameDialog) {
        NicknameDialog(
            onDismissRequest = { viewModel.processIntent(HomeIntent.DismissNicknameDialog) },
            onConfirmation = { newNickname ->
                viewModel.processIntent(HomeIntent.EnterNickname(newNickname))
                viewModel.processIntent(HomeIntent.DismissNicknameDialog)
            },
            dialogTitle = "Your nickname",
            currentNickname = state.nickname ?: ""
        )
    }

}