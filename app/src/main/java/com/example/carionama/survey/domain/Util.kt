package com.example.carionama.survey.domain

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.ui.graphics.Color
import com.example.carionama.survey.components.piechart.data.ChartView
import com.example.carionama.survey.data.Indicator
import com.example.carionama.survey.data.IndicatorCategory
import com.example.carionama.survey.data.IndicatorLevel.SECOND
import com.example.carionama.survey.data.IndicatorLevel.THIRD
import com.example.carionama.survey.data.IndicatorOption
import com.example.carionama.survey.data.IndicatorView
import com.example.carionama.survey.data.RiskLevel
import com.himanshoe.charty.common.asSolidChartColor

object Util {

    fun getAvailableBMIOptionByValue(bmiIndicator: Indicator, value: Float): IndicatorOption {
        return if (value < 18.5) {
            bmiIndicator.options[0]
        } else {
            bmiIndicator.options[1]
        }
    }

    fun getAvailableAgeOptionByValue(ageIndicator: Indicator, userAge: Int): IndicatorOption {
        if (userAge in 12..99) {
            val options = ageIndicator.options
            for (i in options.indices) {
                val currentOption = options[i]
                val lowerBound =
                    currentOption.description.toIntOrNull() ?: continue // Skip if not a number

                val isFirstOption = (i == 0)
                val isLastOption = (i == options.size - 1)

                if (isLastOption) {
                    if (userAge >= lowerBound) {
                        return currentOption
                    }
                } else if (isFirstOption) {
                    val nextOption = options[i + 1]
                    val upperBoundExclusive =
                        nextOption.description.toIntOrNull() ?: continue // Skip if not a number

                    if (userAge < upperBoundExclusive) {
                        return currentOption
                    }
                } else {
                    val nextOption = options[i + 1]
                    val upperBoundExclusive =
                        nextOption.description.toIntOrNull() ?: continue // Skip if not a number

                    if (userAge in lowerBound until upperBoundExclusive) {
                        return currentOption
                    }
                }
            }
        }
        error("No option was valid for the entered age: $userAge")
    }

    val ColorPallet = listOf(
        Color.hsl(131f, 0.45f, 0.43f),
        Color.hsl(271f, 0.56f, 0.43f),
        Color.hsl(345f, 0.53f, 0.55f),
        Color.hsl(44f, 0.72f, 0.62f),
        Color.hsl(218f, 0.56f, 0.52f),
        Color.hsl(199f, 0.100f, 0.18f),
        Color.hsl(248f, 0.28f, 0.43f),
        Color.hsl(324f, 0.45f, 0.53f),
        Color.hsl(1f, 0.100f, 0.69f),
        Color.hsl(39f, 0.100f, 0.50f),
        Color.hsl(212f, 0.62f, 0.64f),
        Color.hsl(120f, 0.60f, 0.67f),
        Color.hsl(3f, 0.100f, 0.69f)
    )

    fun calculateSynergy(currentSelection: IndicatorView, selections: List<IndicatorView>): Float {
        var addedSynergy = 0f
        val relations = currentSelection.selection?.relations
        Log.d("calculateSynergy", "Calculating for ${currentSelection.indicator.name}")
        for (selection in selections) {
            if (selection.indicator.id != currentSelection.indicator.id) {
                addedSynergy += relations?.find { it.id == selection.indicator.id }?.effect?.find { it.first == selection.selection?.id }?.second
                    ?: 0f
                Log.d("calculateSynergy", "     ${selection.indicator.name} :: $addedSynergy")
            }
        }
        Log.d("calculateSynergy", "Calculation result : $addedSynergy")

        return addedSynergy

    }

    @SuppressLint("DefaultLocale")
    fun calculateChartOnIndicators(
        indicatorViews: List<IndicatorView>, indicatorCategory: List<IndicatorCategory>
    ): List<ChartView> {
//        val indicators = indicatorViews.map {
//            if (it.selection != null)
//                Selection(it.indicator.id, it.indicator.name, it.selection)
//            else
//                error("No Selection for ${it.indicator.id}")
//        }

        var sum = 0f
        var riskFactor = 0f
        val actualValues = mutableMapOf<String, Float>()

        for (view in indicatorViews) {
            val actualValue = (view.selection?.value ?: 0f) + calculateSynergy(view, indicatorViews)
            sum += actualValue
            if (actualValue > 0) riskFactor += actualValue
            actualValues[view.indicator.id] = actualValue
        }

        val size = indicatorViews.size
        val pieChartLevels = mutableMapOf<String, MutableList<ChartView>>()
        indicatorViews.forEachIndexed { index, view ->
            val actualValue = actualValues[view.indicator.id] ?: 0f
            if (actualValue > 0) {
                val hFraction: Float = (actualValue / riskFactor) * 100
                val flInd = calculateLevel(view.indicator.id, indicatorCategory)
                val color = calculateColor(
                    flInd, index, size
                ).asSolidChartColor()

                val nameAndFraction =
                    "${view.indicator.name} - ${String.format("%.1f", hFraction)}%"
                pieChartLevels.getOrPut(flInd?.name ?: "other") { mutableListOf() }.add(
                    ChartView(
                        id = view.indicator.id,
                        centralText = nameAndFraction,
                        subCentralText = view.indicator.suggestion,
                        legendLabel = nameAndFraction,
                        value = actualValue,
                        color = color,
                        labelColor = color,
                        label = view.indicator.id,
                        level = view.level
                    )
                )

            }

        }
        val chartViews = mutableListOf<ChartView>()
        for (levels in pieChartLevels) {
            chartViews += levels.value.sortedByDescending { it.value }
        }
        return chartViews
    }

    fun calculateLevel(
        indicatorId: String, indicatorCategory: List<IndicatorCategory>
    ): IndicatorCategory? {
        val flInd = indicatorCategory.find { it.relatedIndicators.contains(indicatorId) }
        return flInd
    }

    fun calculateColor(indicatorCategory: IndicatorCategory?, position: Int, size: Int): Color {
        return indicatorCategory?.hslRange?.smartColor(position, size) ?: ColorPallet.get(
            position % 12
        )
    }

    fun calculateRisk(
        indicatorViews: List<ChartView>, l2Multiplier: Float, l3Multiplier: Float
    ): Float {
        var total = 0f
        indicatorViews.forEach { view ->
            total += when (view.level) {
                SECOND -> {
                    Log.d(
                        "RiskScore",
                        "name:${view.id} multi:$l2Multiplier riskScore:${view.value} calculatedRisk:${view.value * l2Multiplier}"
                    )
                    view.value * l2Multiplier
                }

                THIRD -> {
                    Log.d(
                        "RiskScore",
                        "name:${view.id} multi:$l3Multiplier riskScore:${view.value} calculatedRisk:${view.value * l3Multiplier}"
                    )
                    view.value * l3Multiplier
                }
            }
        }
        Log.d("RiskScore", "total Risk: ${total}")
        return total
    }

    fun calculateRiskPercentileAndLevel(
        riskScore: Float,
        riskLevels: List<RiskLevel>
    ): Pair<Float, String> {
        var max = 0f
        var label = ""
        riskLevels.forEach { rl ->
            if (riskScore in rl.scoreRange) {
                label = rl.label
            }
            max = maxOf(rl.scoreRange.endInclusive, max)
        }
        val percentile = (riskScore / max) * 100
        return Pair(percentile, label)

    }

}