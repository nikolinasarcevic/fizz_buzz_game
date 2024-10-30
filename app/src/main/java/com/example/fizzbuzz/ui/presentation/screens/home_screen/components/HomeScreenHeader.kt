package com.example.fizzbuzz.ui.presentation.screens.home_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fizzbuzz.R
import com.example.fizzbuzz.ui.presentation.components.ImageButtonComponent
import com.example.fizzbuzz.ui.theme.FizzBuzzTheme

@Composable
fun HomeScreenHeader(
    onClick: () -> Unit,
    onClickExit: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 42.dp, start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ImageButtonComponent(
            imageVector = ImageVector.vectorResource(id = R.drawable.history),
            contentDescription = stringResource(id = R.string.repeat_description),
            onClick = onClick,
            modifier = Modifier
                .size(45.dp)
        )

        ImageButtonComponent(
            imageVector = ImageVector.vectorResource(id = R.drawable.exit),
            contentDescription = stringResource(id = R.string.exit_description),
            onClick = onClickExit,
            modifier = Modifier
                .size(45.dp)
        )
    }

}

@Preview
@Composable
private fun HomeScreenHeaderPreview() {
    FizzBuzzTheme {
        HomeScreenHeader(
            onClick = {},
            onClickExit = {}
        )
    }

}