package com.example.carionama.survey.components.piechart.components

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.max
import kotlin.math.min

@Composable
fun VerticalProgress(
    progressInput: Float, modifier: Modifier = Modifier, label: String
) {
    val progress = progressInput.coerceIn(0.001f, 99.99f)
    val mProgress = animateFloatAsState(targetValue = progress / 100)
    val backGroundBrush = listOf(
        Color(0xFFB71C1C),   // Dark red (wrong) - top
        Color(0xFFD50000),   // Deep red
        Color(0xFFFF6D00),   // Orange
        Color(0xFFFFD600),   // Yellow midpoint
        Color(0xFF64DD17),   // Lime green
        Color(0xFF00C853),   // Vibrant green
        Color(0xFF00C853)    // Vibrant green (correct) - bottom
    )
    val cut = min(max((backGroundBrush.size * progress / 100).toInt(), 1), backGroundBrush.size)
    val barBg = if (cut >= backGroundBrush.size) backGroundBrush else backGroundBrush.asReversed()
        .subList(0, cut + 1).asReversed()

    Log.d("Colors", cut.toString())
    Log.d("Colors", barBg.toString())

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Column(
            modifier = modifier
                .weight(90f)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.LightGray)
                .width(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1 - mProgress.value)
                    .fillMaxWidth()
            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .weight(mProgress.value)
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            barBg
                        )
                    )

            )
        }
        Box(
            modifier = Modifier
                .weight(10f)
                .padding(4.dp), contentAlignment = Alignment.Center
        ) {
            Text(label, style = MaterialTheme.typography.labelSmall)

        }

    }

}

@Preview(showBackground = true, showSystemUi = true, device = Devices.PHONE)
@Composable
fun VerticalProgressPreview() {

    VerticalProgress(60f, Modifier, "My Label")


}