package com.example.carionama.survey.components.piechart.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.carionama.theme.CarionamaTheme

@Composable
fun PieChartLegend(
    modifier: Modifier = Modifier, data: Map<String, Int>, colors: List<Color>
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // create the data items
        data.values.forEachIndexed { index, value ->
            DetailsPieChartItem(
                data = Pair(data.keys.elementAt(index), value),
                color = colors[index],
                height = 12.dp
            )
        }

    }
}

@Composable
fun PieChartLegend(
    modifier: Modifier = Modifier, data: List<String>, colors: List<Color>
) {
    PieChartLegend(modifier, data = data.associate { it to 1 }, colors = colors)
}

@Composable
fun DetailsPieChartItem(
    data: Pair<String, Int>, height: Dp = 45.dp, color: Color
) {

    Surface(
        modifier = Modifier.padding(vertical = 2.dp), color = Color.Transparent
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .background(
                        color = color, shape = RoundedCornerShape(percent = 33)
                    )
                    .size(height)
            )

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = data.first,
                    style = MaterialTheme.typography.labelMedium

                )
//                Text(
//                    modifier = Modifier.padding(start = 15.dp),
//                    text = data.second.toString(),
//                    style = MaterialTheme.typography.labelSmall
//
//                )
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPieChartLegend() {
    CarionamaTheme {
        val colors = listOf(
            Color.Red, Color.Blue, Color.Green, Color.Magenta
        )
        val data = mapOf(
            Pair("Red", 1), Pair("Blue", 1), Pair("Green", 2), Pair("Purple", 3)
        )

        PieChartLegend(Modifier, data, colors)
    }
}
