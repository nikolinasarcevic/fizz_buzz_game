package com.example.fizzbuzz.ui.presentation.home_screen

import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.fizzbuzz.ui.presentation.home_screen.components.ExitDialog
import com.example.fizzbuzz.ui.presentation.home_screen.components.NicknameDisplay
import com.example.fizzbuzz.ui.presentation.home_screen.intent.HomeIntent

@Composable
fun HomeScreen(
    navigateToPlayGameScreen: () -> Unit,
    navigateToLeaderboardScreen: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    var showExitDialog by remember { mutableStateOf(false) }
    val activity = LocalContext.current as? ComponentActivity
    val state by viewModel.state.collectAsStateWithLifecycle()


//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(color = MaterialTheme.colorScheme.primary)
//    ) {

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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 42.dp, start = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.history),
                    contentDescription = "History",
                    modifier = Modifier
                        .size(45.dp)
                        .clickable(onClick = navigateToLeaderboardScreen),
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.secondary)
                )


                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.exit),
                    contentDescription = "Exit",
                    modifier = Modifier
                        .size(45.dp)
                        .clickable { showExitDialog = true },
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.secondary)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                NicknameDisplay(
                    nickname = state.nickname,
                    onNicknameSaved = { newNickname ->
                        viewModel.processIntent(HomeIntent.EnterNickname(newNickname))
                    }
                )
            }
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

    if (showExitDialog) {
        ExitDialog(
            onDismissRequest = { showExitDialog = false },
            onConfirmation = {
                activity?.finishAffinity()
            },
            painter = painterResource(id = R.drawable.exit_from_app),
            imageDescription = "exit"
        )

    }


//    }
}