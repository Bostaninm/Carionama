package com.example.carionama.survey.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.carionama.survey.data.Indicator
import com.example.carionama.survey.data.IndicatorOption
import com.example.carionama.theme.CarionamaTheme

@Composable
fun Select(
    modifier: Modifier = Modifier,
    indicator: Indicator,
    selected: IndicatorOption?,
    onSelect: (option: IndicatorOption) -> Unit
) {
    Card(
        modifier = modifier,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        colors = CardDefaults.cardColors().copy(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground
        )
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
            Text(
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                text = indicator.description,
                textAlign = TextAlign.Justify,
                style = MaterialTheme.typography.bodySmall
            )

            if (indicator.options.size <= 3) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min)
                        .padding(top = 8.dp)
                ) {
                    indicator.options.forEach { option ->
                        Option(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(1f),
                            option = option,
                            onClick = { onSelect(option) },
                            selected = (option == selected)
                        )
                    }
                }
            } else {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min)
                        .padding(top = 8.dp)
                ) {
                    indicator.options.forEach { option ->
                        Option(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(1f),
                            option = option,
                            onClick = { onSelect(option) },
                            selected = (option == selected)
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SelectPreview() {
    CarionamaTheme {
        val indicator = Indicator(
            "SES",
            "Socioeconomic Status (SES)",
            "Please select your socioeconomic group based on income quintiles (Quintile 5 = Wealthiest).",
            listOf(
                IndicatorOption("0", "Wealthiest (Quintile 5)", 0f, relations = listOf()),
                IndicatorOption("1", "Quintile 4", 1.234f, relations = listOf()),
//                IndicatorOption("Quintile 3 (Average)", 2.098f),
//                IndicatorOption("Quintile 2", 2.919f),
//                IndicatorOption("Poorest (Quintile 1)", 3.933f)
            )
        )
        var selected by remember { mutableStateOf<IndicatorOption?>(null) }
        Select(indicator = indicator, selected = selected) {
            selected = it
        }
    }
}
