package com.example.carionama.survey.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.carionama.R

@Composable
fun AgeGroup(modifier: Modifier = Modifier, onValidAgeEntered: (validAge: Int?) -> Unit) {

    var inputAge by remember { mutableStateOf("") }

//    if (validAge != null) {
//        viewModel.userAge = validAge
//        viewModel.assignIndicators(indicatorsData.first, indicatorsData.second)
//    }

    RangedNumberInputField(
        value = inputAge,
        onValueChange = { inputAge = it },
        onNumberInRangeChanged = onValidAgeEntered,
        label = stringResource(R.string.age),
        minRange = 12,
        maxRange = 99,
        modifier = modifier
    )
}