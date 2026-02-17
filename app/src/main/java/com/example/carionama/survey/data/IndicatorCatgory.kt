package com.example.carionama.survey.data

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

data class IndicatorCategory(
    val id: String,
    val name: String,
    val relatedIndicators: List<String>,
    val hslRange: HslRange
)

class FirstLevelIndicatorDeserializer : JsonDeserializer<IndicatorCategory> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): IndicatorCategory {
        val jsonObject = json.asJsonObject

        val id = jsonObject.get("id").asString
        val name = jsonObject.get("name").asString

        val rIdArray = jsonObject.getAsJsonArray("related_indicators_id")
        val rIdList = mutableListOf<String>()
        rIdArray.forEach { entry ->
            rIdList.add(entry.asString)
        }

        val hslObject = jsonObject.getAsJsonObject("hsl_ranges")
        val hueObject = hslObject.getAsJsonObject("hue")
        val satObject = hslObject.getAsJsonObject("saturation")
        val lightObject = hslObject.getAsJsonObject("lightness")
        val hslRange = HslRange(
            ValueRange(
                hueObject.get("min").asInt,
                hueObject.get("max").asInt,

                ),
            ValueRange(
                satObject.get("min").asInt,
                satObject.get("max").asInt,

                ),
            ValueRange(
                lightObject.get("min").asInt,
                lightObject.get("max").asInt,

                ),
        )

        return IndicatorCategory(id, name, rIdList, hslRange)
    }
}
