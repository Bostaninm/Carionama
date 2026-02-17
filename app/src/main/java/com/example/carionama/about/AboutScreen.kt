package com.example.carionama.about

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.carionama.R
import com.example.carionama.common.component.UButton
import com.example.carionama.theme.CarionamaTheme


@Composable
fun AboutScreen(onBackPressed: () -> Unit) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Surface(Modifier.padding(12.dp), shadowElevation = 4.dp, shape = ShapeDefaults.Medium) {
            Column(Modifier.padding(top = 24.dp, bottom = 4.dp)) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                    Text(
                        stringResource(R.string.about_this_app),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Text(
                    stringResource(R.string.about_page_description),
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(
                        top = 16.dp,
                        bottom = 8.dp,
                        start = 16.dp,
                        end = 12.dp
                    )
                )
                UButton(
                    Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(horizontal = 12.dp)
                        .fillMaxWidth(),
                    label = stringResource(R.string.back)
                ) {
                    onBackPressed()
                }
            }
        }

    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AboutScreenPreview() {
    CarionamaTheme {
        AboutScreen { }
    }
}
