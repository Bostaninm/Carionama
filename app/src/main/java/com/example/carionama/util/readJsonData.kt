package com.example.carionama.util

import android.content.Context
import com.example.carionama.survey.data.CarionamaMetadata
import com.example.carionama.survey.data.CarionamaMetadataDeserializer
import com.example.carionama.survey.data.FirstLevelIndicatorDeserializer
import com.example.carionama.survey.data.Indicator
import com.example.carionama.survey.data.IndicatorCategory
import com.example.carionama.survey.data.IndicatorDeserializer
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.koin.core.component.KoinComponent


class ReadJsonData(val context: Context) : KoinComponent {
    private val gson = GsonBuilder()
        .registerTypeAdapter(CarionamaMetadata::class.java, CarionamaMetadataDeserializer())
        .registerTypeAdapter(Indicator::class.java, IndicatorDeserializer())
        .registerTypeAdapter(IndicatorCategory::class.java, FirstLevelIndicatorDeserializer())
        .create()

    fun readIndicatorJson(fileName: String): List<Indicator> {
        val indicatorListType = object : TypeToken<List<Indicator>>() {}.type
        val jsonString =
            context.assets.open(fileName).bufferedReader().use { it.readText() }
        return gson.fromJson(jsonString, indicatorListType)
    }

    fun readFirstLevelJson(fileName: String): List<IndicatorCategory> {
        val firstLevelListType = object : TypeToken<List<IndicatorCategory>>() {}.type
        val firstLevelJsonString =
            context.assets.open(fileName).bufferedReader().use { it.readText() }
        return gson.fromJson(firstLevelJsonString, firstLevelListType)
    }

    fun readCarionamaMetadata(fileName: String): CarionamaMetadata {
        val metadataJsonString =
            context.assets.open(fileName).bufferedReader().use { it.readText() }
        return gson.fromJson(metadataJsonString, CarionamaMetadata::class.java)
    }
}
