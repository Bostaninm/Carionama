package com.example.carionama.survey.data

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

data class Indicator(
    val id: String,
    val name: String,
    val description: String,
    val options: List<IndicatorOption>,
    val suggestion: String
)

class IndicatorDeserializer : JsonDeserializer<Indicator> {
    override fun deserialize(
        json: JsonElement, typeOfT: Type, context: JsonDeserializationContext
    ): Indicator {
        val jsonObject = json.asJsonObject

        val id = jsonObject.get("id").asString
        val name = jsonObject.get("name").asString
        val description = jsonObject.get("description").asString
        val suggestion = jsonObject.get("suggestion").asString
        val optionsArray = jsonObject.getAsJsonArray("options")
        val optionsList = mutableListOf<IndicatorOption>()
        optionsArray.forEach { entry ->
            val optionObject = entry.asJsonObject
            val optionId = optionObject.get("id").asString
            val optionDescription = optionObject.get("description").asString
            val optionValue = optionObject.get("value").asFloat
            val relationArray = optionObject.getAsJsonArray("relations")

            val relationsList = mutableListOf<Relation>()
            relationArray.forEach { r ->
                val relationObject = r.asJsonObject
                val relationId = relationObject.get("id").asString
                val effectArray = relationObject.getAsJsonArray("relation")
                val effectsList = mutableListOf<Pair<String, Float>>()
                effectArray.forEach { e ->
                    val effectObject = e.asJsonObject
                    effectsList.add(
                        Pair(
                            effectObject.get("optionID").asString, effectObject.get("value").asFloat
                        )
                    )
                }
                relationsList.add(Relation(relationId, effectsList))
            }
            optionsList.add(
                IndicatorOption(
                    optionId, optionDescription, optionValue, relationsList as List<Relation>
                )
            )
        }

        return Indicator(id, name, description, optionsList as List<IndicatorOption>, suggestion)
    }
}
