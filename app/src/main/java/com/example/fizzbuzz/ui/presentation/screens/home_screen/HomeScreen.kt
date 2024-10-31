package com.example.fizzbuzz.ui.presentation.screens.home_screen

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fizzbuzz.R
import com.example.fizzbuzz.ui.presentation.components.GradientAnimatedButton
import com.example.fizzbuzz.ui.presentation.screens.home_screen.components.ExitDialog
import com.example.fizzbuzz.ui.presentation.screens.home_screen.components.HomeScreenHeader
import com.example.fizzbuzz.ui.presentation.screens.home_screen.components.NicknameDialog
import com.example.fizzbuzz.ui.presentation.screens.home_screen.components.NicknameDisplay
import com.example.fizzbuzz.ui.presentation.screens.home_screen.intent.HomeIntent

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
            contentDescription = stringResource(id = R.string.logo_description),
            modifier = Modifier
                .size(350.dp)
                .weight(1f),
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.secondary)
        )



        GradientAnimatedButton(
            modifier = Modifier
                .padding(bottom = 100.dp),
            buttonText = stringResource(id = R.string.play),
            firstColor = MaterialTheme.colorScheme.secondary,
            secondColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f),
            textColor = MaterialTheme.colorScheme.primary,
            onClick = navigateToPlayGameScreen
        )


        if (state.exitDialog) {
            ExitDialog(
                onDismissRequest = { viewModel.processIntent(HomeIntent.DismissExitDialog) },
                onConfirmation = {
                    activity?.finishAffinity()
                },
                imageVector = ImageVector.vectorResource(id = R.drawable.exit_from_app),
                imageDescription = stringResource(id = R.string.exit_description)
            )

        }

        if (state.nicknameDialog) {
            NicknameDialog(
                onDismissRequest = { viewModel.processIntent(HomeIntent.DismissNicknameDialog) },
                onConfirmation = { newNickname ->
                    viewModel.processIntent(HomeIntent.EnterNickname(newNickname))
                    viewModel.processIntent(HomeIntent.DismissNicknameDialog)
                },
                dialogTitle = stringResource(id = R.string.nickname_dialog_title),
                currentNickname = state.nickname ?: ""
            )
        }

    }
}