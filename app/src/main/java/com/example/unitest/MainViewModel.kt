package com.example.unitest

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.unitest.util.Util
import org.koin.core.component.KoinComponent

private const val TAG = "MainViewModelInfo"

class MainViewModel : ViewModel(), KoinComponent {

    init {
        Log.d(TAG, "Initializing MainViewModel")
    }


    var userAge by mutableStateOf(0)

    var userBMI by mutableStateOf(0f)

    var indicators: List<Indicator>? = null

    val selection = mutableStateMapOf<String, String?>()

    var chartData by mutableStateOf<ChartData?>(null)
        private set
    var showIndicatorDialog by mutableStateOf(false)
        private set
    var showChartDialog by mutableStateOf(false)
        private set
    var showAgeGroupWarning by mutableStateOf(false)
        private set
    var showNullIndicatorWarningDialog by mutableStateOf(false)
        private set
    var nullIndicatorName by mutableStateOf<String?>(null)
    private set

    fun resetSelection() {
        selection.clear()
    }

    fun showAgeGroupWarning() {
        showAgeGroupWarning = true
    }

    fun closeAgeGroupWarning() {
        showAgeGroupWarning = false
    }

    fun setSelection(key: String, value: String) {
        selection[key] = value
    }

    fun showNullIndicatorWarningDialog(indicatorName: String?) {
        showNullIndicatorWarningDialog = true;
        this.nullIndicatorName = indicatorName
    }

    fun closeNullIndicatorWarningDialog() {
        showNullIndicatorWarningDialog = false;
        this.nullIndicatorName = null
    }

    fun showIndicatorDialog() {
        showIndicatorDialog = true
    }

    fun closeIndicatorDialog() {
        showIndicatorDialog = false
    }

    fun changeIndicatorLanguage(lang: String) {
        val indicatorsData: Pair<List<Indicator>, List<Indicator>> =
            ReadIndicatorsData().readJsonData(lang)
        if (userAge > 18)
            indicators = indicatorsData.first
        else
            indicators = indicatorsData.second
    }

    fun validateInputs(): Boolean {
        Log.i(TAG, "Validating Inputs")
        if (userAge in 12..99) {
            indicators?.find { it.id == "AgeGroup" }?.let { ageGroupIndicator ->
                val options = ageGroupIndicator.options
                for (i in options.indices) {
                    val currentOption = options[i]
                    val lowerBound =
                        currentOption.description.toIntOrNull() ?: continue // Skip if not a number

                    val isFirstOption = (i == 0)
                    val isLastOption = (i == options.size - 1)

                    if (isLastOption) {
                        if (userAge >= lowerBound) {
                            selection[ageGroupIndicator.id] = currentOption.id
                            break // Found the group, no need to check further
                        }
                    } else if (isFirstOption) {
                        val nextOption = options[i + 1]
                        val upperBoundExclusive =
                            nextOption.description.toIntOrNull() ?: continue // Skip if not a number

                        if (userAge < upperBoundExclusive) {
                            selection[ageGroupIndicator.id] = currentOption.id
                            break // Found the group, no need to check further
                        }
                    } else {
                        val nextOption = options[i + 1]
                        val upperBoundExclusive =
                            nextOption.description.toIntOrNull() ?: continue // Skip if not a number

                        if (userAge in lowerBound until upperBoundExclusive) {
                            selection[ageGroupIndicator.id] = currentOption.id
                            break // Found the group, no need to check further
                        }
                    }
                }
            }
        }

        //TODO:: Make this more modular and value dependent (Right now it depeneds on the order of option in json file 0 being less than 18.5
        indicators?.find { it.id == "BMI" }?.let { bmiIndicator ->
            if (userBMI < 18.5) {
                selection[bmiIndicator.id] = bmiIndicator.options[0].id
            } else {
                selection[bmiIndicator.id] = bmiIndicator.options[1].id
            }
        }

        indicators?.forEach { indicator ->
            if (selection[indicator.id] == null) {
                showNullIndicatorWarningDialog(indicator.name)
                Log.i(TAG, "Null Indicator ${indicator.id}")
                return false
            }
        }

        return true
    }

    fun showChartDialog() {
        val nonNullSelection = buildMap<String, IndicatorOption> {
            for (s in selection) {
                val indicator = indicators?.find { it.id == s.key }
                val option = indicator?.options?.find { it.id == s.value }
                put(
                    indicator?.name ?: error("Indicator with id of ${s.key} was not found!"),
                    option ?: error("Empty indicator ${s.key}")
                )
            }
        }
        Log.i(TAG, "Showing Chart Dialog with ${nonNullSelection}")
        chartData = Util.calculateChartOnIndicators(nonNullSelection)
        showChartDialog = true
    }

    fun closeChartDialog() {
        chartData = null
        showChartDialog = false
    }

    fun enableAdultForm(aIndicators: List<Indicator>) {
        indicators = aIndicators
        Log.d(TAG, "Setting indicators ${indicators}")
        resetSelection()

    }

    fun enableTeenForm(tIndicators: List<Indicator>) {
        indicators = tIndicators
        resetSelection()
    }
}
