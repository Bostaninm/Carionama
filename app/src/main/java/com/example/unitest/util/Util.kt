package com.example.unitest.util

import android.annotation.SuppressLint
import androidx.compose.ui.graphics.Color
import com.example.unitest.ChartData
import com.example.unitest.IndicatorOption
import com.himanshoe.charty.common.asSolidChartColor
import com.himanshoe.charty.pie.model.PieChartData

object Util {
    val ColorPallet = listOf(
        Color.hsl(131f, 0.45f, 0.43f).asSolidChartColor(),
        Color.hsl(271f, 0.56f, 0.43f).asSolidChartColor(),
        Color.hsl(345f, 0.53f, 0.55f).asSolidChartColor(),
        Color.hsl(44f, 0.72f, 0.62f).asSolidChartColor(),
        Color.hsl(218f, 0.56f, 0.52f).asSolidChartColor(),
        Color.hsl(199f, 0.100f, 0.18f).asSolidChartColor(),
        Color.hsl(248f, 0.28f, 0.43f).asSolidChartColor(),
        Color.hsl(324f, 0.45f, 0.53f).asSolidChartColor(),
        Color.hsl(1f, 0.100f, 0.69f).asSolidChartColor(),
        Color.hsl(39f, 0.100f, 0.50f).asSolidChartColor(),
        Color.hsl(212f, 0.62f, 0.64f).asSolidChartColor(),
        Color.hsl(120f, 0.60f, 0.67f).asSolidChartColor(),
        Color.hsl(3f, 0.100f, 0.69f).asSolidChartColor(),
    )

    @SuppressLint("DefaultLocale")
    fun calculateChartOnIndicators(indicators: Map<String, IndicatorOption>): ChartData {
        var sum = 0f
        var riskFactor = 0f
        for (indicator in indicators) {
            sum += indicator.value.value
            if (indicator.value.value > 0)
                riskFactor += indicator.value.value
        }
        val base = 10
        val DMFT = sum + base

        val pieChartData = buildList {
            var i = 0
            for (indicator in indicators) {
                if (indicator.value.value > 0) {
                    val hFraction: Float = (indicator.value.value / riskFactor) * 100
                    i = i % 12

                    add(
                        PieChartData(
                            indicator.value.value,
                            ColorPallet[i],
                            labelColor = ColorPallet[i++],
                            label = "${indicator.key} - ${String.format("%.1f",hFraction)}%"
                        )
                    )
                }
            }
        }
        return ChartData(pieChartData)
    }
}
