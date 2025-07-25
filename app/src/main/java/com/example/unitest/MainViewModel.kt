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

    val selection = mutableStateMapOf<String, IndicatorOption?>()

    var chartData by mutableStateOf<ChartData?>(null)
        private set
    var showIndicatorDialog by mutableStateOf(false)
        private set
    var showChartDialog by mutableStateOf(false)
        private set

    fun resetSelection() {
        selection.clear()
    }

    fun setSelection(key: String, value: IndicatorOption) {
        selection[key] = value
    }

    fun showIndicatorDialog() {
        showIndicatorDialog = true
    }

    fun closeIndicatorDialog() {
        showIndicatorDialog = false
    }

    fun validateInputs(): Boolean {
        Log.i(TAG, "Validating Inputs")
        if (userAge in 12..99) {
            indicators?.find { it.id == "AgeGroup" }?.let { ageGroupIndicator ->
                val options = ageGroupIndicator.options
                for (i in options.indices) {
                    val currentOption = options[i]
                    val lowerBound =
                        currentOption.name.toIntOrNull() ?: continue // Skip if not a number

                    val isFirstOption = (i == 0)
                    val isLastOption = (i == options.size - 1)

                    if (isLastOption) {
                        if (userAge >= lowerBound) {
                            selection[ageGroupIndicator.name] = currentOption
                            break // Found the group, no need to check further
                        }
                    } else if (isFirstOption) {
                        val nextOption = options[i + 1]
                        val upperBoundExclusive =
                            nextOption.name.toIntOrNull() ?: continue // Skip if not a number

                        if (userAge < upperBoundExclusive) {
                            selection[ageGroupIndicator.name] = currentOption
                            break // Found the group, no need to check further
                        }
                    } else {
                        val nextOption = options[i + 1]
                        val upperBoundExclusive =
                            nextOption.name.toIntOrNull() ?: continue // Skip if not a number

                        if (userAge in lowerBound until upperBoundExclusive) {
                            selection[ageGroupIndicator.name] = currentOption
                            break // Found the group, no need to check further
                        }
                    }
                }
            }
        }

        //TODO:: Make this more modular
        indicators?.find { it.id == "BMI" }?.let { bmiIndicator ->
            if (userBMI < 18.5) {
                selection[bmiIndicator.name] = bmiIndicator.options[0]
            } else {
                selection[bmiIndicator.name] = bmiIndicator.options[1]
            }
        }

        indicators?.forEach { indicator ->
            if (selection[indicator.name] == null) {
                Log.i(TAG, "Null Indicator ${indicator.name}")
                return false
            }
        }

        return true
    }

    fun showChartDialog() {
        val nonNullSelection = buildMap<String, IndicatorOption> {
            for (s in selection) {
                put(s.key, s.value ?: error("Empty indicator ${s.key}"))
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

    fun enableAdultForm() {
        indicators = adultIndicators
        Log.d(TAG, "Setting indicators ${indicators}")
        resetSelection()

    }

    fun enableTeenForm() {
        indicators = teenIndicators
        resetSelection()
    }
}
