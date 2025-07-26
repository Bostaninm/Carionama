import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.os.LocaleListCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.unitest.MainViewModel
import com.example.unitest.R
import org.koin.androidx.compose.koinViewModel

fun setAndPersistLocale(context: Context, lang: String) {
    val sharedPrefs = context.getSharedPreferences("AppSettings", Activity.MODE_PRIVATE)
    sharedPrefs.edit().putString("AppLocale", lang).apply()

    val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags(lang)
    Log.d("LocaleDebug", "appLocale ::" + appLocale.toLanguageTags())
    AppCompatDelegate.setApplicationLocales(appLocale)

}

@SuppressLint("LocalContextConfigurationRead")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {

    val currentContext = LocalContext.current

    val navController = rememberNavController()
    var currentLanguage by remember { mutableStateOf("En") } // Default language
    val viewModel: MainViewModel = koinViewModel()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.app_name)) },
                navigationIcon = {
                    if (navController.currentBackStackEntryAsState().value?.destination?.route != "home") {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(
                                    R.string.back
                                )
                            )
                        }
                    }
                },
                actions = {
                    // Language Selector
                    var languageMenuExpanded by remember { mutableStateOf(false) }
                    IconButton(onClick = { languageMenuExpanded = true }) {
                        Icon(
                            Icons.Filled.Language,
                            contentDescription = stringResource(id = R.string.language)
                        )
                    }
                    DropdownMenu(
                        expanded = languageMenuExpanded,
                        onDismissRequest = { languageMenuExpanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text(stringResource(id = R.string.english)) },
                            onClick = {
                                currentLanguage = "en"
                                setAndPersistLocale(context, "en")
                                Log.d(
                                    "LocaleDebug",
                                    "Composable Locale: ${currentContext.resources.configuration.locales[0]}"
                                )
                                // Add logic here to change app locale if needed
                                languageMenuExpanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(stringResource(id = R.string.farsi)) },
                            onClick = {
                                currentLanguage = "fa"
                                setAndPersistLocale(context, "fa")

//                                val locale = Locale("fa")
//                                val resource = context.resources
//                                val configuration = resource.configuration
//                                configuration.setLayoutDirection(locale)
//                                resource.updateConfiguration(configuration, resource.displayMetrics)
//
//                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
//                                    context.createConfigurationContext(configuration)
                                val activity = context as? Activity
                                activity?.recreate()
                                // Add logic here to change app locale if needed
                                languageMenuExpanded = false
                            }
                        )
                    }

                    // About Page Icon
                    IconButton(onClick = { navController.navigate("about") }) {
                        Icon(
                            Icons.Filled.Info,
                            contentDescription = stringResource(id = R.string.about)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { HomeScreen(navController, viewModel, currentLanguage) }
            composable("about") { AboutScreen(navController) }
            composable("adult") { IndicatorListAdult(navController, viewModel) }
            composable("teen") { IndicatorListChild(navController, viewModel) }
        }
    }
}
