package com.example.carionama.survey.data

data class IndicatorView(
    val indicator: Indicator,
    val selection: IndicatorOption?,
    val level: IndicatorLevel
)

enum class IndicatorLevel {
    SECOND,
    THIRD
}