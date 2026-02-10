package com.example.unitest.util

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.ui.graphics.Color
import com.example.unitest.ChartData
import com.example.unitest.Selection
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

    fun calculateSynergy(currentSelection: Selection, selections: List<Selection>): Float {
        var addedSynergy = 0f
        val relations = currentSelection.indicatorOption.relations
        Log.d("calculateSynergy", "Calculating for ${currentSelection.indicatorName}")
        for (selection in selections) {
            if (selection.indicatorId != currentSelection.indicatorId) {
                addedSynergy += relations.find { it.id == selection.indicatorId }?.effect?.find { it.first == selection.indicatorOption.id }?.second
                    ?: 0f
                Log.d("calculateSynergy", "     ${selection.indicatorName} :: $addedSynergy")
            }
        }
        Log.d("calculateSynergy", "Calculation result : ${addedSynergy}")

        return addedSynergy

    }

    @SuppressLint("DefaultLocale")
    fun calculateChartOnIndicators(indicators: List<Selection>): ChartData {

        var sum = 0f
        var riskFactor = 0f

        for (indicator in indicators) {
            val actualValue =
                indicator.indicatorOption.value + calculateSynergy(indicator, indicators)
            sum += actualValue
            if (actualValue > 0)
                riskFactor += actualValue
        }
        val base = 10
        sum + base

        val pieChartData = buildList {
            var i = 0
            for (indicator in indicators) {
                var actualValue =
                    indicator.indicatorOption.value + calculateSynergy(indicator, indicators)

                if (actualValue > 0) {
                    val hFraction: Float = (actualValue / riskFactor) * 100
                    i = i % 12

                    add(
                        PieChartData(
                            actualValue,
                            ColorPallet[i],
                            labelColor = ColorPallet[i++],
                            label = "${indicator.indicatorName} - ${
                                String.format(
                                    "%.1f",
                                    hFraction
                                )
                            }%"
                        )
                    )
                }
            }
        }

        return ChartData(pieChartData.sortedByDescending { it -> it.value })
    }
}
