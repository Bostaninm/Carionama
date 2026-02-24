package com.example.carionama.survey

import com.example.carionama.common.data.CarionamaLocals
import com.example.carionama.survey.components.piechart.data.ChartView
import com.example.carionama.survey.data.CarionamaMetadata
import com.example.carionama.survey.data.IndicatorCategory
import com.example.carionama.survey.data.IndicatorView

sealed interface CarionamaUiState {
    data object Loading : CarionamaUiState
    data object ShowInitialWarning : CarionamaUiState
    data object Ready : CarionamaUiState
}

data class SurveyState(
    val uiState: CarionamaUiState = CarionamaUiState.Loading,
    val metadata: CarionamaMetadata = CarionamaMetadata(),
    val indicatorCategories: List<IndicatorCategory> = emptyList(),
    val secondLevelChartView: List<ChartView> = emptyList(),
    val thirdLevelChartView: List<ChartView> = emptyList(),
    val indicatorViews: List<IndicatorView> = emptyList(),
    val currentLanguage: CarionamaLocals = CarionamaLocals.En,
    val languageMenuExpanded: Boolean = false,
    val showChartDialog: Boolean = false,
    val showWarningDialog: Boolean = false,
    val firstNullIndicatorName: String = "",
    val riskPercentile: Float = 0f,
    val riskLabel: String = "",
    val warningMessage: String = "",
    val warningTitle: String = "",
    // Business Logic?
    // BMI
    val currentHeight: Float = 160f,
    val currentWeight: Float = 70f,
    // AgeGroup
    val currentAge: Int = 30,

    )

fun SurveyState.getBMI(): Float {
    return currentWeight * 10000f / (currentHeight * currentHeight)
}
