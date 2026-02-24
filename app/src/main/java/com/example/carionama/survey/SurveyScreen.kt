@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.carionama.survey

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.carionama.R
import com.example.carionama.common.data.CarionamaLocals
import com.example.carionama.survey.components.AgeGroup
import com.example.carionama.survey.components.AgeGroupWarningDialog
import com.example.carionama.survey.components.BMI
import com.example.carionama.survey.components.NullIndicatorWarningDialog
import com.example.carionama.survey.components.Select
import com.example.carionama.survey.components.piechart.ChartDialog
import org.koin.androidx.compose.koinViewModel

@Composable
fun SurveyScreen(
    onAboutIconClicked: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SurveyViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val onAction = viewModel::onAction

    if (state.showChartDialog) {
        ChartDialog(
            state.indicatorCategories,
            state.secondLevelChartView,
            state.thirdLevelChartView,
            state.riskPercentile,
            state.riskLabel,
            {
                onAction(
                    SurveyAction.OnChartDialogDismissed
                )
            })
    }
    if (state.showWarningDialog) {
        NullIndicatorWarningDialog(
            { onAction(SurveyAction.OnDismissWarningDialog) },
            state.firstNullIndicatorName
        )
    }

    when (state.uiState) {
        CarionamaUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    "Loading...",
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f)
                )
            }
        }

        CarionamaUiState.ShowInitialWarning ->
            AgeGroupWarningDialog(
                title = state.warningTitle,
                message = state.warningMessage,
                onProceed = {
                    onAction(SurveyAction.OnGeneralWarningProceedClicked)
                }
            )

        CarionamaUiState.Ready ->
            Scaffold(
                topBar = {
                    SurveyTopAppBar(state, onAction, onAboutIconClicked)
                }
            ) { innerPadding ->
                SurveyContent(
                    modifier = modifier.padding(innerPadding),
                    state = state,
                    onAction = viewModel::onAction
                )
            }
    }

}

@Composable
fun SurveyTopAppBar(
    state: SurveyState,
    onAction: (SurveyAction) -> Unit,
    onAboutIconClicked: () -> Unit
) {
    TopAppBar(
        title = { Text(stringResource(id = R.string.app_name)) },
        actions = {
            // Language Selector
            IconButton(onClick = { onAction(SurveyAction.OnLanguageIconClicked) }) {
                Icon(
                    Icons.Filled.Language,
                    contentDescription = stringResource(id = R.string.language)
                )
            }
            if (state.languageMenuExpanded) {
                DropdownMenu(
                    expanded = state.languageMenuExpanded,
                    onDismissRequest = { onAction(SurveyAction.OnLanguageMenuDismissed) }
                ) {
                    CarionamaLocals.entries.forEach {
                        DropdownMenuItem(
                            text = { Text(stringResource(id = it.displayNameResId)) },
                            onClick = {
                                onAction(SurveyAction.OnLanguageSelected(it))
                            }
                        )

                    }
                }
            }
            // About Page Icon
            IconButton(onClick = onAboutIconClicked) {
                Icon(
                    Icons.Filled.Info,
                    contentDescription = stringResource(id = R.string.about)
                )
            }
        }
    )
}


@Composable
fun SurveyContent(
    modifier: Modifier = Modifier,
    state: SurveyState,
    onAction: (SurveyAction) -> Unit,
) {
    val scrollState = rememberScrollState()

    Box(
        modifier
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
                .verticalScroll(scrollState)
        ) {
            state.indicatorViews.forEach { iView ->
                when (iView.indicator.id) {
                    "AgeGroup" -> {
                        AgeGroup(
                            Modifier
                                .fillMaxWidth()
                                .padding(4.dp),
                            initAge = state.currentAge,
                        ) { validAge ->
                            onAction(SurveyAction.OnValidAgeNumberEntered(iView, validAge))
                        }
                    }

                    "BMI" -> {
                        BMI(
                            Modifier.padding(12.dp),
                            initHeight = state.currentHeight,
                            initWeight = state.currentWeight,
                            { height, weight, bmi ->
                                onAction(
                                    SurveyAction.OnBMIChange(
                                        iView,
                                        height,
                                        weight,
                                        bmi
                                    )
                                )
                            })
                    }

                    else -> {
                        Select(
                            indicator = iView.indicator,
                            selected = iView.selection
                        ) { option ->
                            onAction(
                                SurveyAction.OnIndicatorOptionSelected(
                                    iView,
                                    option
                                )
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(32.dp)) // Add Spacer here
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp), // Add padding for the content within the Box
            contentAlignment = Alignment.BottomEnd // Align content to the bottom right
        ) {
            Button(
                onClick = { onAction(SurveyAction.OnActionButtonClicked) },
                // No need for Modifier.align here as Box's contentAlignment handles it
                modifier = Modifier
                    .width(64.dp) // Standard FAB size
                    .height(64.dp),
                shape = CircleShape // Make the button round
            ) {
                Icon(
                    Icons.Filled.Done,
                    contentDescription = stringResource(R.string.done),
                    modifier = Modifier.size(64.dp)
                )
            }
        }

    }
}
