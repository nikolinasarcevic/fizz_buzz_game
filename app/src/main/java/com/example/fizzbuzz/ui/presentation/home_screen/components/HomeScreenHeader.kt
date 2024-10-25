package com.example.fizzbuzz.ui.presentation.home_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
            contentDescription = "History",
            onClick = onClick,
            modifier = Modifier
                .size(45.dp)
                .shadow(
                    shape = CircleShape,
                    elevation = 200.dp,
                    clip = true
                )
        )

        ImageButtonComponent(
            imageVector = ImageVector.vectorResource(id = R.drawable.exit),
            contentDescription = "Exit",
            onClick = onClickExit,
            modifier = Modifier
                .size(45.dp)
                .shadow(
                    elevation = 200.dp,
                    clip = true,
                    shape = GenericShape { size, _ ->
                        // Pravokutnik
                        moveTo(0f, 0f)
                        lineTo(size.width * 0.7f, 0f)
                        lineTo(size.width * 0.7f, size.height)
                        lineTo(0f, size.height)
                        close()

                        // Strelica koja izlazi iz pravokutnika
                        moveTo(size.width * 0.7f, size.height * 0.25f)
                        lineTo(size.width * 0.7f, size.height * 0.75f)
                        lineTo(size.width, size.height * 0.5f)
                        close()
                    },
                ),
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