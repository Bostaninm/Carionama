package com.example.unitest

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.junit.Test
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import kotlin.test.assertEquals


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun raed_and_deserialize_json_level_2() {
        val gson = GsonBuilder()
            .registerTypeAdapter(Indicator::class.java, IndicatorDeserializer())
            .create()
        val indicatorListType = object : TypeToken<List<Indicator>>() {}.type
        var inputStream: InputStream? = null

        inputStream =
            javaClass.classLoader?.getResourceAsStream("Importing_JSON_Level2.json")
        val builder = StringBuilder()
        val reader = BufferedReader(InputStreamReader(inputStream))
        var str: String? = reader.readLine()
        while (str != null) {
            builder.append(str)

            str = reader.readLine()
        }

        val indicators = gson.fromJson(
            builder.toString() as String?,
            indicatorListType
        ) as List<Indicator>

        assertEquals("Gender", indicators.get(0).options.get(0).relations.get(1).id)
    }

    @Test
    fun raed_and_deserialize_json_level_3() {
        val gson = GsonBuilder()
            .registerTypeAdapter(Indicator::class.java, IndicatorDeserializer())
            .create()
        val indicatorListType = object : TypeToken<List<Indicator>>() {}.type
        var inputStream: InputStream? = null

        inputStream =
            javaClass.classLoader?.getResourceAsStream("Importing_JSON_Level3.json")
        val builder = StringBuilder()
        val reader = BufferedReader(InputStreamReader(inputStream))
        var str: String? = reader.readLine()
        while (str != null) {
            builder.append(str)

            str = reader.readLine()
        }

        val indicators = gson.fromJson(
            builder.toString() as String?,
            indicatorListType
        ) as List<Indicator>

        assertEquals("MaritalStatus", indicators.get(0).options.get(0).relations.get(1).id)
    }
}