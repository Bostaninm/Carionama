import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun UButton(modifier: Modifier = Modifier, label: String = "", onClick: () -> Unit) {
    Button(
        modifier = modifier.padding(vertical = 12.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(percent = 15),
        onClick = onClick
    ) {
        Text(label)
    }
}
