import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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

@Preview(showBackground = true)
@Composable
fun BMIDemo() {
    BMI {}
}


@SuppressLint("DefaultLocale")
@Composable
fun BMI(modifier: Modifier = Modifier, setBMI: (Float) -> Unit) {
    var height by remember { mutableStateOf(170f) }
    var weight by remember { mutableStateOf(70f) }
    val bmi = weight * 10000f / (height * height)
    Column(modifier) {
        LabeledSlider(
            defaultValue = 160f,
            valueRange = 50f..220f,
            steps = 169,
            label = "Height",
            onValueChangeFinished = { setBMI(bmi) }
        ) { value ->
            height = value
        }

        LabeledSlider(
            defaultValue = 70f,
            valueRange = 20f..150f,
            steps = 129,
            label = "Weight",
            onValueChangeFinished = { setBMI(bmi) }
        ) { value ->
            weight = value
        }
        Row(
            Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .weight(0.2f)
                    .padding(end = 4.dp),
                textAlign = TextAlign.Start,
                text = "BMI",
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .weight(0.7f)
                    .padding(vertical = 4.dp, horizontal = 4.dp)
                    .border(
                        1.dp,
                        color = MaterialTheme.colorScheme.outline,
                        shape = RoundedCornerShape(percent = 25)
                    )
                    .padding(vertical = 8.dp),
                textAlign = TextAlign.Center,
                text = String.format("%.1f", bmi)
            )
            Spacer(
                Modifier
                    .weight(0.1f)
                    .padding(start = 4.dp)
            )
        }

    }
}
