import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.unitest.Indicator
import com.example.unitest.MainViewModel
import com.example.unitest.RangedNumberInputField
import com.example.unitest.ui.theme.UniTestTheme
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: MainViewModel,
    language: String
) {
    val appLocale = koinInject<String>(named("AppLocale"))
    val indicatorsData: Pair<List<Indicator>, List<Indicator>> =
        koinInject { parametersOf(appLocale) }

    HomeContent { validAge ->
        if (validAge != null) {
            viewModel.userAge = validAge
            if (validAge > 18) {
                val adultIndicators = indicatorsData.first
                viewModel.enableAdultForm(adultIndicators)
                navController.navigate("adult")
            } else {
                val teenIndicators = indicatorsData.second
                viewModel.enableTeenForm(teenIndicators)
                navController.navigate("teen")
            }
        }
    }
}

@Composable
fun HomeContent(onSubmit: (validAge: Int?) -> Unit) {
    var inputAge by remember { mutableStateOf("") }
    var validAge by remember { mutableStateOf<Int?>(null) }

    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RangedNumberInputField(
                value = inputAge,
                onValueChange = { inputAge = it },
                onNumberInRangeChanged = { validAge = it },
                label = "Age",
                minRange = 1,
                maxRange = 99,
                modifier = Modifier.width(256.dp)
            )
            UButton(label = "Submit", onClick = { onSubmit(validAge) })
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.PHONE)
@Composable
fun PreviewHomeContent() {
    UniTestTheme {
        HomeContent { }
    }
}
