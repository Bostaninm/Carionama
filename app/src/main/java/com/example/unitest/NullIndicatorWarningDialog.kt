package com.example.unitest

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NullIndicatorWarningDialog(
    onDismissRequest: () -> Unit,
    indicatorName: String? // Optional: Pass the name of the missing indicator
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        icon = {
            Icon(
                Icons.Rounded.Warning,
                contentDescription = stringResource(R.string.warning_sign),
                tint = MaterialTheme.colorScheme.error
            )
        },
        title = {
            Text(text = stringResource(R.string.missing_information))
        },
        text = {
            val message = if (indicatorName != null) {
                stringResource(
                    R.string.null_indicator_warning_message_with_indicator_name,
                    indicatorName
                )
            } else {
                stringResource(R.string.null_indicator_warning_message)
            }
            Text(message)
        },
        confirmButton = {
            TextButton(
                onClick = onDismissRequest
            ) {
                Text(stringResource(R.string.ok))
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewNullIndicatorWarningDialogWithName() {
    NullIndicatorWarningDialog(
        onDismissRequest = {},
        indicatorName = "Blood Pressure"
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewNullIndicatorWarningDialogGeneric() {
    NullIndicatorWarningDialog(
        onDismissRequest = {},
        indicatorName = null
    )
}
