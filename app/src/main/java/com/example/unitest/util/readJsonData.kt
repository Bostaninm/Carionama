package com.example.unitest.util

import android.content.Context
import android.util.Log
import androidx.core.os.LocaleListCompat
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

    fun readJsonData(): Pair<List<Indicator>, List<Indicator>> {
        val context: Context by inject()
        Log.d("LocaleDebug", "Wide Locale::" + LocaleListCompat.getDefault().get(0))
        val adultJsonString =
            context.assets.open("AdultIndicators.json").bufferedReader().use { it.readText() }
        val teenJsonString =
            context.assets.open("TeenIndicators.json").bufferedReader().use { it.readText() }
        val adultIndicators: List<Indicator> = gson.fromJson(adultJsonString, indicatorListType)
        val teenIndicators: List<Indicator> = gson.fromJson(teenJsonString, indicatorListType)
        return Pair(adultIndicators, teenIndicators)
    }
}
