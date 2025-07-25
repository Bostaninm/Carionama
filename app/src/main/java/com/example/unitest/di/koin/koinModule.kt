package com.example.unitest.di.koin

import com.example.unitest.MainViewModel
import com.example.unitest.util.ReadIndicatorsData
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    factory { ReadIndicatorsData().readJsonData() }
    viewModel { MainViewModel() }
}
