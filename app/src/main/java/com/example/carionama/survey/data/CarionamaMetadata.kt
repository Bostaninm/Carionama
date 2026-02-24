package com.example.carionama.survey.data

import com.example.carionama.common.data.CarionamaLocals
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

data class LocalsCarionamaMetadata(
    val local: CarionamaLocals,
    val carionamaMetadata: CarionamaMetadata
)

data class CarionamaMetadata(
    val l2Multiplier: Float = 0f,
    val l3Multiplier: Float = 0f,
    val initialWarningMessage: String = "",
    val initialWarningTitle: String = "",
    val riskLevels: List<RiskLevel> = emptyList()
)

data class RiskLevel(
    val label: String, val scoreRange: ClosedFloatingPointRange<Float>, val description: String
)

class CarionamaMetadataDeserializer : JsonDeserializer<CarionamaMetadata> {
    override fun deserialize(
        json: JsonElement, typeOfT: Type, context: JsonDeserializationContext
    ): CarionamaMetadata {
        val jsonObject = json.asJsonObject

        val l2Multiplier = jsonObject.get("l2Multiplier").asFloat
        val l3Multiplier = jsonObject.get("l3Multiplier").asFloat
        val warningObject = jsonObject.getAsJsonObject("warningMessage")
        val initialWarningTitle = warningObject.get("title").asString
        val initialWarningMessage = warningObject.get("message").asString
        val riskArray = jsonObject.getAsJsonArray("riskCategories")
        val riskLevels = mutableListOf<RiskLevel>()
        riskArray.forEach { entry ->
            val riskObject = entry.asJsonObject
            val label = riskObject.get("label").asString
            val minScore = riskObject.get("minScore").asFloat
            val maxScore = riskObject.get("maxScore").asFloat
            val description = riskObject.get("description").asString
            riskLevels.add(
                RiskLevel(
                    label,
                    minScore..maxScore,
                    description,
                )
            )
        }

        return CarionamaMetadata(
            l2Multiplier, l3Multiplier, initialWarningMessage, initialWarningTitle, riskLevels
        )
    }
}
