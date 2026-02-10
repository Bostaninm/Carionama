package com.example.unitest.util

import android.content.Context
import com.example.unitest.Indicator
import com.example.unitest.IndicatorDeserializer
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class ReadIndicatorsData : KoinComponent {
    val gson = GsonBuilder()
        .registerTypeAdapter(Indicator::class.java, IndicatorDeserializer())
        .create()
    val indicatorListType = object : TypeToken<List<Indicator>>() {}.type

    fun readJsonData(localeCode: String): Pair<List<Indicator>, List<Indicator>> {
        val context: Context by inject()
        var adultJsonString =
            context.assets.open("Importing_JSON_Level2.json").bufferedReader().use { it.readText() }
        var teenJsonString =
            context.assets.open("Importing_JSON_Level3.json").bufferedReader().use { it.readText() }
        if (localeCode == "fa") {
            adultJsonString =
                context.assets.open("Importing_JSON_Level2.json").bufferedReader()
                    .use { it.readText() }
            teenJsonString =
                context.assets.open("Importing_JSON_Level3.json").bufferedReader()
                    .use { it.readText() }
        }
        val adultIndicators: List<Indicator> = gson.fromJson(adultJsonString, indicatorListType)
        val teenIndicators: List<Indicator> = gson.fromJson(teenJsonString, indicatorListType)
        return Pair(adultIndicators, teenIndicators)
    }
}
