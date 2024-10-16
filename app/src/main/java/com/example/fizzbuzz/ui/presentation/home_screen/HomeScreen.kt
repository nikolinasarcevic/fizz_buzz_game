package com.example.fizzbuzz.ui.presentation.home_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fizzbuzz.R
import com.example.fizzbuzz.ui.presentation.home_screen.components.DialogWithImage
import com.example.fizzbuzz.ui.theme.FizzBuzzTheme
import com.example.fizzbuzz.ui.theme.serifDisplayFontFamily

@Composable
fun HomeScreen(navigateToPlayGameScreen: () -> Unit) {
    var showDialog by remember { mutableStateOf(false) }
    var showExitDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary)
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
                    .padding(top = 42.dp, start = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.history),
                    contentDescription = "History",
                    modifier = Modifier
                        .size(45.dp)
                        .clickable {  },
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.secondary)
                )


                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.exit),
                    contentDescription = "Exit",
                    modifier = Modifier
                        .size(45.dp)
                        .clickable {showExitDialog = true},
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.secondary)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .clickable { showDialog = true },
                    text = stringResource(id = R.string.nickname),
                    fontFamily = serifDisplayFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 25.sp,
                    color = MaterialTheme.colorScheme.secondary
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


            Button(
                onClick = navigateToPlayGameScreen,
                modifier = Modifier
                    .padding(bottom = 80.dp),
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

        if (showExitDialog) {
            DialogWithImage(
                onDismissRequest = { showExitDialog = false },
                onConfirmation = {
                    showDialog = false
                },
                painter = painterResource(id = R.drawable.exit_from_app),
                imageDescription = "exit"
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    FizzBuzzTheme {
        HomeScreen(navigateToPlayGameScreen = {})
    }
}