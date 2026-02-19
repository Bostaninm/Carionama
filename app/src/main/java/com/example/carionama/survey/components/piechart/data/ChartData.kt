package com.example.carionama.survey.components.piechart.data

import com.himanshoe.charty.pie.model.PieChartData

data class ChartData(val pieChartData: List<PieChartData> = emptyList()) {
    operator fun plus(other: ChartData): ChartData {
        return ChartData(this.pieChartData + other.pieChartData)
    }
}
