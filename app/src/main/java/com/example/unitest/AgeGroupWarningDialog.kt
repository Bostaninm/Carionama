import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.unitest.R

@Composable
fun AgeGroupWarningDialog(onClose: () -> Unit, onProceed: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .zIndex(1f)
    ) {
        Surface(
            shadowElevation = 8.dp,
            shape = ShapeDefaults.Medium,
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .padding(horizontal = 24.dp)
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .weight(1f)
                        .background(MaterialTheme.colorScheme.error),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.fillMaxSize(0.75f),
                        imageVector = Icons.Rounded.Warning,
                        tint = MaterialTheme.colorScheme.onError,
                        contentDescription = stringResource(R.string.warning_sign)
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(horizontal = 12.dp)
                ) {
                    Column(modifier = Modifier.align(Alignment.Center)) {
                        Text(
                            stringResource(R.string.warning_for_age_group_18_35),
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.error,
                            modifier = Modifier
                                .padding(bottom = 4.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            stringResource(R.string.age_group_young_warning),
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Justify,
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(end = 16.dp, bottom = 12.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onClose) {
                        Text(stringResource(R.string.cancel))
                    }
                    Spacer(Modifier.padding(8.dp))
                    UButton(
                        label = stringResource(R.string.proceed),
                        vPadding = 0.dp,
                        onClick = onProceed
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AgeGroupWarningDialogPreview() {
    AgeGroupWarningDialog({}, {})
}
