package com.example.carionama.survey

import com.example.carionama.common.data.CarionamaLocals
import com.example.carionama.survey.data.IndicatorOption
import com.example.carionama.survey.data.IndicatorView

sealed interface SurveyAction {
    data class OnValidAgeNumberEntered(val ageView: IndicatorView, val validAge: Int?) :
        SurveyAction

    data class OnLanguageSelected(val language: CarionamaLocals) : SurveyAction
    object OnLanguageIconClicked : SurveyAction
    object OnLanguageMenuDismissed : SurveyAction
    data class OnBMIChange(
        val bmiView: IndicatorView,
        val height: Float,
        val weight: Float,
        val bmi: Float
    ) : SurveyAction

    data class OnIndicatorOptionSelected(
        val indicatorView: IndicatorView,
        val option: IndicatorOption
    ) :
        SurveyAction

    data object OnActionButtonClicked : SurveyAction
    data object OnChartDialogDismissed : SurveyAction
    data object OnDismissWarningDialog : SurveyAction
    data object OnGeneralWarningProceedClicked : SurveyAction


}