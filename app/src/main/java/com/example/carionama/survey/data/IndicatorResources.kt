package com.example.carionama.survey.data

import com.example.carionama.common.data.CarionamaLocals


data class IndicatorInfo(
    val indicatorCategoryResource: IndicatorCategoryResource = IndicatorCategoryResource(),
    val secondLevelIndicatorResource: IndicatorResource = IndicatorResource(),
    val thirdLevelIndicatorResource: IndicatorResource = IndicatorResource()
)

data class IndicatorResource(val indicators: Map<CarionamaLocals, List<Indicator>> = emptyMap())
data class IndicatorCategoryResource(val category: Map<CarionamaLocals, List<IndicatorCategory>> = emptyMap())

fun IndicatorResource.getResource(local: CarionamaLocals): List<Indicator>? {
    return indicators[local]
}

fun IndicatorCategoryResource.getResource(local: CarionamaLocals): List<IndicatorCategory>? {
    return category[local]
}

fun IndicatorInfo.getResource(local: CarionamaLocals): Triple<List<IndicatorCategory>, List<Indicator>, List<Indicator>>? {
    val categories = this.indicatorCategoryResource.getResource(local) ?: return null
    val secondLevelIndicators = this.secondLevelIndicatorResource.getResource(local) ?: return null
    val thirdLevelIndicators = this.thirdLevelIndicatorResource.getResource(local) ?: return null
    return Triple(categories, secondLevelIndicators, thirdLevelIndicators)
}