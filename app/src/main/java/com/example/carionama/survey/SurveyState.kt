package com.example.carionama.survey

import com.example.carionama.common.data.CarionamaLocals
import com.example.carionama.survey.components.piechart.data.ChartData
import com.example.carionama.survey.data.IndicatorCategory
import com.example.carionama.survey.data.IndicatorView

data class SurveyState(
    val indicatorCategories: List<IndicatorCategory> = emptyList(),
    val secondLevelChartData: ChartData = ChartData(),
    val thirdLevelChartData: ChartData = ChartData(),
    val indicatorViews: List<IndicatorView> = emptyList(),
    val currentLanguage: CarionamaLocals = CarionamaLocals.En,
    val languageMenuExpanded: Boolean = false,
    val showChartDialog: Boolean = false,
    val showWarningDialog: Boolean = false,
    val firstNullIndicatorName: String = ""
)
