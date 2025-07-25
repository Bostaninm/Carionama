import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.unitest.Indicator
import com.example.unitest.MainViewModel

@Composable
fun IndicatorOptionSelectionDialog(indicator : Indicator) {
    val viewModel = viewModel<MainViewModel>()

    Dialog(onDismissRequest = {viewModel.closeIndicatorDialog()}) {
        LazyColumn {
            items(indicator.options) { indicatorOption ->
                Button(onClick = {
                    viewModel.setSelection(indicator.name, indicatorOption)
                    viewModel.closeIndicatorDialog()
                }) {
                    Text(indicatorOption.name)
                }
            }
        }
    }
}
