package com.example.unitest.di.koin

import android.content.Context.MODE_PRIVATE
import com.example.unitest.MainViewModel
import com.example.unitest.util.ReadIndicatorsData
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    factory<String>(named("AppLocale")) {
        androidContext().getSharedPreferences("AppSettings", MODE_PRIVATE)
            .getString("AppLocale", null) ?: "en"
    }
    factory { params -> ReadIndicatorsData().readJsonData(params.get()) }
    viewModel { MainViewModel() }
}
