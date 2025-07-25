import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.unitest.ChartData
import com.example.unitest.MainViewModel
import com.example.unitest.common.component.PieChartLegend
import com.himanshoe.charty.common.asSolidChartColor
import com.himanshoe.charty.pie.PieChart
import com.himanshoe.charty.pie.model.PieChartData


@Composable
fun ChartDialog(viewModel: MainViewModel) {
    val chartData =
        viewModel.chartData ?: error("Char dialog was show when chartData was not assigned")

    Dialog(
        properties = DialogProperties(
            decorFitsSystemWindows = false,
            usePlatformDefaultWidth = false
        ), onDismissRequest = { viewModel.closeChartDialog() }) {
        Surface(
            modifier = Modifier
                .padding(horizontal = 12.dp)
        ) {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ChartContent(chartData)
                UButton(label = "Close", onClick = { viewModel.closeChartDialog() })
            }
        }
    }
}

@Composable
fun ChartContent(chartData: ChartData) {
    var chartCentralText by remember { mutableStateOf("") }
    Surface(modifier = Modifier) {
        Column(
            Modifier
                .fillMaxHeight(0.85f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
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
                        textAlign = _root_ide_package_.androidx.compose.ui.text.style.TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                PieChart(
                    data = { chartData.pieChartData },
                    modifier = Modifier.fillMaxSize(1f),
                    isDonutChart = true,
                    onPieChartSliceClick = { slice ->
                        chartCentralText = slice.label
                    }
                )
            }
            PieChartLegend(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                chartData.pieChartData.associate { it.label to it.value.toInt() },
                chartData.pieChartData.map { it.color.value[0] })
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.PHONE)
@Composable
fun PreviewChartContent() {
    val data = listOf(
        PieChartData(1f, Color.Red.asSolidChartColor(), label = "Red"),
        PieChartData(1f, Color.Blue.asSolidChartColor(), label = "Blue"),
        PieChartData(2f, Color.Green.asSolidChartColor(), label = "Green Color"),
        PieChartData(3f, Color.Magenta.asSolidChartColor(), label = "Purple Color of the gods"),
    )
    val chartData = ChartData(data)
    ChartContent(chartData)
}
