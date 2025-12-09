package com.example.funlearn

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.funkidslearnsapp.Routes
import com.example.funlearn.ui.screens.GameMenuScreen
import com.example.funlearn.ui.screens.GameplayScreen
import com.example.funlearn.ui.screens.LoseScreen
import com.example.funlearn.ui.screens.PauseScreen
import com.example.funlearn.ui.screens.SettingsScreen
import com.example.funlearn.ui.screens.StartUpScreen
import com.example.funlearn.ui.screens.WinScreen

@Composable
fun FunLearnApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.StartUp) {
        composable(Routes.StartUp) { StartUpScreen(onContinue = { navController.navigate(Routes.GameMenu) }) }
        composable(Routes.GameMenu) { GameMenuScreen(
            onStartGame = { gameId -> navController.navigate("${Routes.Gameplay}/$gameId") },
            onSettings = { navController.navigate(Routes.Settings) }
        ) }
        composable("${Routes.Gameplay}/{gameId}",
            arguments = listOf(navArgument("gameId") { type = NavType.StringType })
        ) { backStackEntry ->
            val gameId = backStackEntry.arguments?.getString("gameId") ?: "1"
            GameplayScreen(gameId = gameId,
                onWin = { navController.navigate(Routes.Win) },
                onLose = { navController.navigate(Routes.Lose) },
                onPause = { navController.navigate(Routes.Pause) }
            )
        }
        composable(Routes.Win) { WinScreen(onBackToMenu = { navController.popBackStack(Routes.GameMenu, false) }) }
        composable(Routes.Lose) { LoseScreen(onRetry = { navController.popBackStack(); navController.navigate(Routes.GameMenu) }) }
        composable(Routes.Settings) { SettingsScreen(onBack = { navController.popBackStack() }) }
        composable(Routes.Pause) { PauseScreen(onResume = { navController.popBackStack() }, onQuit = { navController.navigate(Routes.GameMenu) }) }
        // Additional screens for Letters, Numbers, Shapes as separate entries:
        composable(Routes.Letters) { LettersScreen(onBack = { navController.popBackStack() }) }
        composable(Routes.Numbers) { NumbersScreen(onBack = { navController.popBackStack() }) }
        composable(Routes.Shapes) { ShapesScreen(onBack = { navController.popBackStack() }) }
    }
}

@Composable
fun LettersScreen(onBack: () -> Boolean) {
    TODO("Not yet implemented")
}

@Composable
fun ShapesScreen(onBack: () -> Boolean) {
    TODO("Not yet implemented")
}

@Composable
fun NumbersScreen(onBack: () -> Boolean) {
    TODO("Not yet implemented")
}
