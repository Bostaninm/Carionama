import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.unitest.ui.theme.UniTestTheme

@Preview(showBackground = true)
@Composable
fun BasicSlider() {
    UniTestTheme {
        LabeledSlider(
            defaultValue = 160f, valueRange = 50f..220f, steps = 169, label = "Height"
        ) {

        }
    }
}

@SuppressLint("DefaultLocale")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LabeledSlider(
    modifier: Modifier = Modifier,
    defaultValue: Float = 0f,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 0,
    label: String = "",
    onValueChangeFinished: () -> Unit = {},
    onValueChange: (value: Float) -> Unit
) {
    var sliderValue by remember { mutableStateOf(defaultValue) }

    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        Text(
            text = label, modifier = Modifier
                .padding(end = 2.dp)
                .weight(0.2f)
        )
        Slider(
            value = sliderValue,
            onValueChange = {
                sliderValue = it
                onValueChange(it)
            },
            valueRange = valueRange,
            steps = steps,
            thumb = { TransparentCircularThumb(thumbSize = 24.dp) },
            track = { CustomTrack(it) },
            modifier = Modifier.weight(0.7f),
            onValueChangeFinished = onValueChangeFinished
        )
        Text(
            modifier = Modifier
                .padding(start = 2.dp)
                .weight(0.1f), textAlign = TextAlign.End
//                .background(Color.LightGray, shape = RoundedCornerShape(percent = 50))
//                .padding(vertical = 2.dp, horizontal = 12.dp)
            , text = String.format("%d", sliderValue.toInt())
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTrack(sliderState: SliderState) {

    // Calculate fraction of the slider that is active
    val fraction by remember {
        derivedStateOf {
            (sliderState.value - sliderState.valueRange.start) / (sliderState.valueRange.endInclusive - sliderState.valueRange.start)
        }
    }

    Box(Modifier.fillMaxWidth()) {
        Box(
            Modifier
                .fillMaxWidth(fraction)
                .align(Alignment.CenterStart)
                .height(6.dp)
                .background(MaterialTheme.colorScheme.primary, CircleShape)
        )
        Box(
            Modifier
                .fillMaxWidth(1f - fraction)
                .align(Alignment.CenterEnd)
                .height(3.dp)
                .background(
                    MaterialTheme.colorScheme.inversePrimary,
                    CircleShape
                )
        )
    }
}

@Composable
fun TransparentCircularThumb(
    thumbSize: Dp = 24.dp, color: Color = MaterialTheme.colorScheme.secondaryContainer

) {
    Box(
        Modifier
            .size(thumbSize)
            .background(
                color = color, shape = CircleShape
            ),
        contentAlignment = Alignment.Center,
    ) {}
}
