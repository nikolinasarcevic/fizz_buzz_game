package com.example.fizzbuzz.ui.presentation.home_screen.components

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.fizzbuzz.R
import com.example.fizzbuzz.ui.presentation.components.ImageButtonComponent

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
        )

        ImageButtonComponent(
            imageVector = ImageVector.vectorResource(id = R.drawable.exit),
            contentDescription = "Exit",
            onClick = onClickExit,
            modifier = Modifier
                .size(45.dp)
        )
    }

}