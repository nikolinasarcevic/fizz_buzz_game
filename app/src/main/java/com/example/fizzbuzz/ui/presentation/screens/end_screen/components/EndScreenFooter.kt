package com.example.fizzbuzz.ui.presentation.screens.end_screen.components

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
fun EndScreenFooter(
    onClickHome: () -> Unit,
    onClickRepeat: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(56.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ImageButtonComponent(
            imageVector = ImageVector.vectorResource(id = R.drawable.home),
            contentDescription = stringResource(id = R.string.home_description),
            onClick = onClickHome,
            modifier = Modifier
                .size(75.dp)
        )

        ImageButtonComponent(
            imageVector = ImageVector.vectorResource(id = R.drawable.repeat),
            contentDescription = stringResource(id = R.string.repeat_description),
            onClick = onClickRepeat,
            modifier = Modifier
                .size(75.dp)
        )

    }
}

@Preview
@Composable
private fun FooterPreview() {
    FizzBuzzTheme {
        EndScreenFooter(onClickHome = {}) {
            
        }
    }
    
}