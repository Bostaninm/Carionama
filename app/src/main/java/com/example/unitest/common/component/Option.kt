package com.example.unitest.common.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.unitest.IndicatorOption

@Composable
fun Option(
    modifier: Modifier = Modifier,
    option: IndicatorOption,
    selected: Boolean,
    onClick: () -> Unit
) {
    InputChip(
        selected = selected,
        modifier = modifier,
        onClick = onClick,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        label = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp, vertical = 6.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall,
                text = option.name
            )
        })
}
