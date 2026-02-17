package com.example.carionama

//import AboutScreen
//import AppNavigation
//import android.graphics.Color
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.IntrinsicSize
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.heightIn
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.layout.wrapContentHeight
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.Button
//import androidx.compose.material3.NavigationBar
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.Dp
//import androidx.compose.ui.unit.dp
//import com.example.carionama.ui.theme.UniTestTheme
//import androidx.compose.ui.window.Dialog
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.himanshoe.charty.pie.PieChart
//
import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.toArgb
import com.example.carionama.theme.CarionamaTheme

// Assuming you have these string resources defined in strings.xml
// R.string.app_name, R.string.about, R.string.language, R.string.english, R.string.farsi
// R.string.home_screen_title, R.string.about_screen_title

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val sharedPrefs = getSharedPreferences("AppSettings", MODE_PRIVATE)
//        val currentLocaleTag = sharedPrefs.getString("AppLocale", null)
//        if (currentLocaleTag != null) {
//            val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags(currentLocaleTag)
//            AppCompatDelegate.setApplicationLocales(appLocale)
//        }
//        Log.d(
//            "LocaleDebug",
//            "Composable Locale: ${this.resources.configuration.locales[0]}"
//        )
//        Log.d(
//            "LocaleDebug",
//            "shared prefs AppLocale value: ${currentLocaleTag}"
//        )
        setContent {
            CarionamaTheme {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.auto(
                        lightScrim = MaterialTheme.colorScheme.background.toArgb(),
                        darkScrim = MaterialTheme.colorScheme.background.toArgb()
                    )
                )
                AppNavigation()
            }
        }
    }
}
