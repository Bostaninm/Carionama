package com.example.carionama.survey.components.piechart.data

import androidx.compose.ui.graphics.Color
import com.example.carionama.survey.data.IndicatorLevel
import com.himanshoe.charty.common.ChartColor
import com.himanshoe.charty.common.asSolidChartColor
import com.himanshoe.charty.pie.model.PieChartData

data class ChartView(
    val id: String,
    val centralText: String,
    val subCentralText: String,
    val legendLabel: String,
    val value: Float,
    val color: ChartColor,
    val labelColor: ChartColor = Color.White.asSolidChartColor(),
    val label: String,
    val level: IndicatorLevel
) {
    fun getPieCharData(newLabel: String? = null): PieChartData {
        return PieChartData(value, color, labelColor, newLabel ?: label)
    }
}
