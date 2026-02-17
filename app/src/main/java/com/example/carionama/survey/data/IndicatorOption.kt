package com.example.carionama.survey.data

data class IndicatorOption(
    val id: String,
    val description: String,
    val value: Float,
    val relations: List<Relation>
)

