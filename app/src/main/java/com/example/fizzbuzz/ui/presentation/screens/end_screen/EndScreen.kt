package com.example.fizzbuzz.ui.presentation.screens.end_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fizzbuzz.R
import com.example.fizzbuzz.ui.presentation.screens.end_screen.components.EndScreenFooter
import com.example.fizzbuzz.ui.theme.FizzBuzzTheme

@Composable
fun EndScreen(
    endScreenScore: Int,
    isHighScore: Boolean,
    navigateToHomeScreen: () -> Unit,
    navigateToPlayScreen: () -> Unit,
) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        val textDisplay = if(isHighScore) {
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
                .padding(bottom = 305.dp),
            text = "$endScreenScore",
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

@Preview
@Composable
private fun EndScreenPreview() {
    FizzBuzzTheme {
        EndScreen(endScreenScore = 120, isHighScore = true, navigateToHomeScreen = { /*TODO*/ }) {
            
        }
    }
    
}
