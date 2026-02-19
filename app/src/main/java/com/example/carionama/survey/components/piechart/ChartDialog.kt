@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.carionama.survey.components.piechart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.carionama.common.component.UButton
import com.example.carionama.survey.components.piechart.components.PieChartLegend
import com.example.carionama.survey.components.piechart.components.VerticalProgress
import com.example.carionama.survey.components.piechart.data.ChartData
import com.example.carionama.survey.data.IndicatorCategory
import com.example.carionama.theme.CarionamaTheme
import com.himanshoe.charty.common.asSolidChartColor
import com.himanshoe.charty.pie.PieChart
import com.himanshoe.charty.pie.model.PieChartData
import kotlin.math.ceil


@Composable
fun ChartDialog(
    indicatorCategories: List<IndicatorCategory>,
    secondLevelChartData: ChartData,
    thirdLevelChartData: ChartData,
    percentile: Float,
    onChartDialogDismiss: () -> Unit
) {
    val colorCode = remember { indicatorCategories.map { Pair(it.id, it.hslRange.randomColor()) } }
    var tabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Second Level", "Third Level")

    val decileLabel = "${ceil(percentile / 10).toInt()}th Decile"

    Dialog(
        properties = DialogProperties(
            decorFitsSystemWindows = true, usePlatformDefaultWidth = false
        ), onDismissRequest = onChartDialogDismiss
    ) {
        Surface(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 16.dp)
                .clip(MaterialTheme.shapes.medium)
//                .padding(horizontal = 16.dp, vertical = 64.dp)
        ) {
            Column(
                modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PrimaryTabRow(selectedTabIndex = tabIndex) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            text = { Text(title) },
                            selected = tabIndex == index,
                            onClick = { tabIndex = index })
                    }
                }
                when (tabIndex) {
                    0 -> {
                        if (secondLevelChartData.pieChartData.isNotEmpty()) {
                            ChartContent(
                                Modifier.weight(0.98f),
                                secondLevelChartData,
                                colorCode,
                                percentile,
                                decileLabel
                            )
                        } else {
                            EmptyChart()
                        }
                    }

                    1 -> {
                        if (thirdLevelChartData.pieChartData.isNotEmpty()) {
                            ChartContent(
                                Modifier.weight(0.92f),
                                thirdLevelChartData,
                                decilePercentile = percentile,
                                decileLabel = decileLabel
                            )
                        } else {
                            EmptyChart()
                        }
                    }
                }
                UButton(
                    label = "Close", onClick = onChartDialogDismiss
                )
            }
        }
    }
}

@Composable
fun EmptyChart(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("There are no risk factor")
    }
}

@Composable
fun ChartContent(
    modifier: Modifier = Modifier,
    chartData: ChartData,
    colorCode: List<Pair<String, Color>>? = null,
    decilePercentile: Float = 0f,
    decileLabel: String = ""
) {
    var chartCentralText by remember { mutableStateOf("") }

    Surface(modifier = modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxHeight(0.85f)
                .fillMaxWidth()
                .padding(top = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
//                    .weight(1f)
                    .aspectRatio(1f)
                    .padding(horizontal = 32.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(0.75f)
                        .align(Alignment.Center)
                ) {
                    Text(
                        chartCentralText,
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                PieChart(
                    data = { chartData.pieChartData },
                    modifier = Modifier.fillMaxSize(1f),
                    isDonutChart = true,
                    onPieChartSliceClick = { slice ->
                        chartCentralText = slice.label
                    })
            }
            Row(
                Modifier
                    .fillMaxWidth(1f)
                    .weight(1f)
            ) {
                Column(
                    Modifier
                        .weight(7f)
                        .fillMaxHeight(), verticalArrangement = Arrangement.Center
                ) {
                    colorCode?.let {
                        PieChartLegend(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            colorCode.map { it.first },
                            colorCode.map { it.second },
                        )
                        HorizontalDivider(modifier = Modifier.padding(2.dp, 16.dp))
                    }

                    PieChartLegend(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        chartData.pieChartData.associate { it.label to it.value.toInt() },
                        chartData.pieChartData.map { it.color.value[0] })
                }
                Box(
                    Modifier
                        .weight(3f)
                        .fillMaxHeight(0.9f)
                        .align(Alignment.CenterVertically),
                    contentAlignment = Alignment.Center
                ) {
                    VerticalProgress(decilePercentile, Modifier, decileLabel)

                }

            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.PHONE)
@Composable
fun PreviewChartContent() {
    CarionamaTheme {
        val data = listOf(
            PieChartData(1f, Color.Red.asSolidChartColor(), label = "Red"),
            PieChartData(1f, Color.Blue.asSolidChartColor(), label = "Blue"),
            PieChartData(2f, Color.Green.asSolidChartColor(), label = "Green Color"),
            PieChartData(3f, Color.Magenta.asSolidChartColor(), label = "Purple Color of the gods"),
        )
        val colorCodes = listOf(
            Pair("MicroOrganisms", Color.Yellow),
            Pair("Host", Color.Green),
            Pair("Substrate", Color.Blue)
        )
        val chartData = ChartData(data)
        ChartContent(modifier = Modifier, chartData, colorCodes)

    }
}
