package com.example.carionama.survey.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.carionama.R

/**
 * A reusable Composable for number input within a specified range.
 *
 * @param value The current text value of the input field.
 * @param onValueChange Callback that is triggered when the input value changes.
 *                      It provides the raw text input.
 * @param onNumberInRangeChanged Callback that is triggered when a valid number
 *                             within the specified range is entered or cleared.
 *                             It provides the valid number as an Int?, or null if invalid/empty.
 * @param modifier Modifier for this Composable.
 * @param label The label to display for the text field.
 * @param minRange The minimum allowed number (inclusive).
 * @param maxRange The maximum allowed number (inclusive).
 * @param enabled Controls the enabled state of the [OutlinedTextField]. When `false`,
 *                this text field will not be clickable and visually muted.
 * @param singleLine When set to true, this text field becomes a single horizontally scrolling
 *                   text field instead of wrapping onto multiple lines.
 */
@Composable
fun RangedNumberInputField(
    value: String,
    onValueChange: (String) -> Unit,
    onNumberInRangeChanged: (Int?) -> Unit,
    modifier: Modifier = Modifier,
    label: String = stringResource(R.string.enter_number),
    minRange: Int = 0,
    maxRange: Int = 100,
    enabled: Boolean = true,
    singleLine: Boolean = true
) {
    var errorMessage by remember(minRange, maxRange) { mutableStateOf<String?>(null) }

    // Ensure minRange is not greater than maxRange
    val actualMinRange = minOf(minRange, maxRange)
    val actualMaxRange = maxOf(minRange, maxRange)
    val rangeErrorMessage =
        stringResource(R.string.invalid_range_error_message, actualMinRange, actualMaxRange)
    val invalidNumberErrorMessage = stringResource(R.string.invalid_number)
    OutlinedTextField(
        value = value,
        onValueChange = { newText ->
            // Allow only digits or empty string
            if (newText.isEmpty() || newText.all { it.isDigit() }) {
                onValueChange(newText) // Update the raw text state

                val number = newText.toIntOrNull()
                if (number != null) {
                    if (number in actualMinRange..actualMaxRange) {
                        onNumberInRangeChanged(number)
                        errorMessage = null
                    } else {
                        onNumberInRangeChanged(null)
                        errorMessage = rangeErrorMessage
                    }
                } else if (newText.isNotEmpty()) {
                    onNumberInRangeChanged(null)
                    errorMessage = invalidNumberErrorMessage
                } else { // Empty text
                    onNumberInRangeChanged(null)
                    errorMessage = null // Clear error if input is empty
                }
            }
        },
        label = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = String.format("$label (%d-%d)", actualMinRange, actualMaxRange),
                textAlign = TextAlign.Center
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        isError = errorMessage != null,
        supportingText = {
            errorMessage?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        },
        enabled = enabled,
        singleLine = singleLine,
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun RangedNumberInputFieldPreview() {
    var textValue by remember { mutableStateOf("") }
    var validNumber by remember { mutableStateOf<Int?>(null) }

    Column(modifier = Modifier.padding(16.dp)) {
        RangedNumberInputField(
            value = textValue,
            onValueChange = { textValue = it },
            onNumberInRangeChanged = { validNumber = it },
            label = "Quantity",
            minRange = 1,
            maxRange = 50,
            modifier = Modifier.width(256.dp)
        )
        Text(
            text = "Raw input: $textValue",
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = "Valid number in range: ${validNumber ?: "N/A"}",
            modifier = Modifier.padding(top = 8.dp)
        )

        RangedNumberInputField(
            value = "", // Example with initial empty and different range
            onValueChange = { /* ... */ },
            onNumberInRangeChanged = { /* ... */ },
            label = "Score",
            minRange = 0,
            maxRange = 10,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
    }
}
