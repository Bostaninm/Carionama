package com.example.carionama.di

import android.content.Context.MODE_PRIVATE
import com.example.carionama.common.data.CarionamaLocals
import com.example.carionama.survey.SurveyViewModel
import com.example.carionama.survey.data.IndicatorCategoryResource
import com.example.carionama.survey.data.IndicatorInfo
import com.example.carionama.survey.data.IndicatorResource
import com.example.carionama.survey.data.LocalsCarionamaMetadata
import com.example.carionama.util.ReadJsonData
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    factory<String>(named("AppLocale")) {
        androidContext().getSharedPreferences("AppSettings", MODE_PRIVATE)
            .getString("AppLocale", null) ?: "en"
    }

    viewModel {
        SurveyViewModel(
            get(),
            get(),
            androidContext().getSharedPreferences("AppSettings", MODE_PRIVATE)
        )
    }

    // This runs blocking the main thread(IO Operation) but the file is small so there should be no problem
    // Change if there is too much delay on app start
    single<ReadJsonData> {
        ReadJsonData(androidContext())
    }

    single<IndicatorInfo> {
        val r: ReadJsonData = get()
        val categoryFileName = "Indicators_Categories"
        val secondLevelFileName = "Indicators_Level_2"
        val thirdLevelFileName = "Indicators_Level_3"
        val categories = IndicatorCategoryResource(CarionamaLocals.entries.associateWith {
            r.readFirstLevelJson(categoryFileName + "_" + it.toString() + ".json")
        })
        val secondLevelIndicators = IndicatorResource(CarionamaLocals.entries.associateWith {
            r.readIndicatorJson(secondLevelFileName + "_" + it.toString() + ".json")
        })
        val thirdLevelIndicators = IndicatorResource(CarionamaLocals.entries.associateWith {
            r.readIndicatorJson(thirdLevelFileName + "_" + it.toString() + ".json")
        })

        IndicatorInfo(categories, secondLevelIndicators, thirdLevelIndicators)
    }

    single<List<LocalsCarionamaMetadata>> {
        val r: ReadJsonData = get()
        var metadata = mutableListOf<LocalsCarionamaMetadata>()
        CarionamaLocals.entries.forEach {
            val metadataFileName = "Metadata_$it.json"
            metadata.add(LocalsCarionamaMetadata(it, r.readCarionamaMetadata(metadataFileName)))
        }
        metadata
    }
}
