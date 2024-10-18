package com.example.fizzbuzz.ui.presentation.home_screen.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fizzbuzz.R
import com.example.fizzbuzz.ui.theme.FizzBuzzTheme
import com.example.fizzbuzz.ui.theme.serifDisplayFontFamily

@Composable
fun NicknameDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: (String) -> Unit,
    dialogTitle: String,
    currentNickname: String
) {

    val nickname = rememberSaveable { mutableStateOf(currentNickname) }

    AlertDialog(
        title = {
            Text(
                text = dialogTitle,
                fontFamily = serifDisplayFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.primary
            )
        },
        text = {
            OutlinedTextField(
                value = nickname.value,
                onValueChange = { nickname.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .height(60.dp),
                label = {
                    Text(
                        text = "Nickname",
                        fontSize = 12.sp,
                    )
                },
                shape = CircleShape,
                textStyle = MaterialTheme.typography.bodySmall,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
                    focusedContainerColor = MaterialTheme.colorScheme.secondary,
                    focusedTextColor = MaterialTheme.colorScheme.primary,
                    unfocusedTextColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                    focusedLabelColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                    unfocusedLabelColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                    focusedIndicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                )
            )
        },
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation(nickname.value)
                },
                enabled = nickname.value.isNotBlank()
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Cancel")
            }
        },
        containerColor = MaterialTheme.colorScheme.secondary
    )
}

@Preview(showBackground = true)
@Composable
fun NicknameDialogPreview() {
    FizzBuzzTheme {
        NicknameDialog(
            onDismissRequest = {},
            onConfirmation = {},
            dialogTitle = stringResource(id = R.string.nickname),
            currentNickname = "",
        )
    }
}