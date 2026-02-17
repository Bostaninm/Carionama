package com.example.carionama.survey.domain

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.ui.graphics.Color
import com.example.carionama.survey.components.piechart.data.ChartData
import com.example.carionama.survey.data.Indicator
import com.example.carionama.survey.data.IndicatorCategory
import com.example.carionama.survey.data.IndicatorOption
import com.example.carionama.survey.data.IndicatorView
import com.example.carionama.survey.data.Selection
import com.himanshoe.charty.common.asSolidChartColor
import com.himanshoe.charty.pie.model.PieChartData
import kotlin.math.max
import kotlin.random.Random

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
    fun calculateChartOnIndicators(
        indicatorViews: List<IndicatorView>,
        indicatorCategory: List<IndicatorCategory>
    ): ChartData {
        val indicators = indicatorViews.map {
            if(it.selection != null)
                Selection(it.indicator.id, it.indicator.name, it.selection)
            else
                error("No Selection for ${it.indicator.id}")
        }
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

        val pieChartLevels = mutableMapOf<String, MutableList<PieChartData>>()
        for (indicator in indicators) {
            var actualValue =
                indicator.indicatorOption.value + calculateSynergy(indicator, indicators)

            if (actualValue > 0) {
                val hFraction: Float = (actualValue / riskFactor) * 100
                val flInd = calculateLevel(indicator.indicatorId, indicatorCategory)
                val color = calculateColor(
                    flInd
                ).asSolidChartColor()

                pieChartLevels.getOrPut(flInd?.id ?: "other") { mutableListOf() }.add(
                    PieChartData(
                        actualValue,
                        color,
                        labelColor = color,
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
        val pieChartData = mutableListOf<PieChartData>()
        for (levels in pieChartLevels) {
            pieChartData += levels.value.sortedByDescending { it.value }
        }
        return ChartData(pieChartData)
    }

    fun calculateLevel(
        indicatorId: String,
        indicatorCategory: List<IndicatorCategory>
    ): IndicatorCategory? {
        val flInd = indicatorCategory.find { it.relatedIndicators.contains(indicatorId) }
        return flInd
    }

    fun calculateColor(indicatorCategory: IndicatorCategory?): Color {
        return indicatorCategory?.hslRange?.randomColor() ?: ColorPallet.get(
            Random.nextInt(
                0,
                11
            )
        )

    }

    fun calculatePercentile(data: List<PieChartData>?, references: List<Indicator>?): Float {
        if (references == null)
            return 0f

        var total = 0f
        var maxTotal = 0f
        for (r in references) {
            var maxForOption = 0f
            for (o in r.options) {
                val currentBase = o.value
                var maxRelationSynergy = 0f
                for (rel in o.relations) {
                    var maxEffect = 0f
                    for (e in rel.effect) {
                        maxEffect = max(maxRelationSynergy, e.second)
                    }
                    maxRelationSynergy += maxEffect
                }
                maxForOption = max(maxForOption, maxRelationSynergy + currentBase)
            }
            maxTotal += maxForOption
        }

        data?.forEach { total += it.value }

        return total / maxTotal * 100
    }

}