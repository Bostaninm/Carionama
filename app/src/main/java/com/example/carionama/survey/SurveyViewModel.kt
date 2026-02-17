package com.example.carionama.survey

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carionama.common.data.CarionamaLocals
import com.example.carionama.survey.data.IndicatorInfo
import com.example.carionama.survey.data.IndicatorLevel
import com.example.carionama.survey.data.IndicatorOption
import com.example.carionama.survey.data.IndicatorView
import com.example.carionama.survey.data.getResource
import com.example.carionama.survey.domain.Util
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent

private const val TAG = "SurveyViewModel"

class SurveyViewModel(
    private val indInfo: IndicatorInfo,
    private val sharedPreferences: SharedPreferences
) : ViewModel(), KoinComponent {
    private var hasLoadedInitialData = false
    private val _state = MutableStateFlow(SurveyState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                val lanCode = sharedPreferences.getString("AppLocale", null) ?: "en"
                val initLang = CarionamaLocals.fromCode(lanCode)
                changeLanguage(initLang)

                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = SurveyState()
        )

    fun onAction(action: SurveyAction) {
        when (action) {

            is SurveyAction.OnLanguageSelected -> {
                closeLanguageMenu()
                changeLanguage(action.language)
                persistLanguageChange(action.language)
            }

            SurveyAction.OnLanguageIconClicked -> openLanguageMenu()

            SurveyAction.OnLanguageMenuDismissed -> closeLanguageMenu()

            is SurveyAction.OnBMIChange -> {
                val iOption =
                    Util.getAvailableBMIOptionByValue(action.bmiView.indicator, action.bmi)
                setSelection(action.bmiView, iOption)
            }

            is SurveyAction.OnIndicatorOptionSelected -> {
                setSelection(action.indicatorView, action.option)
            }

            is SurveyAction.OnValidAgeNumberEntered -> {
                if (action.validAge == null) {
                    //TODO::Error
                    return
                }
                val iOption =
                    Util.getAvailableAgeOptionByValue(action.ageView.indicator, action.validAge)
                setSelection(action.ageView, iOption)
            }

            SurveyAction.OnActionButtonClicked -> submitSelection()

            SurveyAction.OnChartDialogDismissed -> {
                _state.update {
                    it.copy(
                        showChartDialog = false
                    )
                }
            }

            SurveyAction.DismissWarningDialog -> {
                _state.update {
                    it.copy(
                        showWarningDialog = false,
                        firstNullIndicatorName = ""
                    )
                }
            }
        }
    }

    private fun submitSelection() {
        state.value.indicatorViews.forEach { view ->
            if (view.selection == null) {
                _state.update {
                    it.copy(
                        showWarningDialog = true,
                        firstNullIndicatorName = view.indicator.name
                    )
                }
                return
            }
        }
        _state.update {
            val secondLevelViews = it.indicatorViews.filter { it.level == IndicatorLevel.SECOND }
            val thirdLevelViews = it.indicatorViews.filter { it.level == IndicatorLevel.THIRD }
            val secondLevelChartData =
                Util.calculateChartOnIndicators(secondLevelViews, it.indicatorCategories)
            val thirdLevelChartData =
                Util.calculateChartOnIndicators(thirdLevelViews, it.indicatorCategories)
            it.copy(
                showChartDialog = true,
                secondLevelChartData = secondLevelChartData,
                thirdLevelChartData = thirdLevelChartData,
            )
        }
    }

    private fun setSelection(indicatorView: IndicatorView, indicatorOption: IndicatorOption) {
        val newIndicatorView = indicatorView.copy(selection = indicatorOption)
        _state.update {
            val newIndicatorView = it.indicatorViews.map { oldView ->
                if (oldView.indicator.id == indicatorView.indicator.id) newIndicatorView else oldView
            }
            it.copy(
                indicatorViews = newIndicatorView
            )
        }
    }

    private fun openLanguageMenu() {
        _state.update {
            it.copy(
                languageMenuExpanded = true
            )
        }
    }

    private fun closeLanguageMenu() {
        _state.update {
            it.copy(
                languageMenuExpanded = false
            )
        }
    }

    fun changeLanguage(language: CarionamaLocals) {
        _state.update {
            it.copy(
                currentLanguage = language
            )
        }
        applySystemLanguageChange(language)
        changeIndicatorLanguage(language)
    }

    private fun changeIndicatorLanguage(language: CarionamaLocals) {
        val indicatorInfo = indInfo.getResource(language)
        if (indicatorInfo != null) {
            val indicators = indicatorInfo.second + indicatorInfo.third
            val indicatorViews = indicators.map { iv ->
                val isSecond = indicatorInfo.second.firstOrNull { it.id == iv.id } != null
                val level = if (isSecond) IndicatorLevel.SECOND else IndicatorLevel.THIRD
                val selectionId =
                    state.value.indicatorViews.firstOrNull { it.indicator.id == iv.id }?.selection?.id
                val selection =
                    if (selectionId != null) iv.options.firstOrNull { it.id == selectionId } else null
                IndicatorView(iv, selection, level)
            }


            _state.update {
                it.copy(
                    indicatorCategories = indicatorInfo.first,
                    indicatorViews = indicatorViews
                )
            }
        }
    }

    fun persistLanguageChange(language: CarionamaLocals) {
        sharedPreferences.edit { putString("AppLocale", language.code) }
    }

    fun applySystemLanguageChange(language: CarionamaLocals) {
        val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags(language.code)
        AppCompatDelegate.setApplicationLocales(appLocale)
    }
}


