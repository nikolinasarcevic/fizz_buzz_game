package com.example.fizzbuzz.ui.presentation.home_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.fizzbuzz.R
import timber.log.Timber


@Composable
fun NicknameDisplay(
    nickname: String?,
    onNicknameSaved: (String) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    if (nickname.isNullOrEmpty()) {
        Text(
            modifier = Modifier
                .clickable { showDialog = true }
                .alpha(0.3f),
            text = stringResource(id = R.string.nickname),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary,
        )
    } else {
        Text(
            text = "Hi, $nickname",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable { showDialog = true },
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center
        )
    }
    Timber.d("Before nickname: $nickname")

    if (showDialog) {
        NicknameDialog(
            onDismissRequest = { showDialog = false },
            onConfirmation = { newNickname ->
                saveNicknameToPreferences(context, newNickname)
                onNicknameSaved(newNickname)
                showDialog = false
                Timber.d("Current nickname: $nickname")

            },
            dialogTitle = "Your nickname",
            currentNickname = nickname ?: ""
        )
    }
}

