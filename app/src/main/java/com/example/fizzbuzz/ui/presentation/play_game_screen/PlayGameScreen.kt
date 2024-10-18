package com.example.fizzbuzz.ui.presentation.play_game_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fizzbuzz.R
import com.example.fizzbuzz.ui.presentation.components.ImageButtonComponent
import com.example.fizzbuzz.ui.presentation.play_game_screen.components.PlayButtons
import com.example.fizzbuzz.ui.theme.FizzBuzzTheme
import com.example.fizzbuzz.ui.theme.serifDisplayFontFamily

@Composable
fun PlayGameScreen(
    navigateToLeaderboardScreen: () -> Unit,
//    viewModel: HomeViewModel = hiltViewModel(),
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
                .padding(top = 42.dp, end = 26.dp),
            horizontalArrangement = Arrangement.Absolute.Right
        ) {
            ImageButtonComponent(
                imageVector = ImageVector.vectorResource(id = R.drawable.next),
                contentDescription = "Next",
                onClick = navigateToLeaderboardScreen
            )
        }
        Box(
            modifier = Modifier
                .padding(16.dp)
                .size(180.dp)
                .border(
                    border = BorderStroke(4.dp, MaterialTheme.colorScheme.secondary),
                    shape = CircleShape
                )
                .clip(CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "1",
                fontFamily = serifDisplayFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 80.sp,
                color = MaterialTheme.colorScheme.secondary
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(top = 200.dp)
        ) {
            PlayButtons()
        }

    }
}

@Preview
@Composable
private fun PlayGamePreview() {
    FizzBuzzTheme {
        PlayGameScreen(navigateToLeaderboardScreen = {})
    }
}