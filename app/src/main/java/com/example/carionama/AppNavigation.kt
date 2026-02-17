package com.example.carionama

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.carionama.about.AboutScreen
import com.example.carionama.survey.SurveyScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "survey",
    ) {
        composable("about") { AboutScreen({ navController.navigateUp() }) }
        composable("survey") { SurveyScreen({ navController.navigate("about") }) }
    }
}
