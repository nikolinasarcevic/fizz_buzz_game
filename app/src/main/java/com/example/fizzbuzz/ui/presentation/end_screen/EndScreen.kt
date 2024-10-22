package com.example.fizzbuzz.ui.presentation.end_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.fizzbuzz.R
import com.example.fizzbuzz.ui.presentation.components.ImageButtonComponent
import com.example.fizzbuzz.ui.presentation.end_screen.components.EndScreenFooter
import com.example.fizzbuzz.ui.presentation.home_screen.intent.HomeIntent
import com.example.fizzbuzz.ui.presentation.navigation.Screen
import com.example.fizzbuzz.ui.presentation.play_game_screen.PlayGameViewModel
import com.example.fizzbuzz.ui.presentation.play_game_screen.intent.PlayGameIntent
import com.example.fizzbuzz.ui.theme.FizzBuzzTheme

@Composable
fun EndScreen(
    navigateToHomeScreen: () -> Unit,
    navigateToPlayScreen: () -> Unit,
    viewModel: EndViewModel = hiltViewModel()
) {

//    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {


        Text(
            modifier = Modifier
                .padding(top = 100.dp),
            text = stringResource(id = R.string.score),
            style = MaterialTheme.typography.labelMedium,
            fontSize = 70.sp,
            color = MaterialTheme.colorScheme.secondary
        )

        Text(
            modifier = Modifier
                .padding(bottom = 305.dp),
            text = "1",
//            text = "${state.score}",
            style = MaterialTheme.typography.labelMedium,
            fontSize = 80.sp,
            color = MaterialTheme.colorScheme.secondary
        )
        EndScreenFooter(
            onClickHome = navigateToHomeScreen,
            onClickRepeat = navigateToPlayScreen
        )
    }
}
