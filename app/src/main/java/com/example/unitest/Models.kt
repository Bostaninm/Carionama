package com.example.unitest

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.himanshoe.charty.pie.model.PieChartData
import java.lang.reflect.Type

data class Indicator(
    val id: String,
    val name: String,
    val description: String,
    val options: List<IndicatorOption>
)

data class IndicatorOption(val id: String, val name: String, val value: Float)

data class ChartData(val pieChartData: List<PieChartData>)


// Custom deserializer for the Indicator class
class IndicatorDeserializer : JsonDeserializer<Indicator> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Indicator {
        val jsonObject = json.asJsonObject

        val id = jsonObject.get("id").asString
        val name = jsonObject.get("name").asString
        val description = jsonObject.get("description").asString

        val optionsObject = jsonObject.getAsJsonObject("options")
        val optionsList = optionsObject.entrySet().map { entry ->
            IndicatorOption(name = entry.key, value = entry.value.asFloat)
        }

        return Indicator(id, name, description, optionsList)
    }
}
