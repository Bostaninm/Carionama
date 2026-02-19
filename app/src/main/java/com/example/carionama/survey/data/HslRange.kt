package com.example.carionama.survey.data

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

data class ValueRange(val min: Int, val max: Int) {
    fun randomValue(): Float = Random.nextInt(min, max + 1).toFloat()
    fun percentileInRange(percentage: Float): Float {
        val p = percentage.coerceIn(0f, 1f)
        return (min + (max - min) * p)
    }
}

data class HslRange(
    val hue: ValueRange,
    val saturation: ValueRange,
    val lightness: ValueRange
) {
    // Simple random color
    fun randomColor(): Color {
        return Color.hsl(
            hue = hue.randomValue(),
            saturation = saturation.randomValue() / 100,
            lightness = lightness.randomValue() / 100
        )
    }

    fun smartColor(position: Int, size: Int): Color {
        val percentage = position.toFloat() / (size - 1)
        return Color.hsl(
            hue = hue.percentileInRange(percentage),
            saturation = saturation.percentileInRange(percentage) / 100,
            lightness = lightness.percentileInRange(percentage) / 100
        )
    }

    // Biased random (favors certain values)
    fun randomColorBiased(
        hueBias: Float = 0.5f,
        saturationBias: Float = 0.5f,
        lightnessBias: Float = 0.5f
    ): Color {
        return Color.hsl(
            hue = biasedRandom(hue, hueBias),
            saturation = biasedRandom(saturation, saturationBias) / 100,
            lightness = biasedRandom(lightness, lightnessBias) / 100
        )
    }

    private fun biasedRandom(range: ValueRange, bias: Float): Float {
        val width = (range.max - range.min).toFloat()
        val offset = Random.nextFloat() * width * bias
        return range.min + offset
    }
}
