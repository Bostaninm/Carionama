//package com.example.carionama
//
//import android.util.Log
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateMapOf
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.setValue
//import androidx.lifecycle.ViewModel
//import com.example.carionama.survey.domain.Util
//import com.example.carionama.util.ReadIndicatorsData
//import org.koin.core.component.KoinComponent
//
//private const val TAG = "MainViewModelInfo"
//
//class MainViewModel : ViewModel(), KoinComponent {
//
//    init {
//        Log.d(TAG, "Initializing MainViewModel")
//    }
//
//    var userAge by mutableStateOf(0)
//    var percentile by mutableStateOf(0f)
//
//    var userBMI by mutableStateOf(0f)
//
//    var indicators: List<Indicator>? = null
//    var thirdLevelIndicators: List<Indicator>? = null
//    var secondLevelIndicators: List<Indicator>? = null
//    val selection = mutableStateMapOf<String, String?>()
//
//    var thirdLevelChartData by mutableStateOf<ChartData?>(null)
//        private set
//    var secondLevelChartData by mutableStateOf<ChartData?>(null)
//        private set
//    var showChartDialog by mutableStateOf(false)
//        private set
//    var showNullIndicatorWarningDialog by mutableStateOf(false)
//        private set
//    var nullIndicatorName by mutableStateOf<String?>(null)
//        private set
//    val firstLevelIndicator = ReadIndicatorsData().readFirstLevelJson()
//
//    fun resetSelection() {
//        selection.clear()
//    }
//
//    fun setSelection(key: String, value: String) {
//        selection[key] = value
//    }
//
//    fun showNullIndicatorWarningDialog(indicatorName: String?) {
//        showNullIndicatorWarningDialog = true
//        this.nullIndicatorName = indicatorName
//    }
//
//    fun closeNullIndicatorWarningDialog() {
//        showNullIndicatorWarningDialog = false
//        this.nullIndicatorName = null
//    }
//
//    fun changeIndicatorLanguage(lang: String) {
//        val indicatorsData: Pair<List<Indicator>, List<Indicator>> =
//            ReadIndicatorsData().readJsonData(lang)
//        secondLevelIndicators = indicatorsData.first
//        thirdLevelIndicators = indicatorsData.second
//        indicators = indicatorsData.first + indicatorsData.second
//    }
//
//    fun validateInputs(): Boolean {
//        Log.i(TAG, "Validating Inputs")
//        if (userAge in 12..99) {
//            indicators?.find { it.id == "AgeGroup" }?.let { ageGroupIndicator ->
//                val options = ageGroupIndicator.options
//                for (i in options.indices) {
//                    val currentOption = options[i]
//                    val lowerBound =
//                        currentOption.description.toIntOrNull() ?: continue // Skip if not a number
//
//                    val isFirstOption = (i == 0)
//                    val isLastOption = (i == options.size - 1)
//
//                    if (isLastOption) {
//                        if (userAge >= lowerBound) {
//                            selection[ageGroupIndicator.id] = currentOption.id
//                            break // Found the group, no need to check further
//                        }
//                    } else if (isFirstOption) {
//                        val nextOption = options[i + 1]
//                        val upperBoundExclusive =
//                            nextOption.description.toIntOrNull() ?: continue // Skip if not a number
//
//                        if (userAge < upperBoundExclusive) {
//                            selection[ageGroupIndicator.id] = currentOption.id
//                            break // Found the group, no need to check further
//                        }
//                    } else {
//                        val nextOption = options[i + 1]
//                        val upperBoundExclusive =
//                            nextOption.description.toIntOrNull() ?: continue // Skip if not a number
//
//                        if (userAge in lowerBound until upperBoundExclusive) {
//                            selection[ageGroupIndicator.id] = currentOption.id
//                            break // Found the group, no need to check further
//                        }
//                    }
//                }
//            }
//        }
//
//        //TODO:: Make this more modular and value dependent (Right now it depends on the order of option in json file 0 being less than 18.5
//        indicators?.find { it.id == "BMI" }?.let { bmiIndicator ->
//            if (userBMI < 18.5) {
//                selection[bmiIndicator.id] = bmiIndicator.options[0].id
//            } else {
//                selection[bmiIndicator.id] = bmiIndicator.options[1].id
//            }
//        }
//
//        indicators?.forEach { indicator ->
//            if (selection[indicator.id] == null) {
//                showNullIndicatorWarningDialog(indicator.name)
//                Log.i(TAG, "Null Indicator.kt ${indicator.id}")
//                return false
//            }
//        }
//
//        return true
//    }
//
//    fun showChartDialog() {
//        val thirdLevelSelection = selection.filter { s ->
//            thirdLevelIndicators?.any {
//                it.id == s.key
//            } ?: false
//        }
//        val secondLevelSelection = selection.filter { s ->
//            secondLevelIndicators?.any {
//                it.id == s.key
//            } ?: false
//        }
//        Log.d(TAG, "$selection")
//        Log.d(TAG, "$thirdLevelSelection")
//        Log.d(TAG, "$secondLevelSelection")
//        thirdLevelChartData = getChartDataForSelection(thirdLevelSelection)
//        secondLevelChartData = getChartDataForSelection(secondLevelSelection)
//        percentile =
//            Util.calculatePercentile(
//                secondLevelChartData?.pieChartData!! + thirdLevelChartData?.pieChartData!!,
//                indicators
//            )
//        showChartDialog = true
//    }
//
//    fun getChartDataForSelection(currentSelection: Map<String, String?>): ChartData {
//        val nonNullSelection = buildList<Selection> {
//
//            for (s in currentSelection) {
//                val indicator = indicators?.find { it.id == s.key }
//                val option = indicator?.options?.find { it.id == s.value }
//                add(
//                    Selection(
//                        indicator?.id ?: error("Indicator.kt with id of ${s.key} was not found!"),
//                        indicator.name,
//                        option ?: error("Empty indicator ${s.key}")
//                    )
//                )
//            }
//        }
//        Log.i(TAG, "Showing Chart Dialog with ${nonNullSelection}")
//        return Util.calculateChartOnIndicators(nonNullSelection, firstLevelIndicator)
//    }
//
//    fun closeChartDialog() {
//        secondLevelChartData = null
//        thirdLevelChartData = null
//        showChartDialog = false
//    }
//
//    fun assignIndicators(secondLevel: List<Indicator>, thirdLevel: List<Indicator>) {
//        secondLevelIndicators = secondLevel
//        thirdLevelIndicators = thirdLevel
//        indicators = secondLevel + thirdLevel
//        Log.d(TAG, "Setting indicators ${indicators}")
//        resetSelection()
//
//    }
//
//}
