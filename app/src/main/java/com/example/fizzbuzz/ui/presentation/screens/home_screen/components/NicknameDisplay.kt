package com.example.fizzbuzz.ui.presentation.screens.home_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fizzbuzz.R
import com.example.fizzbuzz.ui.theme.FizzBuzzTheme


@Composable
fun NicknameDisplay(
    nickname: String?,
    onClick: () -> Unit
) {

    if (nickname.isNullOrEmpty()) {
        Text(
            modifier = Modifier
                .clickable { onClick() }
                .alpha(0.3f),
            text = stringResource(id = R.string.nickname),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary,
        )
    } else {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
                .padding(16.dp)
                .clickable { onClick() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Hi, $nickname",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.labelMedium,
                textAlign = TextAlign.Center,
            )

            Icon(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .size(20.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.edit),
                contentDescription = stringResource(id = R.string.edit_nickname),
                tint = MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f)
            )
        }
    }
}

@Preview
@Composable
private fun NicknameDisplayPreview() {
    FizzBuzzTheme {
        NicknameDisplay(nickname = "Nina") {
            
        }
    }
    
}


