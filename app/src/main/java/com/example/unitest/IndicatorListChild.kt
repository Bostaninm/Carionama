import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.unitest.MainViewModel


@Composable
fun IndicatorListChild(
    navController: NavController,
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val indicators = viewModel.teenIndicators
    var selectedIndicator by remember { mutableStateOf(indicators.first()) }
    var sliderValue by remember { mutableStateOf(170f) }
    val scrollState = rememberScrollState()

    Box(
        Modifier
            .fillMaxSize()
    ) {
        if (viewModel.showIndicatorDialog) {
            IndicatorOptionSelectionDialog(selectedIndicator)
        }
        if (viewModel.showChartDialog) {
            ChartDialog(viewModel)
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier
                .fillMaxSize()
                .padding(12.dp)
                .verticalScroll(scrollState)
        ) {
            indicators.forEach { indicator ->
                when (indicator.id) {
                    "BMI" -> {
                        BMI(Modifier.padding(12.dp), { bmi -> viewModel.userBMI = bmi })
                    }

                    else -> {
                        Select(
                            indicator = indicator,
                            selected = viewModel.selection[indicator.name]
                        ) { option ->
                            viewModel.setSelection(indicator.name, option)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(32.dp)) // Add Spacer here
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp), // Add padding for the content within the Box
            contentAlignment = Alignment.BottomEnd // Align content to the bottom right
        ) {
            Button(
                onClick = {
                    if (viewModel.validateInputs()) {
                        viewModel.showChartDialog()
                    }
                },
                // No need for Modifier.align here as Box's contentAlignment handles it
                modifier = Modifier
                    .width(64.dp) // Standard FAB size
                    .height(64.dp),
                shape = CircleShape // Make the button round
            ) {
                Icon(
                    Icons.Filled.Done,
                    contentDescription = "Done",
                    modifier = Modifier.size(64.dp)
                )
            }
        }

    }
}
